/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.shared.config;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("nookforge.api")
public record ApiProperties(@NotNull URI publicBaseUrl) {

  @AssertTrue(message = "public-base-url must be an absolute HTTP or HTTPS URI with a host") public boolean isPublicBaseUrlValid() {
    return publicBaseUrl != null
        && publicBaseUrl.isAbsolute()
        && !publicBaseUrl.isOpaque()
        && publicBaseUrl.getHost() != null
        && !publicBaseUrl.getHost().isBlank()
        && ("http".equalsIgnoreCase(publicBaseUrl.getScheme())
            || "https".equalsIgnoreCase(publicBaseUrl.getScheme()));
  }
}
