# Technology stack

This page lists the approved direction. Package files, lock files, wrappers, image digests, and release verification become the source of truth for exact versions after implementation.

## Core application

| Tool | Planned job | Status in this archive |
| --- | --- | --- |
| Java 21 | API runtime and domain code | Approved direction |
| Spring Boot | HTTP, configuration, persistence integration, Actuator | Planned in `NFA-002` |
| LangChain4j | AI Services, prompts, structured outputs, Ollama integration | Planned in `NFA-006` |
| Maven Wrapper | Java build and pinned build entry | Planned in `NFA-002` |
| Angular | task and workspace user interface | Planned in `NFA-003` |
| TypeScript strict mode | web contracts and checks | Planned in `NFA-003` |
| PostgreSQL | task, workspace, file, step, and artifact metadata | Planned in `NFA-004` |
| Flyway | schema history and repeatable local setup | Planned in `NFA-004` |
| Ollama | default and only model provider through 0.5 | Planned in `NFA-005` and `NFA-006` |
| Docker Compose | supported local runtime | Planned in `NFA-005` |

## File and document work

| Tool family | Planned job | Selection rule |
| --- | --- | --- |
| Java ZIP APIs plus safe wrappers | archive scan and streamed extraction | no unsafe convenience extraction |
| Text and structure parsers | TXT, Markdown, config, code, PDF, and DOCX | smallest supported set after fixture tests |
| SHA-256 | input and artifact integrity | standard JDK implementation |
| Markdown renderer and sanitizer | safe result preview | raw HTML off by default |

A broad parser library may be used only if its dependency and security cost is reviewed in `NFA-019`.

## API and quality

| Tool | Planned job |
| --- | --- |
| OpenAPI | typed backend contract and web client input |
| Spring Problem Details | stable HTTP error format |
| JUnit and AssertJ | Java tests |
| ArchUnit | package boundary tests |
| Testcontainers | PostgreSQL and runtime integration |
| Playwright | end-to-end flows and privacy-safe screenshots |
| formatter and lint plugins | stable Java and TypeScript source style |

Do not install two tools for the same job without a measured reason.

## Observability

| Tool | Planned job | Deployment rule |
| --- | --- | --- |
| Micrometer | app and task metrics | in API runtime |
| Prometheus | metric collection | optional Compose override |
| Langfuse | local AI-task trace review | optional Compose override |
| Grafana | provisioned metric dashboards | optional Compose override |

`NFA-032` selects the smallest currently supported Java-to-Langfuse integration and keeps it inside an adapter. A collector or separate general tracing platform is not planned.

Kibana and Elasticsearch are not part of Release 0.5.

## Design and documentation

| Tool or format | Planned job |
| --- | --- |
| Penpot MCP | link-triggered design inspection and approved design updates |
| Repository design tokens | reviewed runtime design contract |
| Mermaid | vertical-first architecture and workflow diagrams |
| Playwright | deterministic product and user-guide screenshots |

## Future candidates

| Candidate | Entry condition |
| --- | --- |
| OpenRouter or another model provider | explicit opt-in, secrets, no fallback, privacy proof |
| LangGraph4j | real branching, checkpoint, resume, or human node need |
| Application MCP | allowlisted capabilities, read-only default, write approval, audit |
| Elasticsearch and Kibana | a measured searchable-log need beyond local JSON logs |

CrewAI, TruLens, LangSmith, Kubernetes, and Helm are not planned for this repository through Release 0.5.

## Version policy

- Pin Java dependencies through a reviewed Spring Boot and LangChain4j dependency strategy.
- Commit Maven Wrapper metadata and the npm lock file.
- Use supported LTS or stable runtime versions selected by the owning story.
- Pin container images to reviewed release tags or digests.
- Do not use dynamic dependency versions or `latest` images in release proof.
- Record exact versions in this page only after they exist in lock and build files.
