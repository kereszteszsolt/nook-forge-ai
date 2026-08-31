# ADR-006: Keep design tokens in the repository

- Status: Accepted
- Date: 2026-08-31

## Context

Nook Forge needs a professional and consistent Angular interface. Penpot can support design work through MCP, but runtime builds must not depend on a remote design service. Token values also need normal code review and version history.

## Decision

Keep approved semantic design tokens in `packages/design-tokens`. Generate CSS variables and any theme bridge from that source.

When the user supplies a Penpot design link for an active UI story and MCP is available, Codex must inspect the focused design before planning. Penpot writes require separate plan and implementation approvals, and the final handoff must be checked against repository tokens, Angular output, and Playwright screenshots.

## Consequences

- The app can build without Penpot.
- The repository remains the runtime source of truth for design tokens.
- Penpot provides editable design context and real handoff identifiers.
- A missing MCP connection stays visible and blocks claims of design synchronization.
- Design, code, screenshots, and commit approval require separate proof steps.
- Token drift needs repository checks and explicit review.
