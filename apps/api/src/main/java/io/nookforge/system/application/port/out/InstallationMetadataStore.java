/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.system.application.port.out;

import io.nookforge.system.domain.InstallationMetadata;
import java.util.Optional;

public interface InstallationMetadataStore {

  InstallationMetadata create(InstallationMetadata metadata);

  Optional<InstallationMetadata> find();
}
