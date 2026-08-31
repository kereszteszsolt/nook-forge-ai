/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.system.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record InstallationMetadata(UUID installationId, Instant createdAt) {

  public InstallationMetadata {
    installationId = Objects.requireNonNull(installationId, "installationId must not be null");
    createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
  }
}
