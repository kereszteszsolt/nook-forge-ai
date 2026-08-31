// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { TestBed } from '@angular/core/testing';
import { provideRouter, Router } from '@angular/router';

import { AppComponent } from './app.component';
import { APP_ROUTES } from './app.routes';

describe('AppComponent', () => {
  async function renderAt(path: string) {
    await TestBed.configureTestingModule({
      imports: [AppComponent],
      providers: [provideRouter(APP_ROUTES)],
    }).compileComponents();

    const fixture = TestBed.createComponent(AppComponent);
    await TestBed.inject(Router).navigateByUrl(path);
    await fixture.whenStable();
    fixture.detectChanges();
    return fixture.nativeElement as HTMLElement;
  }

  it('renders the canonical product identity and planned navigation', async () => {
    const element = await renderAt('/');

    expect(element.querySelector('.brand strong')?.textContent).toContain('Nook Forge');
    expect(element.querySelectorAll('nav a')).toHaveLength(4);
    expect(element.querySelector('.skip-link')?.getAttribute('href')).toBe('#main-content');
    expect(element.querySelector('nav a[aria-current="page"]')?.getAttribute('href')).toBe('/');
    expect(element.textContent).toContain('No tasks yet');
  });

  it('renders the new task route without making it operational', async () => {
    const element = await renderAt('/tasks/new');

    expect(element.querySelector('h1')?.textContent).toContain('New task');
    expect(element.textContent).toContain('without uploading files or contacting an API');
  });

  it('renders an explicit unavailable API state', async () => {
    const element = await renderAt('/monitoring');

    expect(element.querySelector('#api-status-title')?.textContent).toContain('API unavailable');
    expect(element.textContent).toContain('later foundation story');
  });

  it('renders the history route and its empty state', async () => {
    const element = await renderAt('/history');

    expect(element.querySelector('h1')?.textContent).toContain('History');
    expect(element.querySelector('nav a[aria-current="page"]')?.getAttribute('href')).toBe(
      '/history',
    );
    expect(element.textContent).toContain('History is empty');
  });

  it('renders a bounded fallback for unknown routes', async () => {
    const element = await renderAt('/missing');

    expect(element.querySelector('h1')?.textContent).toContain(
      'This page is not in the workspace.',
    );
  });
});
