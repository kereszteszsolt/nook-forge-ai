// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

import { API_AVAILABILITY } from '../api/api-availability';
import { BRAND } from '../config/brand.generated';

@Component({
  selector: 'nf-app-shell',
  imports: [RouterLink, RouterLinkActive, RouterOutlet],
  templateUrl: './app-shell.component.html',
  styleUrl: './app-shell.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AppShellComponent {
  readonly brand = BRAND;
  readonly apiAvailability = inject(API_AVAILABILITY);

  constructor() {
    inject(Title).setTitle(BRAND.productName);
  }
}
