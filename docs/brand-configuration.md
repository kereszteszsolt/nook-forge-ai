# Brand configuration

Canonical product and technical identity lives in [`packages/brand/brand.json`](../packages/brand/brand.json). The repository audit validates the stable values.

| Field | Value |
| --- | --- |
| Product | `Nook Forge` |
| Extended product | `Nook Forge AI` |
| Tagline | `Turn local files into useful work.` |
| Repository | `nook-forge-ai` |
| Java package | `io.nookforge` |
| Docker project | `nookforge` |
| Story prefix | `NFA` |

Application stories may generate Java and TypeScript constants from this contract or read a checked build artifact. They must not create different product names in each app.

`NFA-003` establishes the first semantic visual tokens and generates the Angular brand constant from this contract. `NFA-007` owns the first final Penpot handoff and browser evidence.

## GitHub repository metadata

Recommended About text:

> Nook Forge AI is a local-first workspace for analyzing files and ZIP archives, comparing documents, extracting tasks, and generating structured plans, reports, and project documentation with Angular, Spring Boot, LangChain4j, Ollama, and Docker.

Recommended core topics after the owning implementation exists:

```text
java, spring-boot, angular, typescript, langchain4j, ollama,
local-ai, generative-ai, document-analysis, document-processing,
structured-output, zip-processing, docker, docker-compose,
postgresql, flyway, modular-monolith, hexagonal-architecture,
monorepo, portfolio-project
```

Add these only after Release 0.5 implements and verifies them:

```text
langfuse, prometheus, grafana
```

Add `mcp`, `langgraph4j`, `openrouter`, `elasticsearch`, or `kibana` only after a later release implements the matching feature. Repository metadata must not advertise candidate technology as current behavior.
