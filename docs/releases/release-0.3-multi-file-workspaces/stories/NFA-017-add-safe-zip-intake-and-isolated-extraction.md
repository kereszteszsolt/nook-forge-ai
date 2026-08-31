# NFA-017: Add safe ZIP intake and isolated extraction

## Status

Planned

## User story

As a user, I want to add a ZIP without risk to my machine.

## Goal

Scan and unpack safe entries inside one clean workspace.

## Dependencies

`NFA-016`.

## Acceptance criteria

- [ ] The archive adapter scans entry count, names, flags, declared sizes, and configured limits before extraction.
- [ ] Streamed extraction enforces actual total bytes and bytes per entry without trusting metadata alone.
- [ ] Absolute paths, parent traversal, duplicate normalized paths, links, devices, and encrypted data are rejected.
- [ ] Nested archives are recorded and ignored or rejected without extraction.
- [ ] All accepted entries stay under one generated temporary root with configured path depth.
- [ ] Temporary extraction data is removed after success, failure, timeout, and rejected intake.
- [ ] Small crafted tests cover ZIP Slip, bombs, links, duplicates, encryption, and cleanup.
- [ ] The original ZIP remains immutable and separate from extracted workspace data.

## Out of scope

This story does not support TAR files, password prompts, or user-defined safety overrides.
