/*
 * Copyright (c) Benjamin Selig, 2024, licensed under the EUPL
 */

plugins {
    alias(libs.plugins.conventions.shadow)
    alias(libs.plugins.conventions.export)
    alias(libs.plugins.paperweight.userdev)
}

dependencies {
    paperweight.paperDevBundle(libs.paper.get().version)
}

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION