package com.pgoellner.karel.architecture;

import com.pgoellner.karel.localization.TextLabels;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

class Localization {
    JavaClasses languageLabelClasses = new ClassFileImporter().importPackages("com.pgoellner.karel.localization");

    @Test
    void language_labels() {
        ArchRule rule = classes()
                .that().areNotInterfaces()
                .should().haveSimpleNameEndingWith("Labels")
                .andShould().implement(TextLabels.class);

        rule.check(languageLabelClasses);
    }
}
