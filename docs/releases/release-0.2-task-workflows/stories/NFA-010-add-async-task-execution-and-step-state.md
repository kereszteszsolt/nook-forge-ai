# NFA-010: Add async task execution and step state

## Status

Planned

## User story

As a user, I want long work to show its steps so I know what is going on.

## Goal

Run tasks off the request path and save each state.

## Dependencies

`NFA-008` and `NFA-009`.

## Acceptance criteria

- [ ] Starting a task returns its ID before model work finishes.
- [ ] One bounded Spring task executor runs queued work without Kafka, RabbitMQ, or another service.
- [ ] Each task writes ordered step states for prepare, analyze, generate, and store work.
- [ ] State and step changes use clear transaction boundaries and safe failure codes.
- [ ] A model timeout or adapter error moves the task to `FAILED` and preserves prior proof.
- [ ] Executor size, queue size, and step timeout use validated configuration.
- [ ] Tests cover success, rejection, queue saturation, timeout, duplicate start, and database failure.
- [ ] Release 0.2 makes no claim that interrupted running tasks resume after a process crash.

## Out of scope

This story does not add task cancellation, retry, startup recovery, or a separate worker process.
