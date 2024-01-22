@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "glideapp-ktor"
include(":api")
