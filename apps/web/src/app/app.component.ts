// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { ChangeDetectionStrategy, Component } from '@angular/core';

import { AppShellComponent } from './core/layout/app-shell.component';

@Component({
  selector: 'nf-root',
  imports: [AppShellComponent],
  template: '<nf-app-shell />',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AppComponent {}
