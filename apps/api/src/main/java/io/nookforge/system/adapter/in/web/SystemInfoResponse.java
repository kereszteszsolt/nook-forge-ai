/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.system.adapter.in.web;

public record SystemInfoResponse(Product product, Build build) {

  public record Product(
      String productName, String extendedName, String tagline, String applicationId) {}

  public record Build(String group, String artifact, String name, String version) {}
}
