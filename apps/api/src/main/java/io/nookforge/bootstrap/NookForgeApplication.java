/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "io.nookforge")
@ConfigurationPropertiesScan(basePackages = "io.nookforge.shared.config")
public class NookForgeApplication {

  public static void main(String[] args) {
    SpringApplication.run(NookForgeApplication.class, args);
  }
}
