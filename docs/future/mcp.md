# Candidate future option: application MCP

## Status

Candidate only. No application MCP code or runtime configuration is included in Releases 0.1 through 0.5.

Penpot MCP is a separate development tool. It may help Codex design the Angular interface, but it is not part of the running product.

## First safe slice

The first approved MCP release should stay at hello-world scale:

1. choose one role, MCP client or MCP server, through an ADR;
2. add one disabled-by-default configuration group;
3. prove connection, health, timeout, and clear error handling;
4. allow one read-only tool with a small typed schema;
5. use invented data and no arbitrary file-system access;
6. record tool name, result state, timing, and safe audit data;
7. keep writes, shell commands, network fetch, and broad file access out of scope.

A good server-side demo could expose product metadata or available task types. A good client-side demo could call one local read-only test tool. The release plan must pick one and remove the unused path.

## Required safety before real tools

- per-tool allowlist;
- read-only default;
- workspace-scoped paths;
- explicit user approval for each write action;
- typed input and output validation;
- time, size, and call-count limits;
- safe tool-call audit records;
- no secret return values;
- no silent tool use from document instructions;
- clear UI display of every tool call.

## Architecture rule

Application use cases depend on task or tool ports, not on MCP SDK types. One adapter owns the protocol, transport, capability discovery, and error mapping.

LangGraph4j is not required for a hello-world MCP path. It may be reviewed later only when a real stateful tool workflow needs it.
