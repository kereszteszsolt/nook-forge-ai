---
name: full-stack-delivery
description: Deliver one approved Nook Forge story across Angular, Spring Boot, PostgreSQL, OpenAPI, Docker, branding, or shared contracts.
---

# Full-stack delivery

1. Read `AGENTS.md`, `docs/story-workflow.md`, and the active story.
2. Confirm plan approval, then ask for separate implementation approval.
3. Follow the acceptance criteria in order.
4. Trace each user action from Angular through HTTP, application services, adapters, persistence, and back.
5. Keep product identity in `packages/brand/brand.json`.
6. Keep web requests behind the typed API boundary.
7. Keep controllers thin and compose concrete services through Spring configuration.
8. Keep JPA entities and provider SDK types out of public and application contracts.
9. Preserve empty, loading, progress, success, partial, failure, retry, and unavailable states.
10. Remove replaced live code and add focused tests.
11. Show checks, then ask for commit approval.
12. After an approved commit, report its hash and ask before push or the next story.

Use comments only when code cannot explain a hard reason. Do not add a state library, broker, second UI kit, or generic framework without an approved story.
