# Polyglot monorepo

## Decision

Use one repository with one Spring Boot application, one Angular application, shared brand and design-token contracts, local infrastructure, and release documentation. Do not add Nx or Turborepo.

```text
nook-forge-ai/
├── apps/
│   ├── api/                     # Java 21, Spring Boot, Maven Wrapper
│   └── web/                     # Angular, TypeScript, npm
├── packages/
│   ├── brand/                   # stable product and technical identity
│   └── design-tokens/           # approved token source and generators
├── infra/
│   ├── docker/                  # service config owned by Compose stories
│   ├── postgres/                # database init and support files
│   └── observability/           # optional Release 0.5 provisioning
├── docs/
├── .agents/
├── .codex/
├── docker-compose.yml           # added by NFA-005
├── docker-compose.ollama.yml    # added by NFA-005
└── .env.example
```

## Build ownership

| Area | Owner | Native command shape |
| --- | --- | --- |
| Java API | Maven Wrapper in `apps/api` | `./mvnw verify` |
| Angular web | npm and Angular CLI in `apps/web` | `npm run build` |
| Local runtime | Docker Compose at repository root | `docker compose ...` |
| Design tokens | checked JSON plus a small generator | web build and focused token check |
| Repository policy | dependency-free Python audit | `python3 .../verify_repository.py` |

Root scripts may call these commands in order. They must not make the underlying tool invisible or require a second monorepo framework.

## Why not one Maven build for everything

The Angular app should keep its normal npm lifecycle, lock file, tests, and development server. Maven should not become a package manager for Node dependencies. A production image may still build both apps in separate Docker stages.

## Why not Nx or Turborepo

There are only two apps and one small token package. Adding a second task graph would increase configuration and upgrade work without solving a current need. This decision can be reviewed only when repository scale creates a measured problem.

## Java module choice

The API starts as one Maven module and one deployable Spring Boot process. Feature packages and ArchUnit rules enforce the modular boundaries. More Maven modules are allowed only when a real build or ownership boundary appears.

## Shared contracts

- Product identity lives in `packages/brand/brand.json`.
- Design tokens live in `packages/design-tokens`, with checked CSS generated for the Angular build.
- The backend publishes OpenAPI.
- The frontend consumes one typed API client generated or checked from that contract.
- Java domain classes are not shared as source with TypeScript.
