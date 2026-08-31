# Nook Forge web

This directory contains the Angular shell implemented by `NFA-003`. It uses standalone route boundaries, strict TypeScript, Vitest, Angular ESLint, the canonical repository brand, and generated repository design tokens.

## Native commands

Run these commands with Node 24.20.0 and npm 11.19.0:

```bash
npm ci
npm run lint
npm test
npm run generate:check
npm run build
```

Use `npm run generate` after an approved change to `packages/brand/brand.json` or `packages/design-tokens/tokens.json`. The committed generated files must pass their drift checks.

The current shell does not call the product API. Dashboard, new-task, history, monitoring, unavailable-service, and unknown-route states are local UI boundaries for later stories.

`NFA-005` adds a multi-stage container build that serves the production bundle from Nginx on internal port `8080`. It has a static `/healthz` endpoint but no API proxy; `NFA-007` owns the joined browser-to-API path.
