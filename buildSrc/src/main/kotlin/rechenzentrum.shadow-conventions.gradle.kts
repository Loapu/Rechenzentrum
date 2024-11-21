/*
 * Copyright (c) Benjamin Selig, 2024, licensed under the EUPL
 */

import gradle.kotlin.dsl.accessors._8c47cae829ea3d03260d5ff13fb2398e.assemble
import gradle.kotlin.dsl.accessors._8c47cae829ea3d03260d5ff13fb2398e.implementation

plugins {
    id("rechenzentrum.java-conventions")
    id("com.gradleup.shadow")
}

dependencies {
    implementation(project(":api"))
    implementation(project(":common"))
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
        dependencies {
            include(dependency("${project.group}:.*"))
        }
        archiveClassifier.set("")
    }
}