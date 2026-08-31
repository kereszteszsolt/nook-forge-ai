/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.shared.error;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.nookforge.bootstrap.NookForgeApplication;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebMvcTest(ApiExceptionHandlerTest.TestController.class)
@Import({ApiExceptionHandler.class, ApiExceptionHandlerTest.TestController.class})
@ContextConfiguration(classes = NookForgeApplication.class)
class ApiExceptionHandlerTest {

  private static final String SECRET = "secret-token-that-must-not-leak";

  private final MockMvc mockMvc;

  @Autowired
  ApiExceptionHandlerTest(MockMvc mockMvc) {
    this.mockMvc = mockMvc;
  }

  @Test
  void mapsValidationToASafeProblemDetail() throws Exception {
    var response =
        mockMvc
            .perform(
                post("/test/errors/validation")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"\"}"))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.title").value("Request validation failed"))
            .andExpect(
                jsonPath("$.detail").value("The request does not match the required contract."))
            .andExpect(jsonPath("$.instance").value("/test/errors/validation"))
            .andExpect(jsonPath("$.code").value("validation_failed"))
            .andExpect(jsonPath("$.type").value("urn:nookforge:error:validation_failed"))
            .andReturn()
            .getResponse()
            .getContentAsString();

    assertThat(response).doesNotContain(SECRET);
  }

  @Test
  void mapsMissingRoutesToASafeProblemDetail() throws Exception {
    mockMvc
        .perform(get("/route-that-does-not-exist"))
        .andExpect(status().isNotFound())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(jsonPath("$.status").value(404))
        .andExpect(jsonPath("$.title").value("Resource not found"))
        .andExpect(jsonPath("$.instance").value("/route-that-does-not-exist"))
        .andExpect(jsonPath("$.code").value("not_found"))
        .andExpect(jsonPath("$.detail").value("The requested resource is not available."));
  }

  @Test
  void mapsUnexpectedFailuresWithoutLeakingExceptionData() throws Exception {
    var response =
        mockMvc
            .perform(get("/test/errors/unexpected"))
            .andExpect(status().isInternalServerError())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
            .andExpect(jsonPath("$.status").value(500))
            .andExpect(jsonPath("$.title").value("Request failed"))
            .andExpect(jsonPath("$.instance").value("/test/errors/unexpected"))
            .andExpect(jsonPath("$.code").value("internal_error"))
            .andExpect(jsonPath("$.detail").value("The request could not be completed."))
            .andReturn()
            .getResponse()
            .getContentAsString();

    assertThat(response).doesNotContain(SECRET).doesNotContain("IllegalStateException");
  }

  @Test
  void keepsMalformedJsonAsASafeClientError() throws Exception {
    mockMvc
        .perform(
            post("/test/errors/validation").contentType(MediaType.APPLICATION_JSON).content("{"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(jsonPath("$.code").value("request_rejected"))
        .andExpect(jsonPath("$.status").value(400))
        .andExpect(jsonPath("$.instance").value("/test/errors/validation"));
  }

  @Test
  void keepsUnsupportedMediaTypesAsSafeClientErrors() throws Exception {
    mockMvc
        .perform(post("/test/errors/validation").contentType(MediaType.TEXT_PLAIN).content("name"))
        .andExpect(status().isUnsupportedMediaType())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(jsonPath("$.code").value("request_rejected"))
        .andExpect(jsonPath("$.status").value(415));
  }

  @Test
  void keepsUnsupportedMethodsAsSafeClientErrors() throws Exception {
    mockMvc
        .perform(put("/test/errors/validation"))
        .andExpect(status().isMethodNotAllowed())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(jsonPath("$.code").value("request_rejected"))
        .andExpect(jsonPath("$.status").value(405));
  }

  @RestController
  @RequestMapping("/test/errors")
  static class TestController {

    @PostMapping("/validation")
    TestRequest validation(@Valid @RequestBody TestRequest request) {
      return request;
    }

    @GetMapping("/unexpected")
    void unexpected() {
      throw new IllegalStateException(SECRET);
    }
  }

  record TestRequest(@NotBlank String name) {}
}
