#!/usr/bin/env python3
# SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
# SPDX-License-Identifier: Apache-2.0

"""Run dependency-free structural checks for the Nook Forge repository."""

from __future__ import annotations

import json
import re
import sys
import tomllib
from pathlib import Path
from urllib.parse import unquote

from comment_rules import find_comment_rule_errors

ROOT = Path(__file__).resolve().parents[4]
ERRORS: list[str] = []


def fail(message: str) -> None:
    ERRORS.append(message)


def section_text(text: str, heading: str) -> str:
    match = re.search(
        rf"^## {re.escape(heading)}\s*$\n(.*?)(?=^## |\Z)",
        text,
        re.MULTILINE | re.DOTALL,
    )
    return match.group(1).strip() if match else ""


def syllable_count(word: str) -> int:
    clean = re.sub(r"[^a-z]", "", word.casefold())
    if not clean:
        return 0
    count = len(re.findall(r"[aeiouy]+", clean))
    if clean.endswith("e") and count > 1 and not clean.endswith(("le", "ye")):
        count -= 1
    if clean.endswith("es") and count > 1 and not clean.endswith(("aes", "ees", "oes")):
        count -= 1
    return max(1, count)


def flesch_reading_ease(text: str) -> float:
    plain = re.sub(r"`[^`]+`", " ", text)
    words = re.findall(r"[A-Za-z]+(?:'[A-Za-z]+)?", plain)
    sentences = re.findall(r"[.!?]+", plain)
    if not words or not sentences:
        return 0.0
    syllables = sum(syllable_count(word) for word in words)
    return 206.835 - 1.015 * (len(words) / len(sentences)) - 84.6 * (
        syllables / len(words)
    )


def sentence_count(text: str) -> int:
    plain = re.sub(r"https?://\S+", "URL", text.strip())
    return len(re.findall(r"[.!?](?:$|\s|[`*_])", plain))


IGNORED_PARTS = {
    ".angular",
    ".git",
    ".gradle",
    ".idea",
    ".pytest_cache",
    ".venv",
    "__pycache__",
    "coverage",
    "dist",
    "generated",
    "node_modules",
    "playwright-report",
    "target",
    "test-results",
}

for path in ROOT.rglob("*.json"):
    if any(part in IGNORED_PARTS for part in path.parts):
        continue
    try:
        json.loads(path.read_text(encoding="utf-8"))
    except Exception as error:
        fail(f"Invalid JSON {path.relative_to(ROOT)}: {error}")

for path in ROOT.rglob("*.toml"):
    if any(part in IGNORED_PARTS for part in path.parts):
        continue
    try:
        tomllib.loads(path.read_text(encoding="utf-8"))
    except Exception as error:
        fail(f"Invalid TOML {path.relative_to(ROOT)}: {error}")

required_agents = {"architect.toml", "implementation-worker.toml", "reviewer.toml"}
agent_dir = ROOT / ".codex/agents"
found_agents = {path.name for path in agent_dir.glob("*.toml")}
if found_agents != required_agents:
    fail(f"Codex agent set is invalid: {sorted(found_agents)}")

required_skills = {
    "ai-workflows",
    "design-handoff",
    "file-workspace-safety",
    "full-stack-delivery",
    "observability",
    "release-evidence",
}
skill_dir = ROOT / ".agents/skills"
found_skills = {path.parent.name for path in skill_dir.glob("*/SKILL.md")}
if found_skills != required_skills:
    fail(f"Repository skill set is invalid: {sorted(found_skills)}")

for forbidden_path in {
    ROOT / "CONTRIBUTING.md",
    ROOT / "PLANNING-ARCHIVE-MANIFEST.txt",
    ROOT / "docs/planning-manifest.md",
}:
    if forbidden_path.exists():
        fail(f"Forbidden planning or external-workflow file exists: {forbidden_path.relative_to(ROOT)}")

for required_path in {
    ROOT / "docs/planning-scope.md",
    ROOT / "docs/user-guide.md",
    ROOT / "docs/visual-documentation.md",
    ROOT / "docs/design/penpot-handoff-template.md",
    ROOT / "docs/releases/release-0.5-observability-and-reliability/stories/NFA-032-add-optional-langfuse-tracing.md",
}:
    if not required_path.exists():
        fail(f"Required planning file is missing: {required_path.relative_to(ROOT)}")

stories = sorted((ROOT / "docs/releases").rglob("NFA-*.md"))
if not stories:
    fail("Expected at least one NFA story.")
elif len(stories) != 36:
    fail(f"Expected 36 NFA stories, found {len(stories)}.")

story_ids: dict[int, Path] = {}
for path in stories:
    match = re.match(r"NFA-(\d+)-", path.name)
    if not match:
        fail(f"Invalid story filename: {path.relative_to(ROOT)}")
        continue
    number = int(match.group(1))
    if number in story_ids:
        fail(f"Duplicate story ID: NFA-{number:03d}")
    story_ids[number] = path

if story_ids:
    expected_ids = set(range(1, max(story_ids) + 1))
    missing_ids = sorted(expected_ids.difference(story_ids))
    if missing_ids:
        fail("Missing story IDs: " + ", ".join(f"NFA-{n:03d}" for n in missing_ids))

required_headings = [
    "## Status",
    "## User story",
    "## Goal",
    "## Dependencies",
    "## Acceptance criteria",
    "## Out of scope",
]
for story_number, path in sorted(story_ids.items()):
    text = path.read_text(encoding="utf-8")
    headings = [line.strip() for line in text.splitlines() if line.startswith("## ")]
    if headings != required_headings:
        fail(
            f"Invalid story headings in {path.relative_to(ROOT)}: "
            + ", ".join(headings)
        )

    if re.search(
        r"^## (?:Known issue|Known issues|Known limitation|Known limitations)\s*$",
        text,
        re.IGNORECASE | re.MULTILINE,
    ):
        fail(f"Story has a forbidden issue section: {path.relative_to(ROOT)}")

    status_match = re.search(
        r"^## Status\s+^(Planned|In progress|Implemented)\s*$",
        text,
        re.MULTILINE,
    )
    if not status_match:
        fail(f"Invalid story status in {path.relative_to(ROOT)}")
        status = None
    else:
        status = status_match.group(1)

    criteria = re.findall(r"^- \[([ xX])\] (.+)$", text, re.MULTILINE)
    if not 4 <= len(criteria) <= 8:
        fail(
            f"Expected 4 to 8 criteria in {path.relative_to(ROOT)}, "
            f"found {len(criteria)}."
        )
    for _, criterion in criteria:
        if sentence_count(criterion) != 1:
            fail(
                f"Criterion must be one sentence in {path.relative_to(ROOT)}: "
                f"{criterion}"
            )

    if status == "Planned" and any(mark.casefold() == "x" for mark, _ in criteria):
        fail(f"Planned story has checked criteria: {path.relative_to(ROOT)}")
    if status == "Implemented" and any(mark == " " for mark, _ in criteria):
        fail(f"Implemented story has unchecked criteria: {path.relative_to(ROOT)}")

    readable_text = " ".join(
        [section_text(text, "User story"), section_text(text, "Goal")]
    )
    reading_score = flesch_reading_ease(readable_text)
    if reading_score < 80:
        fail(
            f"Story prose is below Flesch 80 in {path.relative_to(ROOT)}: "
            f"{reading_score:.1f}"
        )

    for paragraph in re.split(r"\n\s*\n", text):
        stripped = paragraph.strip()
        if not stripped or stripped.startswith(("#", "-", "```")):
            continue
        if sentence_count(stripped) > 5:
            fail(f"Story prose block exceeds five sentences: {path.relative_to(ROOT)}")

    dependency_text = section_text(text, "Dependencies")
    dependency_ids = [int(value) for value in re.findall(r"NFA-(\d+)", dependency_text)]
    for dependency_id in dependency_ids:
        if dependency_id not in story_ids:
            fail(f"Unknown dependency NFA-{dependency_id:03d} in {path.relative_to(ROOT)}")
        if dependency_id >= story_number:
            fail(f"Future dependency NFA-{dependency_id:03d} in {path.relative_to(ROOT)}")

    release_root = path.parent.parent
    release_map = release_root / "README.md"
    implementation_plan = release_root / "implementation-plan.md"
    verification = release_root / "verification.md"
    if not release_map.exists():
        fail(f"Missing release map for {path.relative_to(ROOT)}")
    else:
        release_text = release_map.read_text(encoding="utf-8")
        if f"stories/{path.name}" not in release_text:
            fail(f"Story is not linked from its release map: {path.relative_to(ROOT)}")
        if "(verification.md)" not in release_text:
            fail(f"Verification is not linked from its release map: {path.relative_to(ROOT)}")
        if "(implementation-plan.md)" not in release_text:
            fail(f"Implementation plan is not linked from its release map: {path.relative_to(ROOT)}")
    if not implementation_plan.exists():
        fail(f"Missing implementation plan for {path.relative_to(ROOT)}")
    if not verification.exists():
        fail(f"Missing release verification for {path.relative_to(ROOT)}")
    elif f"stories/{path.name}" not in verification.read_text(encoding="utf-8"):
        fail(f"Story is not linked from release verification: {path.relative_to(ROOT)}")

link_pattern = re.compile(r"!?\[[^\]]*\]\(([^)]+)\)")
for path in ROOT.rglob("*.md"):
    if any(part in IGNORED_PARTS for part in path.parts):
        continue
    text = path.read_text(encoding="utf-8")
    for raw_target in link_pattern.findall(text):
        target = raw_target.strip().split(maxsplit=1)[0].strip("<>")
        if not target or target.startswith(("http://", "https://", "mailto:", "#")):
            continue
        target = unquote(target.split("#", 1)[0])
        if not target:
            continue
        resolved = (path.parent / target).resolve()
        try:
            resolved.relative_to(ROOT.resolve())
        except ValueError:
            fail(f"Markdown link escapes the repository in {path.relative_to(ROOT)}: {target}")
            continue
        if not resolved.exists():
            fail(f"Broken local Markdown link in {path.relative_to(ROOT)}: {target}")

if (ROOT / ".env").exists():
    fail("The repository must not contain a .env file.")

gitignore_lines = set((ROOT / ".gitignore").read_text(encoding="utf-8").splitlines())
for required_line in {".env", ".env.*", "!.env.example"}:
    if required_line not in gitignore_lines:
        fail(f".gitignore is missing the environment rule: {required_line}")

env_example = (ROOT / ".env.example").read_text(encoding="utf-8")
for required_value in {
    "AI_PROVIDER=ollama",
    "OLLAMA_BASE_URL=",
    "OLLAMA_CONTAINER_PORT=11435",
    "LANGFUSE_ENABLED=false",
    "LANGFUSE_TRACE_CONTENT=false",
    "LANGFUSE_BASE_URL=",
}:
    if required_value not in env_example:
        fail(f".env.example is missing: {required_value}")
if re.search(r"^OPENROUTER_", env_example, re.MULTILINE):
    fail(".env.example must not expose future OpenRouter settings before implementation.")

brand = json.loads((ROOT / "packages/brand/brand.json").read_text(encoding="utf-8"))
expected_brand = {
    "productName": "Nook Forge",
    "extendedName": "Nook Forge AI",
    "tagline": "Turn local files into useful work.",
    "technical": {
        "repository": "nook-forge-ai",
        "appId": "nook-forge-ai",
        "javaPackage": "io.nookforge",
        "mavenArtifact": "nook-forge-api",
        "npmScope": "@nookforge/*",
        "dockerProject": "nookforge",
        "database": "nookforge",
        "storyPrefix": "NFA",
    },
}
if brand != expected_brand:
    fail("The stable Nook Forge brand identity is invalid.")

readme = (ROOT / "README.md").read_text(encoding="utf-8")
for required_phrase in {
    "Nook Forge AI is a local-first workspace",
    "planning and governance baseline",
    "not a runnable application",
    "USER APPROVES PLAN",
    "USER APPROVES IMPLEMENTATION",
    "USER APPROVES COMMIT",
}:
    if required_phrase not in readme:
        fail(f"README is missing the planning or approval statement: {required_phrase}")


text_paths = [
    path
    for path in ROOT.rglob("*")
    if path.is_file()
    and path.name != "LICENSE"
    and path.name != "package-lock.json"
    and path.resolve() != Path(__file__).resolve()
    and path.suffix in {".md", ".toml", ".py", ".json"}
]
text_paths.append(ROOT / ".env.example")
for path in text_paths:
    text = path.read_text(encoding="utf-8")
    if re.search(r"OpenTelemetry|\bOTEL\b|OTEL_|OTLP|otel-collector", text, re.IGNORECASE):
        fail(f"Standalone OpenTelemetry planning remains in {path.relative_to(ROOT)}")
    if re.search(r"flowchart\s+(?:LR|RL|TD)\b", text):
        fail(f"Non-vertical Mermaid flowchart in {path.relative_to(ROOT)}")
    if re.search(r"pull request|public issue|feature contribution", text, re.IGNORECASE):
        fail(f"External development invitation remains in {path.relative_to(ROOT)}")

design_guide = (ROOT / "docs/design/README.md").read_text(encoding="utf-8")
for required_phrase in {
    "When the user supplies a Penpot design link",
    "read-only MCP inspection",
    "USER APPROVES PLAN",
    "USER APPROVES IMPLEMENTATION",
    "Playwright screenshots",
}:
    if required_phrase not in design_guide:
        fail(f"Penpot handoff guide is missing: {required_phrase}")

visual_guide = (ROOT / "docs/visual-documentation.md").read_text(encoding="utf-8")
for required_phrase in {
    "deterministic Playwright automation",
    "Nook Forge AI must not build, start, execute, or test an uploaded project",
    "flowchart TB",
    "top-to-bottom",
}:
    if required_phrase not in visual_guide:
        fail(f"Visual documentation guide is missing: {required_phrase}")

roadmap = (ROOT / "docs/roadmap.md").read_text(encoding="utf-8")
for candidate in {"OpenRouter", "LangGraph4j", "Application MCP", "Kibana"}:
    if candidate not in roadmap:
        fail(f"Roadmap is missing the future candidate: {candidate}")

source_roots = [
    ROOT / ".agents/skills/release-evidence/scripts",
    ROOT / "apps/api/src",
    ROOT / "apps/web/src",
    ROOT / "packages/design-tokens/src",
    ROOT / "infra",
    ROOT / "scripts",
]
source_suffixes = {".java", ".js", ".py", ".scss", ".sh", ".sql", ".ts"}
for source_root in source_roots:
    if not source_root.exists():
        continue
    for path in source_root.rglob("*"):
        if (
            not path.is_file()
            or path.suffix not in source_suffixes
            or any(part in IGNORED_PARTS for part in path.parts)
        ):
            continue
        text = path.read_text(encoding="utf-8")
        if "SPDX-License-Identifier: Apache-2.0" not in text:
            fail(f"Missing SPDX header: {path.relative_to(ROOT)}")

standard_config_paths = [
    ROOT / ".dockerignore",
    ROOT / ".editorconfig",
    ROOT / ".env.example",
    ROOT / ".gitattributes",
    ROOT / ".gitignore",
    ROOT / ".codex/config.toml",
]
standard_config_paths.extend((ROOT / ".codex/agents").glob("*.toml"))
for path in standard_config_paths:
    if "SPDX-License-Identifier" in path.read_text(encoding="utf-8"):
        fail(f"Unexpected SPDX header in standard configuration: {path.relative_to(ROOT)}")

ERRORS.extend(find_comment_rule_errors(ROOT, source_roots))

if ERRORS:
    print("Repository verification failed:")
    for error in ERRORS:
        print(f"- {error}")
    sys.exit(1)

print(
    "Repository verification passed: "
    f"{len(found_agents)} agents, {len(found_skills)} skills, "
    f"{len(stories)} stories, and valid local links."
)
