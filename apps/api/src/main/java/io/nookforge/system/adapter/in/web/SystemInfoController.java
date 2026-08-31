/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.system.adapter.in.web;

import io.nookforge.shared.brand.BrandIdentity;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system")
public class SystemInfoController {

  private final BrandIdentity brandIdentity;
  private final BuildProperties buildProperties;

  public SystemInfoController(BrandIdentity brandIdentity, BuildProperties buildProperties) {
    this.brandIdentity = brandIdentity;
    this.buildProperties = buildProperties;
  }

  @GetMapping("/info")
  public SystemInfoResponse info() {
    var product =
        new SystemInfoResponse.Product(
            brandIdentity.productName(),
            brandIdentity.extendedName(),
            brandIdentity.tagline(),
            brandIdentity.technical().appId());
    var build =
        new SystemInfoResponse.Build(
            buildProperties.getGroup(),
            buildProperties.getArtifact(),
            buildProperties.getName(),
            buildProperties.getVersion());
    return new SystemInfoResponse(product, build);
  }
}
