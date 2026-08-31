# Nook Forge design and Penpot handoff

## Planning state

No final visual system or Penpot board is claimed in this archive. `NFA-003` creates the first repository token set, and `NFA-007` records the first approved Penpot handoff and full-stack screen evidence.

## Source contracts

- Product identity: `packages/brand/brand.json`
- Runtime token source after `NFA-003`: `packages/design-tokens/tokens.json`
- Implemented interface after `NFA-003`: `apps/web/src`
- Browser evidence after `NFA-007`: `docs/screenshots`
- Product flow: `docs/design/ui-brief.md`
- Handoff record: `docs/design/penpot-handoff-template.md`
- Visual evidence rules: `docs/visual-documentation.md`

## Link-triggered Penpot workflow

When the user supplies a Penpot design link for the active UI story and Penpot MCP is available, Codex must inspect the focused design before it writes the implementation plan.

The workflow is:

```text
user supplies a Penpot link
             ↓
read-only MCP inspection
             ↓
record real pages, boards, components, and tokens
             ↓
write the story plan
             ↓
USER APPROVES PLAN
             ↓
USER APPROVES IMPLEMENTATION
             ↓
small and reversible Penpot or code changes
             ↓
reinspect Penpot and verify repository tokens
             ↓
implement or verify Angular output
             ↓
capture Playwright screenshots
             ↓
review and request commit approval
```

Start with read-only actions. Before any Penpot write, describe the exact intended change and keep it inside the approved story.

If the link is supplied but MCP is unavailable, Codex must report the missing access. It must not invent design structure, IDs, token values, exports, or synchronization proof.

## Handoff record

Create a story-owned copy of [`penpot-handoff-template.md`](penpot-handoff-template.md) and record only real values:

```text
Team
Project
File
Page
Board
Board ID
Relevant component or object IDs
Repository token mapping
Checked export files
Playwright screenshot files
```

The MCP key and a server URL that contains an authentication token must never enter the repository, logs, screenshots, or release evidence.

## Direction of truth

The repository owns the runtime token contract, checked Angular implementation, and release proof. Penpot owns editable design exploration and handoff context. Synchronization is a deliberate story step, not an automatic runtime or build dependency.

## Required states

Every feature plan covers:

- first-run and empty state;
- loading and model check;
- file selection and validation;
- queued and step progress;
- success and partial result;
- task and provider failure;
- retry and unavailable service;
- desktop and narrow mobile layout;
- keyboard, focus, labels, contrast, and reduced motion.

## Screenshot rules

Use invented fixture data. Keep personal files, prompts, responses, local paths, host names, tokens, and private trace content out of checked images.

Review each export before commit. Penpot exports and Playwright screenshots serve different proof and must not be mislabeled. Follow [`docs/visual-documentation.md`](../visual-documentation.md) for capture and Mermaid rules.
