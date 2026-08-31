/*
 * SPDX-FileCopyrightText: 2026 Keresztes Zsolt <https://kereszteszsolt.hu>
 * SPDX-License-Identifier: Apache-2.0
 */
package io.nookforge.shared.brand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.databind.json.JsonMapper;

class BrandIdentityTest {

  private final BrandIdentity brandIdentity =
      new BrandConfiguration().brandIdentity(JsonMapper.builder().build());

  @Test
  void classpathBrandIsTheExactRepositoryResource() throws IOException {
    var repositoryBrand = Files.readString(Path.of("..", "..", "packages", "brand", "brand.json"));
    var classpathBrand =
        new ClassPathResource("brand/brand.json").getContentAsString(StandardCharsets.UTF_8);

    assertThat(classpathBrand).isEqualTo(repositoryBrand);
  }

  @Test
  void exposesTheCanonicalProductIdentity() {
    assertThat(brandIdentity.productName()).isEqualTo("Nook Forge");
    assertThat(brandIdentity.extendedName()).isEqualTo("Nook Forge AI");
    assertThat(brandIdentity.tagline()).isEqualTo("Turn local files into useful work.");
    assertThat(brandIdentity.technical().appId()).isEqualTo("nook-forge-ai");
    assertThat(brandIdentity.technical().javaPackage()).isEqualTo("io.nookforge");
    assertThat(brandIdentity.technical().mavenArtifact()).isEqualTo("nook-forge-api");
  }

  @Test
  void rejectsIncompleteBrandData() {
    var technical = brandIdentity.technical();

    assertThatIllegalArgumentException()
        .isThrownBy(
            () ->
                new BrandIdentity(
                    " ", brandIdentity.extendedName(), brandIdentity.tagline(), technical))
        .withMessage("productName must not be blank");
  }
}
