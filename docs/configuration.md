# Configuration and Ollama modes

## Source and precedence

Runtime settings use validated Spring `@ConfigurationProperties`. Docker Compose reads repository-root `.env` values and passes the supported variables to services.

```text
checked application defaults
        ↓
application profile values
        ↓
environment variables
        ↓
command-line override when documented
```

Application services do not call `System.getenv` or parse `.env` files.

## Current API settings

`NFA-002` introduces one non-secret setting:

| Property | Default | Rule |
| --- | --- | --- |
| `nookforge.api.public-base-url` | `http://127.0.0.1:8080` | absolute, hierarchical HTTP or HTTPS URI with a host |

Invalid URI syntax, a non-HTTP scheme, an opaque URI, or a missing host fails configuration binding at startup. The system information response does not expose this setting.

The API server binds to `127.0.0.1` by default through `server.address`.

### Ollama-only AI settings

`NFA-005` adds validated settings under `nookforge.ai` without adding a provider client or model call:

| Property | Environment variable | Default | Rule |
| --- | --- | --- | --- |
| `provider` | `AI_PROVIDER` | `ollama` | only `ollama` |
| `base-url` | `OLLAMA_BASE_URL` | `http://127.0.0.1:11434` | absolute HTTP or HTTPS URI with a host and no credentials, query, or fragment |
| `model` | `OLLAMA_MODEL` | `llama3.1:8b` | nonblank |
| `request-timeout` | `OLLAMA_REQUEST_TIMEOUT` | `120s` | positive duration |

`NFA-006` owns the LangChain4j adapter, configured chat model, and first real model request.

### PostgreSQL

`NFA-004` adds validated settings under `nookforge.database`:

| Property | Local default | Container default | Rule |
| --- | --- | --- | --- |
| `host` | `127.0.0.1` | `postgres` | hostname characters only |
| `port` | `5433` | `5432` | 1 through 65535 |
| `name` | `nookforge` | `nookforge` | letters, digits, and underscores |
| `username` | `nookforge` | `nookforge` | letters, digits, and underscores |
| `password` | none | none | required and nonblank |

The profiles map these values from `POSTGRES_HOST`, `POSTGRES_PORT`, `POSTGRES_DB`, `POSTGRES_USER`, and `POSTGRES_PASSWORD`. The application builds the JDBC URL after validation, and no checked application property supplies a password default.

Flyway runs the checked `db/migration` files before JPA validation. Hibernate does not create or update tables, Spring SQL initialization is disabled, and Flyway clean is disabled.

## Local environment files

```bash
cp .env.example .env
```

Rules:

- `.env.example` is committed and contains no real secret.
- `.env` is local and ignored.
- `.env.local`, `.env.dev`, and secret variants are not supported by default.
- production-like secrets come from the runtime environment or a later approved secret store.
- logs and error payloads never print passwords or API keys.

Current Compose variables are `COMPOSE_PROJECT_NAME`, `BIND_ADDRESS`, `WEB_PORT`, `API_PORT`, `POSTGRES_PORT`, `OLLAMA_CONTAINER_PORT`, the three `POSTGRES_*` values, and the four Ollama-only AI values above. The file also retains empty or disabled Langfuse audit placeholders; their runtime integration and full service variables remain owned by Release 0.5.

## Option A: existing Ollama endpoint

Base Compose starts the web, API, and PostgreSQL services. It passes `OLLAMA_BASE_URL` to the API container and adds the host-gateway route needed for a native endpoint.

Examples:

```env
# Native Ollama on the Docker host
OLLAMA_BASE_URL=http://host.docker.internal:11434
```

```env
# Ollama at a container name reachable from the API network
OLLAMA_BASE_URL=http://ollama:11434
```

```env
# Ollama on another LAN host
OLLAMA_BASE_URL=http://192.168.1.20:11434
```

The base Compose file adds the Linux host-gateway mapping needed for `host.docker.internal`. Another container must share a reachable network or expose a host-accessible address; a remote endpoint remains the user's responsibility and should be protected by their network rules.

Command:

```bash
docker compose up --build
```

## Option B: Nook Forge-owned Ollama container

The optional override adds the official Ollama service, a dedicated model volume, and a host port that does not collide with a native instance.

Command:

```bash
docker compose \
  -f docker-compose.yml \
  -f docker-compose.ollama.yml \
  up --build
```

The API uses the internal service URL `http://ollama:11434`. The host uses `http://localhost:11435` by default.

The service starts with an empty model volume and never pulls a model automatically. `NFA-006` will document the selected model and explicit pull step after the real-model contract passes.

## Loopback defaults

`BIND_ADDRESS=127.0.0.1` keeps the web, API, PostgreSQL, and managed Ollama host ports on loopback by default. Changing it to `0.0.0.0` or another interface exposes an account-free local application to that network and requires user-managed firewall and endpoint protection.

## Current and planned application properties

| Property group | Job |
| --- | --- |
| `nookforge.ai` | implemented provider, model, endpoint, and timeout; schema repair remains planned |
| `nookforge.storage` | workspace root, artifact root, cleanup rules |
| `nookforge.upload` | request and file limits |
| `nookforge.archive` | entry, expansion, path, and depth limits |
| `nookforge.task` | executor size, step timeout, recovery rules |
| `nookforge.observability` | metrics, optional Langfuse tracing, and content opt-in |

Each group needs bean validation and a focused configuration test.


## Optional observability settings

Release 0.5 may use these disabled-by-default client settings after the owning stories verify them:

```env
LANGFUSE_ENABLED=false
LANGFUSE_TRACE_CONTENT=false
LANGFUSE_BASE_URL=http://langfuse:3000
LANGFUSE_PUBLIC_KEY=replace-after-release-0.5
LANGFUSE_SECRET_KEY=replace-after-release-0.5
```

The full self-hosted Langfuse service variables are added only in `NFA-033` after that story pins the supported deployment shape. A missing Langfuse service must not stop the base app or fail a task.

## Profiles

- `local`: host development with local dependencies;
- `container`: Docker Compose service names and paths;
- `test`: reserved for test-only ports and temporary file storage in later stories.

The implemented PostgreSQL lifecycle test supplies fresh Testcontainers coordinates to the local and container profiles rather than storing test credentials in a profile file.

Do not create one profile per developer or commit a personal profile.

## Future providers

OpenRouter and other providers are not configured in `.env.example` until an approved story implements them. Their future keys must remain optional and disabled by default.
