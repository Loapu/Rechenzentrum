/*
 * Copyright (c) Benjamin Selig, 2024, licensed under the EUPL
 */

rootProject.name = "rechenzentrum-conventions"
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}