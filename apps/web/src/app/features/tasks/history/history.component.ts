// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { ChangeDetectionStrategy, Component } from '@angular/core';

import { EmptyStateComponent } from '../../../shared/ui/empty-state.component';

@Component({
  selector: 'nf-history',
  imports: [EmptyStateComponent],
  template: `
    <header class="page-heading">
      <div>
        <p class="eyebrow">Task record</p>
        <h1>History</h1>
        <p>Completed and interrupted work will be listed here.</p>
      </div>
    </header>

    <nf-empty-state
      title="History is empty"
      message="No task records exist in this new local workspace."
    />
  `,
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class HistoryComponent {}
