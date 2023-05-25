plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ktor.gradlePlugin)
    implementation(libs.kotlinx.serialization.gradlePlugin)

    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
