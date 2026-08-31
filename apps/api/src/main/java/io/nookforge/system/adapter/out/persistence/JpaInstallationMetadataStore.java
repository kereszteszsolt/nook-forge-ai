/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.system.adapter.out.persistence;

import io.nookforge.system.application.port.out.InstallationMetadataStore;
import io.nookforge.system.domain.InstallationMetadata;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JpaInstallationMetadataStore implements InstallationMetadataStore {

  private final InstallationMetadataJpaRepository repository;

  JpaInstallationMetadataStore(InstallationMetadataJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public InstallationMetadata create(InstallationMetadata metadata) {
    if (repository.existsById(InstallationMetadataJpaEntity.SINGLETON_KEY)) {
      throw new IllegalStateException("Installation metadata already exists.");
    }
    var entity =
        repository.saveAndFlush(
            new InstallationMetadataJpaEntity(metadata.installationId(), metadata.createdAt()));
    return toDomain(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<InstallationMetadata> find() {
    return repository
        .findById(InstallationMetadataJpaEntity.SINGLETON_KEY)
        .map(JpaInstallationMetadataStore::toDomain);
  }

  private static InstallationMetadata toDomain(InstallationMetadataJpaEntity entity) {
    return new InstallationMetadata(entity.installationId(), entity.createdAt());
  }
}
