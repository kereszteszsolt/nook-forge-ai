# ADR-001: Use a small polyglot monorepo

- Status: Accepted
- Date: 2026-08-31

## Context

Nook Forge needs one Java API, one Angular web app, shared design and brand contracts, Docker configuration, and release evidence. Java and Angular use different native build tools. The repository is too small to need a second monorepo task graph.

## Decision

Use one repository with `apps/api`, `apps/web`, `packages`, `infra`, and `docs`. Maven Wrapper owns Java work. npm and Angular CLI own web work. Do not add Nx or Turborepo.

## Consequences

The full product can change in one reviewed story and one commit. Each tool keeps its normal lock and build flow. Root scripts may coordinate checks but must show the native commands.

The decision may be reviewed if app count, build time, or ownership creates a measured problem.
