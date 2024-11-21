/*
 * Copyright (c) Benjamin Selig, 2024, licensed under the EUPL
 */

import gradle.kotlin.dsl.accessors._8c47cae829ea3d03260d5ff13fb2398e.build

task("copyArtifacts", type = Copy::class) {
    from("build/libs")
    into("${rootDir}/output")
}

tasks {
    afterEvaluate {
        build.get().finalizedBy(tasks.findByName("copyArtifacts"))
    }
}