// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { ChangeDetectionStrategy, Component, inject } from '@angular/core';

import { API_AVAILABILITY } from '../../core/api/api-availability';

@Component({
  selector: 'nf-monitoring',
  template: `
    <header class="page-heading">
      <div>
        <p class="eyebrow">Local services</p>
        <h1>Monitoring</h1>
        <p>See whether the services needed by the workspace can be reached.</p>
      </div>
    </header>

    <section class="status-card" aria-labelledby="api-status-title">
      <span aria-hidden="true">○</span>
      <div>
        <strong id="api-status-title">{{ apiAvailability.label }}</strong>
        <span>{{ apiAvailability.detail }}</span>
      </div>
    </section>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class MonitoringComponent {
  readonly apiAvailability = inject(API_AVAILABILITY);
}
