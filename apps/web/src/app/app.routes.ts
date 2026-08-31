// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { Routes } from '@angular/router';

export const APP_ROUTES: Routes = [
  {
    path: '',
    pathMatch: 'full',
    title: 'Dashboard · Nook Forge',
    loadComponent: () =>
      import('./features/dashboard/dashboard.component').then(
        (module) => module.DashboardComponent,
      ),
  },
  {
    path: 'tasks/new',
    title: 'New task · Nook Forge',
    loadComponent: () =>
      import('./features/tasks/new-task/new-task.component').then(
        (module) => module.NewTaskComponent,
      ),
  },
  {
    path: 'history',
    title: 'History · Nook Forge',
    loadComponent: () =>
      import('./features/tasks/history/history.component').then(
        (module) => module.HistoryComponent,
      ),
  },
  {
    path: 'monitoring',
    title: 'Monitoring · Nook Forge',
    loadComponent: () =>
      import('./features/monitoring/monitoring.component').then(
        (module) => module.MonitoringComponent,
      ),
  },
  {
    path: '**',
    title: 'Page not found · Nook Forge',
    loadComponent: () =>
      import('./core/error/not-found.component').then(
        (module) => module.NotFoundComponent,
      ),
  },
];
