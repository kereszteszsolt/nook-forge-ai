/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@AnalyzeClasses(packages = "io.nookforge", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTest {

  @ArchTest
  static final ArchRule domain_is_framework_independent =
      noClasses()
          .that()
          .resideInAPackage("..domain..")
          .should()
          .dependOnClassesThat()
          .resideInAnyPackage(
              "org.springframework..",
              "jakarta.persistence..",
              "dev.langchain4j..",
              "io.ollama..",
              "java.util.zip..",
              "org.springframework.http..")
          .allowEmptyShould(true);

  @ArchTest
  static final ArchRule application_does_not_depend_on_adapters =
      noClasses()
          .that()
          .resideInAPackage("..application..")
          .should()
          .dependOnClassesThat()
          .resideInAPackage("..adapter..")
          .allowEmptyShould(true);

  @ArchTest
  static final ArchRule web_does_not_reach_persistence_files_or_ai =
      noClasses()
          .that()
          .resideInAPackage("..adapter.in.web..")
          .should()
          .dependOnClassesThat()
          .resideInAnyPackage(
              "..adapter.out.persistence..",
              "jakarta.persistence..",
              "org.springframework.data..",
              "java.nio.file..",
              "dev.langchain4j..",
              "io.ollama..")
          .allowEmptyShould(true);

  @ArchTest
  static final ArchRule persistence_dependencies_stay_in_persistence_adapters =
      noClasses()
          .that()
          .resideOutsideOfPackage("..adapter.out.persistence..")
          .should()
          .dependOnClassesThat()
          .resideInAnyPackage("jakarta.persistence..", "org.springframework.data..")
          .allowEmptyShould(true);

  @ArchTest
  static final ArchRule ai_dependencies_stay_in_ai_adapters_or_configuration =
      noClasses()
          .that()
          .resideOutsideOfPackages("..adapter.out.ai..", "..shared.config..")
          .should()
          .dependOnClassesThat()
          .resideInAnyPackage("dev.langchain4j..", "io.ollama..")
          .allowEmptyShould(true);

  @ArchTest
  static final ArchRule no_spring_field_injection =
      noFields().should().beAnnotatedWith(Autowired.class).orShould().beAnnotatedWith(Value.class);

  @ArchTest
  static final ArchRule no_direct_environment_reads =
      noClasses()
          .should()
          .callMethod(System.class, "getenv")
          .orShould()
          .callMethod(System.class, "getenv", String.class);

  @ArchTest
  static final ArchRule top_level_packages_are_free_of_cycles =
      slices().matching("io.nookforge.(*)..").should().beFreeOfCycles();
}
