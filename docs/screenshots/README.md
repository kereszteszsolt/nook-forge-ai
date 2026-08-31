# Product screenshot evidence

No product screenshots exist in this planning archive. `NFA-007` adds the first implemented full-stack captures, and later release-closing stories update them when the interface changes.

## Capture rules

- use deterministic Playwright automation with invented fixture data;
- use a repository script, a Playwright test, or Playwright MCP as the active environment allows;
- do not require one IDE, host environment, or operating system in the project contract;
- capture desktop and narrow mobile layouts;
- include key empty, progress, result, and failure states when useful;
- keep personal files, real prompts, responses, paths, host names, keys, and tokens out of images;
- use synthetic trace data when a local Langfuse view is captured;
- review every PNG before commit;
- record the command or method, route, viewport, fixture, and related story;
- keep Penpot exports under `docs/design`, not here.

See [`docs/visual-documentation.md`](../visual-documentation.md) for the full documentation and Mermaid policy.

## Planned folders

```text
release-0.1/
    plan-preview-desktop.png
    plan-preview-mobile.png
    api-unavailable-desktop.png

release-0.2/
    create-task.png
    task-progress.png
    task-result.png
    task-history.png

release-0.3/
    zip-upload.png
    workspace-file-tree.png
    rejected-archive.png
    result-bundle.png

release-0.4/
    documentation-preview.png
    source-facts.png
    validation-errors.png
    documentation-export.png

release-0.5/
    monitoring-overview.png
    langfuse-trace.png
    grafana-dashboard.png
```

Actual file names follow the implemented screens and may change during their owning stories.

## Evidence index template

| Image | Story | Route | Viewport | Fixture | Capture method | Reviewed |
| --- | --- | --- | --- | --- | --- | --- |
| Pending | Pending | Pending | Pending | Pending | Pending | Pending |
