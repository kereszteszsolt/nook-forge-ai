---
name: file-workspace-safety
description: Implement or review one approved Nook Forge upload, ZIP, extraction, workspace, storage, document parsing, or export story.
---

# File workspace safety

- Store files under generated server paths.
- Keep original names as metadata only.
- Validate size, count, media type, checksum, path depth, and archive expansion.
- Reject path traversal, absolute paths, links, devices, encrypted archives, and unsupported nested archives.
- Never execute uploaded code, scripts, macros, or binaries.
- Ignore `.git`, dependency folders, build output, caches, and known binary data.
- Keep original uploads read-only.
- Extract into an isolated temporary root and clean it on success or failure.
- Track each accepted, ignored, and rejected file in a workspace manifest.
- Export generated artifacts separately and never replace the input archive in place.
- Add unit tests for path rules and integration tests for archive bombs and cleanup.

Ask for plan approval, implementation approval, and commit approval as separate gates.
