/*
 * Copyright (c) Benjamin Selig, 2024, licensed under the EUPL
 */

rootProject.name = "Rechenzentrum"

include("api", "common", "paper")
rootProject.children.forEach { it.projectDir = file("modules/${it.name}") }