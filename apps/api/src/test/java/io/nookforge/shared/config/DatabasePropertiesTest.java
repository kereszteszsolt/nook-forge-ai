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

class DatabasePropertiesTest {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withUserConfiguration(PropertiesConfiguration.class)
          .withPropertyValues(
              "nookforge.database.host=127.0.0.1",
              "nookforge.database.port=5433",
              "nookforge.database.name=nookforge",
              "nookforge.database.username=nookforge",
              "nookforge.database.password=local-secret");

  @Test
  void acceptsValidatedPostgreSqlCoordinates() {
    contextRunner.run(
        context -> {
          assertThat(context).hasNotFailed();
          var properties = context.getBean(DatabaseProperties.class);
          assertThat(properties.host()).isEqualTo("127.0.0.1");
          assertThat(properties.port()).isEqualTo(5433);
          assertThat(properties.name()).isEqualTo("nookforge");
          assertThat(properties.username()).isEqualTo("nookforge");
          assertThat(properties.password()).isEqualTo("local-secret");
        });
  }

  @Test
  void rejectsAnEmptyPassword() {
    assertInvalid("nookforge.database.password=");
  }

  @Test
  void rejectsAnUnsafeHost() {
    assertInvalid("nookforge.database.host=postgres/other");
  }

  @Test
  void rejectsAnInvalidPort() {
    assertInvalid("nookforge.database.port=0");
  }

  @Test
  void rejectsAnUnsafeDatabaseName() {
    assertInvalid("nookforge.database.name=nook-forge");
  }

  private void assertInvalid(String property) {
    contextRunner
        .withPropertyValues(property)
        .run(
            context -> {
              assertThat(context).hasFailed();
              assertThat(context.getStartupFailure())
                  .hasMessageContaining("Could not bind properties to 'DatabaseProperties'");
            });
  }

  @Configuration(proxyBeanMethods = false)
  @EnableConfigurationProperties(DatabaseProperties.class)
  static class PropertiesConfiguration {}
}
