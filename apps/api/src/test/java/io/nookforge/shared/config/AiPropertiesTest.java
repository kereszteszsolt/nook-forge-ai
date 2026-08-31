/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.shared.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

class AiPropertiesTest {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withUserConfiguration(PropertiesConfiguration.class)
          .withPropertyValues(
              "nookforge.ai.provider=ollama",
              "nookforge.ai.base-url=http://host.docker.internal:11434",
              "nookforge.ai.model=llama3.1:8b",
              "nookforge.ai.request-timeout=120s");

  @Test
  void acceptsTheOllamaConfiguration() {
    contextRunner.run(
        context -> {
          assertThat(context).hasNotFailed();
          var properties = context.getBean(AiProperties.class);
          assertThat(properties.provider()).isEqualTo("ollama");
          assertThat(properties.baseUrl().toString())
              .isEqualTo("http://host.docker.internal:11434");
          assertThat(properties.model()).isEqualTo("llama3.1:8b");
          assertThat(properties.requestTimeout()).isEqualTo(Duration.ofSeconds(120));
        });
  }

  @Test
  void rejectsACloudProvider() {
    assertInvalid("nookforge.ai.provider=openrouter");
  }

  @Test
  void rejectsAFileUri() {
    assertInvalid("nookforge.ai.base-url=file:///tmp/private");
  }

  @Test
  void rejectsCredentialsInTheEndpoint() {
    assertInvalid("nookforge.ai.base-url=http://user:secret@localhost:11434");
  }

  @Test
  void rejectsAnEmptyModel() {
    assertInvalid("nookforge.ai.model=");
  }

  @Test
  void rejectsANonPositiveTimeout() {
    assertInvalid("nookforge.ai.request-timeout=0s");
  }

  private void assertInvalid(String property) {
    contextRunner
        .withPropertyValues(property)
        .run(
            context -> {
              assertThat(context).hasFailed();
              assertThat(context.getStartupFailure())
                  .hasMessageContaining("Could not bind properties to 'AiProperties'");
            });
  }

  @Configuration(proxyBeanMethods = false)
  @EnableConfigurationProperties(AiProperties.class)
  static class PropertiesConfiguration {}
}
