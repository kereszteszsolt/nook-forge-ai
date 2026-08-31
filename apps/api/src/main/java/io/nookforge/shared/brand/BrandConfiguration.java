/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.shared.brand;

import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.json.JsonMapper;

@Configuration(proxyBeanMethods = false)
public class BrandConfiguration {

  @Bean
  BrandIdentity brandIdentity(JsonMapper jsonMapper) {
    var resource = new ClassPathResource("brand/brand.json");
    try (var inputStream = resource.getInputStream()) {
      return jsonMapper.readValue(inputStream, BrandIdentity.class);
    } catch (IOException exception) {
      throw new IllegalStateException("Canonical brand resource cannot be read.", exception);
    }
  }
}
