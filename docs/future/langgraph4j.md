# Candidate future option: LangGraph4j

## Status

Candidate only. Releases 0.1 through 0.5 use explicit Java application services and saved task steps.

## Entry conditions

Add LangGraph4j only when a real workflow needs at least one of these features:

- a branch chosen from validated state;
- a checkpoint that can resume later;
- a loop with a strict stop rule;
- a human approval node;
- a durable handoff between tool or model steps.

A list of linear steps or a progress bar is not enough reason.

## First safe slice

The first story should move one existing, well-tested workflow behind one graph adapter. It must preserve the public task schema, result schema, task states, source proof, and Ollama-only provider rule.

The old orchestration path must be removed in the same story. Do not keep a hidden direct path as fallback.

## Verification

Proof needs deterministic graph state tests, branch tests, checkpoint or resume tests when claimed, stop-limit tests, and a real Ollama smoke fixture. The graph library must not enter the domain model.
