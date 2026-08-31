/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.bootstrap;

import static org.assertj.core.api.Assertions.assertThat;

import io.nookforge.shared.config.ApiProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class NookForgeApplicationTest {

  private final ApplicationContext applicationContext;
  private final ApiProperties apiProperties;

  @Autowired
  NookForgeApplicationTest(ApplicationContext applicationContext, ApiProperties apiProperties) {
    this.applicationContext = applicationContext;
    this.apiProperties = apiProperties;
  }

  @Test
  void startsWithoutExternalServices() {
    assertThat(applicationContext).isNotNull();
    assertThat(applicationContext.getEnvironment().getProperty("server.address"))
        .isEqualTo("127.0.0.1");
    assertThat(apiProperties.publicBaseUrl().toString()).isEqualTo("http://127.0.0.1:8080");
  }
}
