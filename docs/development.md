# Development rules

## Current state

The verified Spring Boot API shell and Maven Wrapper now exist under `apps/api`. The Angular project remains planned for `NFA-003`.

## Native tool ownership

```text
apps/api     Maven Wrapper
apps/web     npm and Angular CLI
root         Docker Compose and repository audit
```

No story may hide all work behind one opaque wrapper command. The README may add a convenience command only after the native commands remain documented.

## Java rules

- Use Java 21 and the Maven Wrapper.
- Use package-by-feature boundaries under `io.nookforge`.
- Use constructor injection; do not use field injection.
- Use validated `@ConfigurationProperties`; do not read environment variables in services.
- Keep controllers thin and return API records, not JPA entities.
- Keep transactions in application services or explicit persistence operations.
- Use Java records for immutable transport or result values when they fit.
- Keep LangChain4j and Ollama classes inside AI adapters or configuration.
- Avoid Lombok, MapStruct, and generic base classes unless an approved story proves a need.
- Use Flyway for every schema change and do not edit an applied migration.
- Use Spring `ProblemDetail` for stable HTTP errors.
- Use ArchUnit for dependency rules.

The `NFA-002` build pins Spring Boot 4.1.1, compiler 3.15.0, Surefire 3.5.5, Enforcer 3.6.3, Site 3.22.0, Spotless 3.9.0 with google-java-format 1.36.0, JaCoCo 0.8.15, Resources 3.5.0, ArchUnit 1.5.0, and Maven 3.9.16 as the Wrapper target. The build checks Java 21, plugin versions, dependency convergence, formatting, tests, architecture, and line coverage.

## Planned Angular rules

- Use the Angular CLI and strict TypeScript.
- Use standalone components and route-level feature boundaries.
- Use typed reactive forms for task input.
- Keep REST and SSE access behind the core API boundary.
- Keep feature state in feature services or stores; do not add NgRx without a story.
- Use repository design tokens and avoid hard-coded colors or spacing in components.
- Inspect a user-supplied Penpot design through MCP before planning when the connection is available.
- Keep Penpot writes inside approved story scope and verify them against Angular output.
- Capture deterministic Playwright screenshots for current README and user-guide paths.
- Use top-to-bottom Mermaid flowcharts in maintained documentation by default.
- Sanitize Markdown and disable raw HTML by default.
- Keep keyboard, focus, label, contrast, overflow, and reduced-motion behavior tested.
- Use one UI primitive strategy and do not mix component libraries.

Exact Angular and test-runner versions are locked by `NFA-003` after supported tool checks.

## Local development

The verified API commands are:

```bash
# API
cd apps/api
./mvnw spring-boot:run
```

```bash
# Web after NFA-003
cd apps/web
npm ci
npm start
```

PostgreSQL and Ollama may run natively or through the documented Compose services. The web uses a same-origin or development proxy for `/api` and task events.

## Branch and story scope

One branch or worktree should contain one active story. Do not mix a later story, broad formatting, dependency upgrades, and unrelated cleanup into the same commit.

## Comments

Use names and structure first. A normal comment has at most three short sentences. A Javadoc, JSDoc, or docstring has at most five short sentences.

Plans, story text, old behavior notes, proof, and logs belong in Markdown, not source comments.

## Generated files

Generated OpenAPI clients, token CSS, build output, coverage, and lock data follow their owning tool rules. Generated source is not hand-edited.

The repository must say which generated files are committed and how to reproduce them before a story adds them.
