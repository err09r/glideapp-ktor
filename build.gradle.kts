import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    alias(libs.plugins.gradleVersions)
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    rejectVersionIf {
        arrayOf("alpha", "beta", "rc", "dev").any {
            candidate.version.contains(it, ignoreCase = true)
        }
    }
    checkForGradleUpdate = true
    outputDir = layout.buildDirectory.dir("dependencyUpdates").get().toString()
    outputFormatter = Config.DependencyUpdates.outputFormatter
    reportfileName = Config.DependencyUpdates.reportfileName
}
