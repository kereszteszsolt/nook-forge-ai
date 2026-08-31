// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterLink } from '@angular/router';

import { EmptyStateComponent } from '../../shared/ui/empty-state.component';

@Component({
  selector: 'nf-dashboard',
  imports: [EmptyStateComponent, RouterLink],
  template: `
    <header class="page-heading">
      <div>
        <p class="eyebrow">Local workspace</p>
        <h1>Dashboard</h1>
        <p>Keep tasks, sources, and generated artifacts in one clear place.</p>
      </div>
      <a class="primary-action" routerLink="/tasks/new">Create a task</a>
    </header>

    <nf-empty-state
      title="No tasks yet"
      message="Your recent work will appear here after the first task workflow is connected."
    >
      <a routerLink="/tasks/new">See the planned task space</a>
    </nf-empty-state>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DashboardComponent {}
