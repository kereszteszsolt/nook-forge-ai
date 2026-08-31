// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { showBootstrapError } from './show-bootstrap-error';

describe('showBootstrapError', () => {
  it('replaces an unusable app root with a bounded visible message', () => {
    const page = document.implementation.createHTMLDocument();
    page.body.innerHTML = `
      <nf-root></nf-root>
      <section id="bootstrap-error" hidden>Safe startup message</section>
    `;

    showBootstrapError(page);

    expect(page.querySelector('nf-root')?.hasAttribute('hidden')).toBe(true);
    expect(page.querySelector<HTMLElement>('#bootstrap-error')?.hidden).toBe(false);
    expect(page.body.textContent).not.toContain('Error:');
  });
});
