/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.shared.config;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.net.URI;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("nookforge.ai")
public record AiProperties(
    @NotBlank @Pattern(regexp = "ollama") String provider,
    @NotNull URI baseUrl,
    @NotBlank String model,
    @NotNull Duration requestTimeout) {

  @AssertTrue(message = "base-url must be an absolute HTTP or HTTPS URI with a host") public boolean isBaseUrlValid() {
    return baseUrl != null
        && baseUrl.isAbsolute()
        && !baseUrl.isOpaque()
        && baseUrl.getHost() != null
        && !baseUrl.getHost().isBlank()
        && baseUrl.getUserInfo() == null
        && baseUrl.getQuery() == null
        && baseUrl.getFragment() == null
        && ("http".equalsIgnoreCase(baseUrl.getScheme())
            || "https".equalsIgnoreCase(baseUrl.getScheme()));
  }

  @AssertTrue(message = "request-timeout must be positive") public boolean isRequestTimeoutValid() {
    return requestTimeout != null && !requestTimeout.isZero() && !requestTimeout.isNegative();
  }
}
