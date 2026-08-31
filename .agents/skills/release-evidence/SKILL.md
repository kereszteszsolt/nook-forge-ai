---
name: release-evidence
description: Maintain Nook Forge story format, approval gates, verification proof, release maps, links, visual evidence, and planning or release archives.
---

# Release evidence

Run the repository audit first:

```bash
python3 .agents/skills/release-evidence/scripts/verify_repository.py
```

For one active story:

1. Confirm the story is the next valid item.
2. Present a plan and record clear plan approval.
3. Ask again and record clear implementation approval.
4. Follow four to eight short criteria in order.
5. Record focused commands and short results in the release `verification.md`.
6. Record Penpot inspection and Playwright evidence when the story owns visual work.
7. Keep large logs outside the story and link them when needed.
8. Propose one commit message and record clear commit approval.
9. Record the commit hash after it succeeds.
10. Ask before push or the next story.

`Implemented` is a tested claim. Story prose stays short, source comments stay short, planned features stay marked as planned, and no screenshot or design ID is invented.
