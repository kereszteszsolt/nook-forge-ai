// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { InjectionToken } from '@angular/core';

export interface ApiAvailability {
  readonly state: 'unavailable';
  readonly label: string;
  readonly detail: string;
}

export const API_AVAILABILITY = new InjectionToken<ApiAvailability>('API_AVAILABILITY', {
  factory: () => ({
    state: 'unavailable',
    label: 'API unavailable',
    detail: 'The local API connection will be added by a later foundation story.',
  }),
});
