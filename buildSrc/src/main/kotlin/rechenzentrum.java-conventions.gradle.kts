/*
 * Copyright (c) Benjamin Selig, 2024, licensed under the EUPL
 */

import org.gradle.kotlin.dsl.support.uppercaseFirstChar
import java.io.ByteArrayOutputStream

plugins {
    java
}

defaultTasks("build")

apply("${rootDir}/properties.gradle")

fun branch(): String {
    val os = ByteArrayOutputStream()
    project.exec {
        commandLine("git", "branch", "--show-current")
        standardOutput = os
    }
    val branch = String(os.toByteArray()).trim()
    return branch
}

fun fullVersion(): String {
    val suffix = if (branch().contains("release/")) "" else "-SNAPSHOT"
    return project.ext.get("apiVersion").toString() + "." + project.ext.get("patchVersion") + suffix
}

fun patchVersion(): Any {
    val tagInfo = ByteArrayOutputStream()
    exec {
        commandLine("git", "describe", "--tags")
        standardOutput = tagInfo
    }
    val tagInfoString = tagInfo.toString()
    if (!tagInfoString.contains('-')) return 0
    return tagInfoString.split('-')[1]
}

project.ext.set("patchVersion", patchVersion())
project.ext.set("apiVersion", "${project.ext.get("majorVersion")}.${project.ext.get("minorVersion")}")
project.ext.set("fullVersion", fullVersion())

group = project.ext.get("group").toString()
version = project.ext.get("fullVersion").toString()

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.codemc.io/repository/maven-snapshots/")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(project.ext.get("javaTargetVersion").toString()))
}

tasks {
    withType<JavaCompile> { options.encoding = Charsets.UTF_8.name() }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
        expand(
            "projectName" to rootProject.name,
            "projectVersion" to project.ext.get("fullVersion"),
            "projectGroup" to project.group,
            "projectDescription" to project.ext.get("description"),
            "projectWebsite" to project.ext.get("website"),
            "moduleName" to project.name,
            "projectAuthor" to project.ext.get("author"),
            "paperApiVersion" to project.ext.get("paperApiVersion"),
        )
    }
    base {
        archivesName.set("${rootProject.name.uppercaseFirstChar()}-${project.name.uppercaseFirstChar()}")
    }
}