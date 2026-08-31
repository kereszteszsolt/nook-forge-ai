// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'nf-new-task',
  template: `
    <header class="page-heading">
      <div>
        <p class="eyebrow">Planned workflow</p>
        <h1>New task</h1>
        <p>Choose a goal and local files when the first task workflow is connected.</p>
      </div>
    </header>

    <section class="page-panel" aria-labelledby="task-space-title">
      <h2 id="task-space-title">Task setup is not available yet</h2>
      <p>
        This foundation shell reserves the route without uploading files or contacting an API.
      </p>
    </section>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NewTaskComponent {}
