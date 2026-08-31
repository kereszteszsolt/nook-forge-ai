/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.system.adapter.in.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.nookforge.bootstrap.NookForgeApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = NookForgeApplication.class)
@AutoConfigureMockMvc
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

  @Test
  void exposesLivenessWithoutComponentsOrDetails() throws Exception {
    mockMvc
        .perform(get("/actuator/health/liveness"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("UP"))
        .andExpect(jsonPath("$.components").doesNotExist())
        .andExpect(jsonPath("$.details").doesNotExist());
  }

  @Test
  void exposesReadinessWithoutComponentsOrDetails() throws Exception {
    mockMvc
        .perform(get("/actuator/health/readiness"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("UP"))
        .andExpect(jsonPath("$.components").doesNotExist())
        .andExpect(jsonPath("$.details").doesNotExist());
  }
}
