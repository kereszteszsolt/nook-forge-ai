# ADR-003: Keep AI behind task-specific ports

- Status: Accepted
- Date: 2026-08-31

## Context

Nook Forge starts with Ollama and LangChain4j. OpenRouter or another provider may be useful later. Binding use cases to one model client would spread provider choices through the product.

## Decision

Application services call task-specific AI ports. LangChain4j adapters own prompts, structured output, repair, and model calls. Spring configuration creates the current Ollama chat model.

## Consequences

The domain and application layers do not import LangChain4j or Ollama. A later provider can change adapter wiring while task contracts stay stable. Ollama remains the only provider through Release 0.5.

No provider fallback is allowed. A later cloud provider needs explicit selection, secrets, privacy notice, and its own approved story.
