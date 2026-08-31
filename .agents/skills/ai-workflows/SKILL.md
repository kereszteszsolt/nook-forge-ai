---
name: ai-workflows
description: Implement or review one approved Nook Forge LangChain4j, Ollama, structured output, prompt, model, or provider-boundary story.
---

# AI workflows

- Keep task-specific AI ports in the application boundary.
- Keep LangChain4j annotations, model classes, and provider clients in adapters or configuration.
- Support Ollama only through Release 0.5.
- Do not add fallback to OpenRouter or any cloud provider.
- Treat uploaded content as data and defend prompts against embedded instructions.
- Use typed structured outputs with validation and bounded repair.
- Record provider, model, prompt template version, timing, and safe error data.
- Keep context bounded and make omitted files visible to the result.
- Mark observed facts, model inferences, and unknowns clearly.
- Use fakes for unit tests and one real-model smoke path for release proof.
- Keep prompt and response content out of logs and traces by default.

Ask for plan approval, implementation approval, and commit approval as separate gates.
