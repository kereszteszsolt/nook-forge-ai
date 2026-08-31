# Release 0.2: Durable task workflows

## Status

Planned

## Outcome

Replace the Release 0.1 preview with durable workspaces, files, tasks, steps, artifacts, safe single-file intake, asynchronous execution, four task types, live events, and Markdown export.

## Boundaries

- One application process runs the first task executor; no broker or separate worker service is added.
- Inputs are one text goal, one supported file, or two files for comparison.
- No ZIP extraction, multi-file project workspace, RAG, cloud provider, or observability stack is added.
- The Release 0.1 preview path is removed when the durable task API owns the same plan flow.

## Story order

| Story | Title | Status |
| --- | --- | --- |
| [`NFA-008`](stories/NFA-008-add-the-task-workspace-file-and-artifact-model.md) | Add the task, workspace, file, and artifact model | Planned |
| [`NFA-009`](stories/NFA-009-add-safe-single-file-intake-and-text-extraction.md) | Add safe single-file intake and text extraction | Planned |
| [`NFA-010`](stories/NFA-010-add-async-task-execution-and-step-state.md) | Add async task execution and step state | Planned |
| [`NFA-011`](stories/NFA-011-add-document-review-and-task-extraction.md) | Add document review and task extraction | Planned |
| [`NFA-012`](stories/NFA-012-add-plan-creation-and-document-comparison.md) | Add plan creation and document comparison | Planned |
| [`NFA-013`](stories/NFA-013-add-typed-api-contracts-and-live-task-events.md) | Add typed API contracts and live task events | Planned |
| [`NFA-014`](stories/NFA-014-build-the-task-workspace-in-angular.md) | Build the task workspace in Angular | Planned |
| [`NFA-015`](stories/NFA-015-export-markdown-results-and-close-the-release.md) | Export Markdown results and close the release | Planned |

## Delivery rule

Work on the first planned story whose dependencies are implemented. Each story needs separate plan, implementation, and commit approval. A later story may not enter the same implementation commit.

## Plans and proof

- [Implementation plan](implementation-plan.md)
- [Verification record](verification.md)
