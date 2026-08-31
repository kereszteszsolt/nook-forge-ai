// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { bootstrapApplication } from '@angular/platform-browser';

import { AppComponent } from './app/app.component';
import { appConfig } from './app/app.config';
import { showBootstrapError } from './app/core/error/show-bootstrap-error';

bootstrapApplication(AppComponent, appConfig).catch(() => {
  showBootstrapError(document);
});
