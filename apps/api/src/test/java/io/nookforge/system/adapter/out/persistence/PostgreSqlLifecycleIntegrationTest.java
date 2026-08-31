/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.system.adapter.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import io.nookforge.bootstrap.NookForgeApplication;
import io.nookforge.system.application.port.out.InstallationMetadataStore;
import io.nookforge.system.domain.InstallationMetadata;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

class PostgreSqlLifecycleIntegrationTest {

  private static final DockerImageName POSTGRES_IMAGE =
      DockerImageName.parse("postgres:18.6-bookworm");
  private static final String DATABASE = "nookforge_test";
  private static final String USERNAME = "nookforge_test";
  private static final String PASSWORD = "test-" + UUID.randomUUID();

  @org.junit.jupiter.api.Test
  void migratesPersistsAcrossRestartAndReportsSafeHealth() throws Exception {
    var metadata =
        new InstallationMetadata(UUID.randomUUID(), Instant.now().truncatedTo(ChronoUnit.MICROS));

    try (var postgres =
        new PostgreSQLContainer(POSTGRES_IMAGE)
            .withDatabaseName(DATABASE)
            .withUsername(USERNAME)
            .withPassword(PASSWORD)) {
      postgres.start();

      try (var firstContext = startApplication(postgres, "local")) {
        var jdbcTemplate = firstContext.getBean(JdbcTemplate.class);
        var store = firstContext.getBean(InstallationMetadataStore.class);

        assertThat(applicationTables(jdbcTemplate))
            .containsExactly("flyway_schema_history", "installation_metadata");
        assertThat(
                jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM installation_metadata", Long.class))
            .isZero();
        assertThat(
                jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM flyway_schema_history WHERE version = '1' AND success",
                    Long.class))
            .isOne();
        assertThat(store.create(metadata)).isEqualTo(metadata);
        assertThat(store.find()).contains(metadata);
        assertThatIllegalStateException()
            .isThrownBy(() -> store.create(metadata))
            .withMessage("Installation metadata already exists.");
      }

      try (var restartedContext = startApplication(postgres, "container")) {
        var store = restartedContext.getBean(InstallationMetadataStore.class);
        assertThat(store.find()).contains(metadata);

        var port =
            restartedContext
                .getEnvironment()
                .getRequiredProperty("local.server.port", Integer.class);
        assertHealth(port, "/actuator/health/readiness", 200, "UP", postgres);

        postgres.stop();

        awaitHealth(port, "/actuator/health/readiness", 503, "DOWN", postgres);
        assertHealth(port, "/actuator/health/liveness", 200, "UP", postgres);
      }
    }
  }

  private ConfigurableApplicationContext startApplication(
      PostgreSQLContainer postgres, String profile) {
    return new SpringApplicationBuilder(NookForgeApplication.class)
        .profiles(profile)
        .properties("spring.main.banner-mode=off", "server.port=0")
        .run(
            "--nookforge.database.host=" + postgres.getHost(),
            "--nookforge.database.port=" + postgres.getFirstMappedPort(),
            "--nookforge.database.name=" + DATABASE,
            "--nookforge.database.username=" + USERNAME,
            "--nookforge.database.password=" + PASSWORD);
  }

  private java.util.List<String> applicationTables(JdbcTemplate jdbcTemplate) {
    return jdbcTemplate.queryForList(
        """
        SELECT table_name
        FROM information_schema.tables
        WHERE table_schema = 'public' AND table_type = 'BASE TABLE'
        ORDER BY table_name
        """,
        String.class);
  }

  private void awaitHealth(
      int port,
      String path,
      int expectedStatus,
      String expectedHealth,
      PostgreSQLContainer postgres)
      throws Exception {
    var deadline = Instant.now().plus(Duration.ofSeconds(15));
    AssertionError latestFailure = null;
    while (Instant.now().isBefore(deadline)) {
      try {
        assertHealth(port, path, expectedStatus, expectedHealth, postgres);
        return;
      } catch (AssertionError failure) {
        latestFailure = failure;
        Thread.sleep(100);
      }
    }
    throw latestFailure == null
        ? new AssertionError("Health state did not change.")
        : latestFailure;
  }

  private void assertHealth(
      int port,
      String path,
      int expectedStatus,
      String expectedHealth,
      PostgreSQLContainer postgres)
      throws Exception {
    var request =
        HttpRequest.newBuilder(URI.create("http://127.0.0.1:" + port + path))
            .timeout(Duration.ofSeconds(5))
            .GET()
            .build();
    try (var client = HttpClient.newHttpClient()) {
      var response = client.send(request, HttpResponse.BodyHandlers.ofString());
      assertThat(response.statusCode()).isEqualTo(expectedStatus);
      assertThat(response.body())
          .isEqualTo("{\"status\":\"" + expectedHealth + "\"}")
          .doesNotContain("jdbc:postgresql", postgres.getHost(), DATABASE, USERNAME, PASSWORD);
    }
  }
}
