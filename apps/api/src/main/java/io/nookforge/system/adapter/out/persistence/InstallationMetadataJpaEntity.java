/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.system.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "installation_metadata")
class InstallationMetadataJpaEntity {

  static final String SINGLETON_KEY = "installation";

  @Id
  @Column(name = "metadata_key", nullable = false, length = 32)
  private String metadataKey;

  @Column(name = "installation_id", nullable = false, unique = true)
  private UUID installationId;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  protected InstallationMetadataJpaEntity() {}

  InstallationMetadataJpaEntity(UUID installationId, Instant createdAt) {
    this.metadataKey = SINGLETON_KEY;
    this.installationId = installationId;
    this.createdAt = createdAt;
  }

  UUID installationId() {
    return installationId;
  }

  Instant createdAt() {
    return createdAt;
  }
}
