# Product vision

## Problem

People keep useful facts in notes, letters, offers, bills, project folders, and ZIP archives. A normal chat box makes them copy text by hand and gives little proof about what was used. Cloud tools may also be a poor fit for private files.

## Product

Nook Forge is a local-first workbench that turns user files into structured work. A user creates a workspace, adds one or more files, chooses a task, watches the steps, and exports a result.

The product is not a general chat clone. It focuses on repeatable tasks with typed results, visible source scope, safe file handling, and durable artifacts.

## Core jobs

- Review a document for gaps, risks, and unclear text.
- Extract tasks, dates, decisions, and open questions.
- Turn a goal or note set into a phased plan.
- Compare two or more documents without choosing for the user.
- Summarize a folder or project archive.
- Find facts that conflict across files.
- Build proposed project documentation from observed repository facts.

## Local-first promise

Ollama is the only provider planned for Releases 0.1 through 0.5. The browser, API, database, files, and models can run on the user's machine. The base app has no account requirement and no silent cloud fallback.

A later cloud provider must be explicit, optional, disabled by default, and clear about which content leaves the machine.

## Product principles

1. Original input stays read-only.
2. Generated work is easy to inspect and export.
3. The app tells the user which files it used or skipped.
4. Observed facts, AI inferences, and unknowns are not mixed.
5. Long jobs show clear steps and failure points.
6. Local setup stays useful without optional monitoring tools.
7. Safety and limits are part of the feature, not a later patch.

## Non-goals through Release 0.5

- General autonomous agents.
- A workflow designer.
- Multi-user accounts and permissions.
- Automatic edits to the user's source archive.
- RAG or vector search.
- Cloud model providers.
- MCP tools in the running product.
- LangGraph4j orchestration.
- Elasticsearch, Kibana, Kubernetes, or Helm.

## Success signals

A release is useful when a person can complete the planned task from the Angular UI, understand the progress, inspect a structured result, and export an artifact without sending data to a cloud model. Engineering proof must show the same behavior in both supported Ollama deployment modes.
