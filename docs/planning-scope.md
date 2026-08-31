# Planning scope

## Purpose

This archive prepares the `nook-forge-ai` repository for story-driven implementation. It adapts the proven Cite Nook release, refactor, evidence, short-comment, feature-boundary, and ports-and-adapters discipline to a new Java and Angular product.

## Included

- product name and technical identifiers;
- polyglot monorepo decision;
- modular Spring Boot and Angular target architecture;
- Ollama-only provider plan and future provider boundary;
- existing-Ollama and Compose-managed Ollama modes;
- `.env.example` and `.env` safety rules;
- file, multi-file, ZIP, documentation, and artifact plans;
- active Penpot MCP handoff rules for supplied design links;
- screenshot, user-guide, and vertical Mermaid rules;
- optional Langfuse, Prometheus, and Grafana plan;
- five release maps and 36 ordered stories;
- three Codex roles and six repository skills;
- repository audit, comment audit, and tests;
- architecture decisions and documentation templates.

## Implemented foundation

- the repository and story-governance baseline from `NFA-001`;
- the Java 21 and Spring Boot API shell from `NFA-002`;
- the Angular shell and repository design-token flow from `NFA-003`;
- PostgreSQL profiles, Flyway V1 schema history, and the installation metadata adapter from `NFA-004`.

## Not included

No Docker Compose service, LangChain4j adapter, Ollama call, task or workspace schema, file parser, ZIP extractor, Langfuse service, Prometheus config, Grafana dashboard, product screenshot, or application MCP code is implemented.

Plan, implementation, commit, push, and next-story approvals remain separate.

## Future labels

OpenRouter, other providers, LangGraph4j, application MCP, Elasticsearch, and Kibana are marked as candidates only. Helm and Kubernetes are excluded from the current direction.

## Archive verification

The planning archive must pass:

```bash
python3 .agents/skills/release-evidence/scripts/verify_repository.py
python3 -m unittest discover \
  -s .agents/skills/release-evidence/scripts \
  -p 'test_*.py'
```

The ZIP receives one external SHA-256 file after all repository checks pass. The repository does not contain an internal file-hash manifest.
