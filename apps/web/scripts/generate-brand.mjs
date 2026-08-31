// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { mkdir, readFile, writeFile } from 'node:fs/promises';
import { dirname, resolve } from 'node:path';
import { fileURLToPath } from 'node:url';

const webDirectory = resolve(dirname(fileURLToPath(import.meta.url)), '..');
const sourcePath = resolve(webDirectory, '../../packages/brand/brand.json');
const outputPath = resolve(webDirectory, 'src/app/core/config/brand.generated.ts');

const brand = JSON.parse(await readFile(sourcePath, 'utf8'));
for (const key of ['productName', 'extendedName', 'tagline']) {
  if (typeof brand[key] !== 'string' || brand[key].trim() === '') {
    throw new Error(`Brand field ${key} must be a non-empty string.`);
  }
}

const generated = `// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>\n// SPDX-License-Identifier: Apache-2.0\n// Generated from packages/brand/brand.json. Do not edit.\n\nexport const BRAND = ${JSON.stringify(
  {
    productName: brand.productName,
    extendedName: brand.extendedName,
    tagline: brand.tagline,
  },
  null,
  2,
)} as const;\n`;

if (process.argv.includes('--check')) {
  const checked = await readFile(outputPath, 'utf8').catch(() => '');
  if (checked !== generated) {
    throw new Error('Generated brand source is out of date. Run npm run brand:generate.');
  }
  console.log('Brand output is current.');
} else {
  await mkdir(dirname(outputPath), { recursive: true });
  await writeFile(outputPath, generated, 'utf8');
  console.log(`Generated ${outputPath}`);
}
