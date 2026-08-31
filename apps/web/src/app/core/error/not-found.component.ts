// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'nf-not-found',
  imports: [RouterLink],
  template: `
    <section class="page-panel" aria-labelledby="not-found-title">
      <p class="eyebrow">Unknown route</p>
      <h1 id="not-found-title">This page is not in the workspace.</h1>
      <p>Use the dashboard to return to the implemented shell.</p>
      <a class="primary-action" routerLink="/">Back to dashboard</a>
    </section>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NotFoundComponent {}
