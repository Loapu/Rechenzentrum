/*
 * Copyright (c) Benjamin Selig, 2024, licensed under the EUPL
 */

plugins {
    alias(libs.plugins.conventions.java)
    alias(libs.plugins.conventions.export)
    `java-library`
}

dependencies {
    implementation(libs.bundles.adventure)
}

tasks {
    jar {
        archiveFileName.set("${rootProject.name.lowercase()}-${project.name.lowercase()}-${project.ext.get("apiVersion")}.jar")
    }
}