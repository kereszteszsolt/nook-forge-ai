# NFA-003: Build the Angular app shell and token flow

## Status

Implemented

## User story

As a user, I want a calm web shell so I can see where my work will live.

## Goal

Start Angular with strict types and shared design tokens.

## Dependencies

`NFA-001`.

## Acceptance criteria

- [x] `apps/web` uses the Angular CLI, strict TypeScript, and pinned npm dependencies.
- [x] The app shell provides planned dashboard, new task, history, and monitoring routes.
- [x] Feature code, shared UI, layout, API, and error boundaries use the documented folder shape.
- [x] `packages/design-tokens/tokens.json` is the source for generated CSS custom properties.
- [x] The first token set covers semantic color, type, space, radius, elevation, and motion names.
- [x] The shell covers keyboard focus, narrow layout, empty state, and unavailable API state.
- [x] Web lint, unit tests, token drift checks, and the production build pass.
- [x] No global state library or second UI component library is added.

## Out of scope

This story does not call a product API or claim a final Penpot handoff.
