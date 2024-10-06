import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    alias(libs.plugins.gradleVersions)
}

/*
    Gradle Versions Plugin
    https://github.com/ben-manes/gradle-versions-plugin
    Use `./gradlew dependencyUpdates` or run `Check dependency updates` configuration
 */
tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    rejectVersionIf {
        isStable(currentVersion) && !isStable(candidate.version)
    }
    checkForGradleUpdate = true
    outputDir = "build/dependencyUpdates"
    outputFormatter = "html"
    reportfileName = "report"
}

private fun isStable(version: String): Boolean {
    return listOf("alpha", "beta", "rc", "dev").none {
        version.contains(it, ignoreCase = true)
    }
}
