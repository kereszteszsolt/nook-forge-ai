/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.shared.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

class ApiPropertiesTest {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner().withUserConfiguration(PropertiesConfiguration.class);

  @Test
  void acceptsAnAbsoluteHttpUri() {
    contextRunner
        .withPropertyValues("nookforge.api.public-base-url=https://127.0.0.1:8443")
        .run(
            context -> {
              assertThat(context).hasNotFailed();
              assertThat(context.getBean(ApiProperties.class).publicBaseUrl().getScheme())
                  .isEqualTo("https");
            });
  }

  @Test
  void failsFastForAnUnsupportedUri() {
    contextRunner
        .withPropertyValues("nookforge.api.public-base-url=file:///tmp/private")
        .run(
            context -> {
              assertThat(context).hasFailed();
              assertThat(context.getStartupFailure())
                  .hasMessageContaining("Could not bind properties to 'ApiProperties'");
            });
  }

  @Test
  void failsFastForAHostlessUri() {
    contextRunner
        .withPropertyValues("nookforge.api.public-base-url=https:example")
        .run(
            context -> {
              assertThat(context).hasFailed();
              assertThat(context.getStartupFailure())
                  .hasMessageContaining("Could not bind properties to 'ApiProperties'");
            });
  }

  @Configuration(proxyBeanMethods = false)
  @EnableConfigurationProperties(ApiProperties.class)
  static class PropertiesConfiguration {}
}
