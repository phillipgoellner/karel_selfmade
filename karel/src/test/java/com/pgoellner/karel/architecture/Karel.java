package com.pgoellner.karel.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.freeze.FreezingArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

class Karel {
    JavaClasses karel = new ClassFileImporter().importClasses(com.pgoellner.karel.Karel.class);

    @Test
    void should_be_decoupled() {
        ArchRule rule = FreezingArchRule.freeze(noClasses()
                .should().dependOnClassesThat().resideInAnyPackage(
                        "..localization..",
                        "..parse.."
                ));

        rule.check(karel);
    }

    @Test
    void access_to_methods() {
        ArchRule publicMethodsRule = methods()
                .that().arePublic()
                .and().haveNameNotEndingWith("run")
                .should().beFinal();

        ArchRule otherMethodsRule = noMethods()
                .should().beProtected();

        publicMethodsRule.check(karel);
        otherMethodsRule.check(karel);
    }
}
