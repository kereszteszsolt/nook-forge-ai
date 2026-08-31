/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.shared.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class DatabaseConfiguration {

  @Bean
  DataSource dataSource(DatabaseProperties properties) {
    var dataSource = new HikariDataSource();
    dataSource.setJdbcUrl(
        "jdbc:postgresql://%s:%d/%s"
            .formatted(properties.host(), properties.port(), properties.name()));
    dataSource.setUsername(properties.username());
    dataSource.setPassword(properties.password());
    dataSource.setPoolName("nook-forge-database");
    dataSource.setConnectionTimeout(3_000);
    dataSource.setValidationTimeout(2_000);
    return dataSource;
  }
}
