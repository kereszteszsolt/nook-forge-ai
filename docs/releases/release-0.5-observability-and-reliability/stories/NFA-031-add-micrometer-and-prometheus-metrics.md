# NFA-031: Add Micrometer and Prometheus metrics

## Status

Planned

## User story

As an operator, I want app metrics so I can see load, speed, and faults.

## Goal

Add small stable metrics that Prometheus can scrape.

## Dependencies

`NFA-030`.

## Acceptance criteria

- [ ] Micrometer records HTTP, JVM, executor, task, AI, upload, archive, storage, and cleanup metrics.
- [ ] Metric names, units, descriptions, and bounded labels follow one documented contract.
- [ ] No metric label uses a task ID, file ID, workspace ID, file name, path, prompt, or error message.
- [ ] The Prometheus endpoint is disabled or local-only by default and exposes no secret or content.
- [ ] Task metrics distinguish the bounded task type, state, provider, and approved model family only.
- [ ] Tests cover success, failure, timeout, rejection, queue saturation, and disabled metric paths.
- [ ] A metric contract test detects accidental high-cardinality labels.
- [ ] The base application still passes all checks without a Prometheus server.

## Out of scope

This story does not add a Prometheus container or a Grafana dashboard.
