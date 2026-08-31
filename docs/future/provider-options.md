# Candidate future option: OpenRouter and other providers

## Status

Candidate only. Ollama is the sole supported provider through Release 0.5.

## Entry conditions

A future provider release must keep the current task-specific application ports. It adds provider configuration and one LangChain4j-backed adapter path without changing task domain rules.

The first provider story should support one provider per deployment. Runtime fallback is not allowed.

## Required behavior

- explicit `AI_PROVIDER` value;
- environment-only secret configuration;
- startup validation with safe errors;
- clear provider and model label in the UI;
- a warning that selected content may leave the machine;
- no request before the user enables the cloud provider;
- provider-specific timeout, rate-limit, and failure mapping;
- cost and token metadata when the provider returns it;
- privacy-safe tests and documentation;
- unchanged source-file immutability and result schemas.

## OpenRouter

OpenRouter is a possible first cloud adapter because a later LangChain4j adapter may use an OpenAI-compatible API shape. The owning story must verify the current official contract and supported LangChain4j integration at implementation time.

No OpenRouter variable belongs in `.env.example` before that story is implemented.
