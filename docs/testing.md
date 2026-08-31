# Testing strategy

## Rule

A story is implemented only when its ordered acceptance criteria have focused proof. A release also needs the full supported-path checks for its current scope.

## Repository checks available now

```bash
python3 .agents/skills/release-evidence/scripts/verify_repository.py
python3 -m unittest discover \
  -s .agents/skills/release-evidence/scripts \
  -p 'test_*.py'
```

These checks need only Python 3 and do not install project dependencies.

## API checks

The API source includes focused configuration, brand-resource, HTTP-contract, safe-error, persistence-lifecycle, health, and ArchUnit tests. Unit and web-slice tests do not require an external service. The lifecycle integration test uses a fresh PostgreSQL 18.6 Testcontainers instance and requires a reachable Docker daemon, but it does not require Docker Compose or Ollama.

The official Wrapper command passes on Java 21 with Docker available:

```bash
cd apps/api
./mvnw verify
```

The PostgreSQL scenario runs Flyway against a clean database, checks the exact application-table set and schema-history row, writes through the outbound adapter, closes and restarts the Spring context against the retained database, and verifies safe readiness and liveness behavior before its run-owned container is removed.

## API test layers

| Layer | Purpose | Typical tool |
| --- | --- | --- |
| Unit | domain rules, task steps, limits, mappings | JUnit and AssertJ |
| Architecture | package and dependency rules | ArchUnit |
| Web slice | validation, Problem Details, SSE contract | Spring MVC test support |
| Persistence | Flyway and JPA mapping against PostgreSQL | Testcontainers |
| File integration | storage, ZIP, parser, cleanup, export | JUnit temp paths and crafted fixtures |
| AI adapter | prompt and schema mapping without a real model | fake or stub chat model |
| Runtime smoke | one real configured Ollama model | supported Compose stack |

A unit test must not require Ollama. A real-model smoke test must not replace deterministic tests.

## Planned web test layers

| Layer | Purpose |
| --- | --- |
| Unit | formatters, reducers, stores, and pure helpers |
| Component | forms, task states, file tree, result views, errors |
| Contract | generated client and API fixture compatibility |
| End-to-end | upload, start, progress, result, preview, and export |
| Screenshot | privacy-safe desktop and mobile product evidence |

Use invented fixture documents. Never capture personal uploads, local file paths, real prompts, secrets, or credentials. A checked Langfuse screenshot may use only synthetic task metadata and must keep prompt, response, and document content tracing off.

Screenshot automation may run through a checked Playwright script, a Playwright test, or Playwright MCP. Release evidence records the actual route, viewport, fixture, and capture method; the project does not require one host environment or operating system.

## Planned core commands

After Release 0.1:

```bash
cd apps/api && ./mvnw verify
cd apps/web && npm ci && npm run lint && npm test -- --run && npm run build
```

Current Compose checks:

```bash
docker compose config
docker compose -f docker-compose.yml -f docker-compose.ollama.yml config
```

After Release 0.5:

```bash
docker compose -f docker-compose.yml -f docker-compose.observability.yml config
docker compose \
  -f docker-compose.yml \
  -f docker-compose.ollama.yml \
  -f docker-compose.observability.yml \
  config
```

Runtime verification also starts each configuration in an isolated Compose project, checks health and loopback ports, and removes only run-owned resources. `NFA-006` adds the real-model smoke path; NFA-005 verifies endpoint routing without downloading a model.

## Release test matrix

| Area | 0.1 | 0.2 | 0.3 | 0.4 | 0.5 |
| --- | ---: | ---: | ---: | ---: | ---: |
| Repository policy and links | Required | Required | Required | Required | Required |
| Java unit and architecture | Required | Required | Required | Required | Required |
| Angular unit and build | Required | Required | Required | Required | Required |
| PostgreSQL migration | Required | Required | Required | Required | Required |
| Existing Ollama endpoint | Required | Required | Required | Required | Required |
| Compose-managed Ollama | Required | Required | Required | Required | Required |
| Single-file user path | Smoke | Required | Required | Required | Required |
| Multi-file and ZIP safety | Not present | Not present | Required | Required | Required |
| Documentation generation | Not present | Not present | Not present | Required | Required |
| Observability disabled | Not present | Not present | Not present | Not present | Required |
| Observability enabled | Not present | Not present | Not present | Not present | Required |

## File security fixtures

Release 0.3 tests need safe crafted fixtures for:

- parent traversal and absolute paths;
- duplicate normalized paths;
- too many entries;
- declared and actual expansion over limits;
- oversized single entry;
- symbolic-link metadata;
- encrypted ZIP data;
- nested archive;
- parser failure;
- cleanup after each failure.

Fixtures must stay small in the repository and produce large behavior through controlled metadata or generated test streams.

## AI result fixtures

Each task type needs tests for valid output, missing required fields, excessive list length, malformed JSON, one bounded repair, repair failure, model timeout, and provider outage. The task must not reach `COMPLETED` on invalid output.

## Verification evidence

Each release verification file records exact commands, relevant versions, short results, both Ollama modes, optional observability modes when they exist, screenshots, review findings, and the approved commit hash. Large logs remain separate and linked.
