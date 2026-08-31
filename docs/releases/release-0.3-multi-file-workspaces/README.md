# Release 0.3: Multi-file workspaces and safe archives

## Status

Planned

## Outcome

Let users work with many files or one ZIP archive, inspect a complete manifest, run bounded cross-file tasks, and export one generated result bundle.

## Boundaries

- Nested archives are detected but not extracted.
- Uploaded code is parsed as text and never run.
- Original files and archives remain read-only.
- No RAG, vector database, OCR, source patching, cloud provider, or application MCP is added.

## Story order

| Story | Title | Status |
| --- | --- | --- |
| [`NFA-016`](stories/NFA-016-add-multi-file-workspace-manifests.md) | Add multi-file workspace manifests | Planned |
| [`NFA-017`](stories/NFA-017-add-safe-zip-intake-and-isolated-extraction.md) | Add safe ZIP intake and isolated extraction | Planned |
| [`NFA-018`](stories/NFA-018-classify-files-and-apply-project-ignore-rules.md) | Classify files and apply project ignore rules | Planned |
| [`NFA-019`](stories/NFA-019-extract-supported-document-and-code-content.md) | Extract supported document and code content | Planned |
| [`NFA-020`](stories/NFA-020-build-bounded-context-for-many-files.md) | Build bounded context for many files | Planned |
| [`NFA-021`](stories/NFA-021-add-workspace-summary-and-consistency-review.md) | Add workspace summary and consistency review | Planned |
| [`NFA-022`](stories/NFA-022-build-the-multi-file-and-archive-user-flow.md) | Build the multi-file and archive user flow | Planned |
| [`NFA-023`](stories/NFA-023-export-a-result-bundle-and-close-the-release.md) | Export a result bundle and close the release | Planned |

## Delivery rule

Work on the first planned story whose dependencies are implemented. Each story needs separate plan, implementation, and commit approval. A later story may not enter the same implementation commit.

## Plans and proof

- [Implementation plan](implementation-plan.md)
- [Verification record](verification.md)
