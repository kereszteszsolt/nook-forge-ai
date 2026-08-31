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

## Current API shell setting

`NFA-002` introduces one non-secret setting:

| Property | Default | Rule |
| --- | --- | --- |
| `nookforge.api.public-base-url` | `http://127.0.0.1:8080` | absolute, hierarchical HTTP or HTTPS URI with a host |

Invalid URI syntax, a non-HTTP scheme, an opaque URI, or a missing host fails configuration binding at startup. The system information response does not expose this setting.

The API server binds to `127.0.0.1` by default through `server.address`.

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

## Option A: existing Ollama endpoint

After `NFA-005`, base Compose starts the web, API, and PostgreSQL services. The API connects to `OLLAMA_BASE_URL`.

Examples:

```env
# Native Ollama on the Docker host
OLLAMA_BASE_URL=http://host.docker.internal:11434
```

```env
# Ollama in another reachable container or network
OLLAMA_BASE_URL=http://ollama:11434
```

```env
# Ollama on another LAN host
OLLAMA_BASE_URL=http://192.168.1.20:11434
```

The base Compose file must add the Linux host-gateway mapping needed for `host.docker.internal`. A remote endpoint remains the user's responsibility and should be protected by their network rules.

Planned command:

```bash
docker compose up --build
```

## Option B: Nook Forge-owned Ollama container

The optional override adds the official Ollama service, a dedicated model volume, and a host port that does not collide with a native instance.

Planned command:

```bash
docker compose \
  -f docker-compose.yml \
  -f docker-compose.ollama.yml \
  up --build
```

The API uses the internal service URL `http://ollama:11434`. The host uses `http://localhost:11435` by default.

Model pull commands are documented only after the image and model contract pass in `NFA-005` and `NFA-006`.

## Loopback defaults

Supported local ports bind to `127.0.0.1` by default. A user may choose another bind address in `.env`, but the docs must explain the security effect.

## Planned application properties after the API shell

| Property group | Job |
| --- | --- |
| `nookforge.ai` | provider, model, endpoint, timeout, schema repair limit |
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

Planned profiles:

- `local`: host development with local dependencies;
- `container`: Docker Compose service names and paths;
- `test`: test-only ports and temporary storage.

Do not create one profile per developer or commit a personal profile.

## Future providers

OpenRouter and other providers are not configured in `.env.example` until an approved story implements them. Their future keys must remain optional and disabled by default.
