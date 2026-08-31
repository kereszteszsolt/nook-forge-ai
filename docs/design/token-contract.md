# Design token contract

## Source

`NFA-003` creates `packages/design-tokens/tokens.json` after the first design plan is approved. The checked JSON file is the runtime source for generated CSS custom properties and any Angular theme bridge.

## Token groups

```text
color
  surface
  text
  border
  action
  status

type
  family
  size
  weight
  lineHeight

space
radius
elevation
motion
breakpoint
layout
```

Use semantic names such as `color.surface.canvas` and `color.status.failed`, not names tied to one hex value or component.

## Output

The token build should create one generated CSS file with stable custom property names:

```css
:root {
  --nf-color-surface-canvas: ...;
  --nf-space-4: ...;
  --nf-radius-control: ...;
}
```

Generated output is not hand-edited. The build verifies that generated content matches the checked token source.

## Angular use

Components consume semantic variables. A feature must not add a new raw color, spacing value, radius, shadow, or motion duration when an existing token fits.

A small local exception needs a short reason and should become a token when it appears twice.

## Penpot use

When the user supplies a Penpot link for an active design story and MCP is available, Codex reads the current token and style names before proposing repository changes. Penpot names mirror the repository names where the connected version supports them.

The story handoff records every mapping, conflict, and feature gap through [`penpot-handoff-template.md`](penpot-handoff-template.md). Penpot writes require the same separate plan and implementation approvals as code changes.

The project does not fetch Penpot tokens during runtime or build. MCP credentials, token-bearing server URLs, and private Penpot access data never enter the repository.

## Accessibility

Token choices must support tested contrast, visible focus, reduced motion, and readable status states. Status meaning cannot depend on hue alone.
