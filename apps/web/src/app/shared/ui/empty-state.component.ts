// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { ChangeDetectionStrategy, Component, input } from '@angular/core';

@Component({
  selector: 'nf-empty-state',
  template: `
    <section class="empty-state">
      <div class="empty-state__mark" aria-hidden="true">◇</div>
      <h2>{{ title() }}</h2>
      <p>{{ message() }}</p>
      <ng-content />
    </section>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EmptyStateComponent {
  readonly title = input.required<string>();
  readonly message = input.required<string>();
}
