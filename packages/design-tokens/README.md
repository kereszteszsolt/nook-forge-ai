# Nook Forge design tokens

[`tokens.json`](tokens.json) is the reviewed runtime source for Nook Forge design values. The dependency-free generator writes [`generated/tokens.css`](generated/tokens.css) with stable `--nf-*` custom properties.

From `apps/web`, run:

```bash
npm run tokens:generate
npm run tokens:check
```

The first set covers semantic color, type, space, radius, elevation, and motion names. Components consume the generated properties; the CSS output is not hand-edited.

Penpot remains a design handoff tool rather than a runtime dependency. `NFA-007` owns the first final Penpot handoff and deterministic browser evidence.
