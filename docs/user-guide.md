# User guide

## Planning status

Nook Forge AI is not implemented in this archive. This file defines the future user-guide structure and must not be treated as working instructions.

`NFA-007` adds the first verified start and plan-preview path. Later release-closing stories extend this guide only after their UI and API paths pass tests.

## Planned guide structure

1. choose an Ollama deployment mode;
2. copy `.env.example` to `.env`;
3. start the supported local stack;
4. confirm provider and model health;
5. create a task;
6. add one or more supported files;
7. follow task progress;
8. review structured results and evidence;
9. export Markdown or ZIP artifacts;
10. enable optional observability when needed.

## Screenshot contract

Each implemented section includes a current privacy-safe screenshot when the image helps the task. Captures use invented fixture data and deterministic Playwright automation.

The screenshot index records the route, viewport, fixture, method, related story, and review result. No guide image may expose a real file, prompt, path, machine name, secret, or private Langfuse content.

## Diagram contract

Startup, task, and export diagrams use top-to-bottom Mermaid flow by default. Wide flows are split into smaller diagrams so GitHub and narrow screens remain readable.

See [visual documentation](visual-documentation.md) and [screenshot evidence](screenshots/README.md).
