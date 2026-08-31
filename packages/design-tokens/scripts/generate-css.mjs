// SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
// SPDX-License-Identifier: Apache-2.0

import { mkdir, readFile, writeFile } from 'node:fs/promises';
import { dirname, resolve } from 'node:path';
import { fileURLToPath } from 'node:url';

const packageDirectory = resolve(dirname(fileURLToPath(import.meta.url)), '..');
const sourcePath = resolve(packageDirectory, 'tokens.json');
const outputPath = resolve(packageDirectory, 'generated/tokens.css');
const requiredGroups = ['color', 'type', 'space', 'radius', 'elevation', 'motion'];

function toKebabCase(segment) {
  return segment.replaceAll(/([a-z0-9])([A-Z])/g, '$1-$2').toLowerCase();
}

function flattenTokens(value, path = []) {
  if (typeof value === 'string' || typeof value === 'number') {
    return [[path.map(toKebabCase).join('-'), String(value)]];
  }

  if (!value || Array.isArray(value) || typeof value !== 'object') {
    throw new Error(`Token ${path.join('.')} must be an object, string, or number.`);
  }

  return Object.keys(value)
    .sort()
    .flatMap((key) => flattenTokens(value[key], [...path, key]));
}

function render(tokens) {
  const missingGroups = requiredGroups.filter((group) => !(group in tokens));
  if (missingGroups.length > 0) {
    throw new Error(`Missing required token groups: ${missingGroups.join(', ')}`);
  }

  const declarations = flattenTokens(tokens)
    .map(([name, value]) => `  --nf-${name}: ${value};`)
    .join('\n');

  return `/* Generated from packages/design-tokens/tokens.json. Do not edit. */\n:root {\n${declarations}\n}\n`;
}

const tokens = JSON.parse(await readFile(sourcePath, 'utf8'));
const generated = render(tokens);

if (process.argv.includes('--check')) {
  const checked = await readFile(outputPath, 'utf8').catch(() => '');
  if (checked !== generated) {
    throw new Error('Generated design tokens are out of date. Run npm run tokens:generate.');
  }
  console.log('Design token output is current.');
} else {
  await mkdir(dirname(outputPath), { recursive: true });
  await writeFile(outputPath, generated, 'utf8');
  console.log(`Generated ${outputPath}`);
}
