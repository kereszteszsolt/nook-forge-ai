# Release 0.4: Documentation Forge

## Status

Planned

## Outcome

Analyze software project files as data and create evidence-aware proposed documentation that a user can inspect and export without changing the original project.

## Boundaries

- Nook Forge does not run, build, test, install, or import an uploaded project.
- Generated claims are marked as observed, inferred, or unknown.
- Source projects stay read-only; augmented copies are new artifacts.
- Verified source screenshots may be reused, but the uploaded project is never started for new captures.
- Generated Mermaid flowcharts use a top-to-bottom layout by default.
- No Git write, application MCP, cloud provider, or autonomous code edit is added.

## Story order

| Story | Title | Status |
| --- | --- | --- |
| [`NFA-024`](stories/NFA-024-detect-project-structure-and-observed-facts.md) | Detect project structure and observed facts | Planned |
| [`NFA-025`](stories/NFA-025-generate-a-readme-and-project-overview.md) | Generate a README and project overview | Planned |
| [`NFA-026`](stories/NFA-026-generate-architecture-and-configuration-guides.md) | Generate architecture and configuration guides | Planned |
| [`NFA-027`](stories/NFA-027-generate-development-and-user-guides.md) | Generate development and user guides | Planned |
| [`NFA-028`](stories/NFA-028-validate-facts-and-build-safe-documentation-bundles.md) | Validate facts and build safe documentation bundles | Planned |
| [`NFA-029`](stories/NFA-029-build-documentation-preview-and-close-the-release.md) | Build documentation preview and close the release | Planned |

## Delivery rule

Work on the first planned story whose dependencies are implemented. Each story needs separate plan, implementation, and commit approval. A later story may not enter the same implementation commit.

## Plans and proof

- [Implementation plan](implementation-plan.md)
- [Verification record](verification.md)
