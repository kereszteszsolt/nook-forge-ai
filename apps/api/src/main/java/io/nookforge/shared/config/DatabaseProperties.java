/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.shared.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("nookforge.database")
public final class DatabaseProperties {

  @NotBlank @Pattern(regexp = "[A-Za-z0-9.-]+") private final String host;

  @Min(1) @Max(65_535) private final int port;

  @NotBlank @Pattern(regexp = "[A-Za-z0-9_]+") private final String name;

  @NotBlank @Pattern(regexp = "[A-Za-z0-9_]+") private final String username;

  @NotBlank private final String password;

  public DatabaseProperties(String host, int port, String name, String username, String password) {
    this.host = host;
    this.port = port;
    this.name = name;
    this.username = username;
    this.password = password;
  }

  public String host() {
    return host;
  }

  public int port() {
    return port;
  }

  public String name() {
    return name;
  }

  public String username() {
    return username;
  }

  public String password() {
    return password;
  }
}
