package com.pgoellner.karel.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class Errors {
    JavaClasses exceptionClasses = new ClassFileImporter().importPackages("com.pgoellner.karel.errors");

    @Test
    void should_not_be_named_exception() {
        ArchRule rule = noClasses()
                .should().haveSimpleNameEndingWith("Exception");

        rule.check(exceptionClasses);
    }
}
