/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.system.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface InstallationMetadataJpaRepository
    extends JpaRepository<InstallationMetadataJpaEntity, String> {}
