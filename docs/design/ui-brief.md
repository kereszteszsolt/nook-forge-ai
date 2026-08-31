# Interface brief

## Product frame

Nook Forge is a task workspace, not a chat-first product. The main navigation should make workspaces, tasks, results, and monitoring clear.

## Planned main screens

### Dashboard

Show recent tasks, states, task types, duration, input count, and the last artifact. Include a clear primary action to create a task.

### New task

Let the user choose a task type, enter a goal, add one or more supported files, inspect validation messages, select the configured model, and start the task.

### Task detail

Show the task goal, input manifest, provider and model, ordered steps, current status, bounded errors, result preview, source references, and artifact downloads.

### Workspace files

Show a safe relative file tree with accepted, ignored, rejected, and failed states. Display reasons and totals without exposing server paths.

### Documentation preview

Show generated file tabs, observed or inferred labels, unresolved questions, source references, and export options. Keep original and generated trees visually separate.

### Monitoring

Show core health in the app and link to optional Langfuse and Grafana services only when configured. Do not embed secrets or full trace content.

## Planned layout

```text
┌──────────────────────────────────────────────────────────┐
│ Nook Forge · local provider state · settings              │
├──────────────┬───────────────────────────────────────────┤
│ Dashboard    │ page title and primary action             │
│ New task     │                                           │
│ Workspaces   │ main workspace                            │
│ History      │                                           │
│ Monitoring   │                                           │
└──────────────┴───────────────────────────────────────────┘
```

On a narrow screen, navigation becomes a compact drawer or bottom-safe pattern. The task result remains readable without horizontal page overflow.

## Visual character

The interface should feel calm, precise, private, and tool-like. It should avoid a generic chatbot bubble layout, excessive gradients, and decorative motion that competes with task state.

## Content rules

- Use plain action labels.
- Show local or cloud provider state in a persistent clear place.
- Use color plus text or icon, never color alone.
- Keep file rejection and model errors direct and recoverable.
- Mark AI inferences and unknowns beside the text, not only in a footer.
