/*
 * Copyright (c) Benjamin Selig, 2024, licensed under the EUPL
 */

plugins {
    alias(libs.plugins.conventions.java)
}

dependencies {
    implementation(project(":api"))
    implementation(libs.bundles.adventure)
}