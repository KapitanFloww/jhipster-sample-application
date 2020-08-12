package de.flowwindustries.jhipstersample;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("de.flowwindustries.jhipstersample");

        noClasses()
            .that()
            .resideInAnyPackage("de.flowwindustries.jhipstersample.service..")
            .or()
            .resideInAnyPackage("de.flowwindustries.jhipstersample.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..de.flowwindustries.jhipstersample.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
