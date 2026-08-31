/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.system.adapter.in.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.nookforge.bootstrap.NookForgeApplication;
import io.nookforge.shared.brand.BrandConfiguration;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SystemInfoController.class)
@Import({BrandConfiguration.class, SystemInfoControllerTest.BuildConfiguration.class})
@ContextConfiguration(classes = NookForgeApplication.class)
class SystemInfoControllerTest {

  private final MockMvc mockMvc;

  @Autowired
  SystemInfoControllerTest(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  void exposesStableProductAndBuildDataWithoutTimestamp() throws Exception {
    mockMvc
        .perform(get("/api/system/info"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.product.productName").value("Nook Forge"))
        .andExpect(jsonPath("$.product.extendedName").value("Nook Forge AI"))
        .andExpect(jsonPath("$.product.tagline").value("Turn local files into useful work."))
        .andExpect(jsonPath("$.product.applicationId").value("nook-forge-ai"))
        .andExpect(jsonPath("$.product.repository").doesNotExist())
        .andExpect(jsonPath("$.build.group").value("io.nookforge"))
        .andExpect(jsonPath("$.build.artifact").value("nook-forge-api"))
        .andExpect(jsonPath("$.build.name").value("Nook Forge API"))
        .andExpect(jsonPath("$.build.version").value("0.1.0-SNAPSHOT"))
        .andExpect(jsonPath("$.build.time").doesNotExist());
  }

  @TestConfiguration(proxyBeanMethods = false)
  static class BuildConfiguration {

    @Bean
    BuildProperties buildProperties() {
      var properties = new Properties();
      properties.setProperty("group", "io.nookforge");
      properties.setProperty("artifact", "nook-forge-api");
      properties.setProperty("name", "Nook Forge API");
      properties.setProperty("version", "0.1.0-SNAPSHOT");
      return new BuildProperties(properties);
    }
  }
}
