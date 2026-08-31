# Technology stack

This page lists the approved direction. Package files, lock files, wrappers, image digests, and release verification become the source of truth for exact versions after implementation.

## Core application

| Tool | Planned job | Status in this archive |
| --- | --- | --- |
| Java 21 | API runtime and domain code | Verified by the `NFA-002` build |
| Spring Boot 4.1.1 | HTTP, configuration, persistence integration, Actuator | Verified API shell |
| LangChain4j | AI Services, prompts, structured outputs, Ollama integration | Planned in `NFA-006` |
| Maven Wrapper 3.3.4 / Maven 3.9.16 | Java build and pinned build entry | Generated and verified in `NFA-002` |
| Angular 22.1.4 | standalone task and workspace user-interface shell | Verified by the `NFA-003` build |
| TypeScript 6.0.3 strict mode | web contracts and checks | Verified by `NFA-003` |
| PostgreSQL 18.6 | durable foundation metadata | Verified by the `NFA-004` lifecycle test |
| Flyway 12.4.0 | schema history and forward-only migrations | Verified by `NFA-004` |
| PostgreSQL JDBC 42.7.13 | API database connection | Managed by Spring Boot 4.1.1 and verified by `NFA-004` |
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
| JUnit 6 and AssertJ | Java tests |
| ArchUnit 1.5.0 | package boundary tests |
| Testcontainers 2.0.5 | PostgreSQL and runtime integration |
| Playwright | end-to-end flows and privacy-safe screenshots |
| Spotless 3.9.0 and google-java-format 1.36.0 | stable Java source style |

The API build also pins Maven Compiler Plugin 3.15.0, Surefire 3.5.5, Enforcer 3.6.3, Site Plugin 3.22.0, JaCoCo 0.8.15, and Resources Plugin 3.5.0. These versions appear in `apps/api/pom.xml`, while the exact build proof is recorded in the release verification file.

The web build pins Node 24.20.0, npm 11.19.0, Angular CLI and build tooling 22.1.6, Vitest 4.1.11, Angular ESLint 22.2.0, and ESLint 10.9.1. Exact direct and transitive npm versions are recorded in `apps/web/package-lock.json`.

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
