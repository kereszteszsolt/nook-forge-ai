// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

export function showBootstrapError(page: Document): void {
  page.querySelector('nf-root')?.setAttribute('hidden', '');

  const fallback = page.querySelector<HTMLElement>('#bootstrap-error');
  if (fallback) {
    fallback.hidden = false;
  }
}
