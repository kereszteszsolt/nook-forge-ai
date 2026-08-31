/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.shared.brand;

import java.util.Objects;

public record BrandIdentity(
    String productName, String extendedName, String tagline, TechnicalIdentity technical) {

  public BrandIdentity {
    productName = requireText(productName, "productName");
    extendedName = requireText(extendedName, "extendedName");
    tagline = requireText(tagline, "tagline");
    technical = Objects.requireNonNull(technical, "technical must not be null");
  }

  public record TechnicalIdentity(
      String repository,
      String appId,
      String javaPackage,
      String mavenArtifact,
      String npmScope,
      String dockerProject,
      String database,
      String storyPrefix) {

    public TechnicalIdentity {
      repository = requireText(repository, "repository");
      appId = requireText(appId, "appId");
      javaPackage = requireText(javaPackage, "javaPackage");
      mavenArtifact = requireText(mavenArtifact, "mavenArtifact");
      npmScope = requireText(npmScope, "npmScope");
      dockerProject = requireText(dockerProject, "dockerProject");
      database = requireText(database, "database");
      storyPrefix = requireText(storyPrefix, "storyPrefix");
    }
  }

  private static String requireText(String value, String field) {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException(field + " must not be blank");
    }
    return value;
  }
}
