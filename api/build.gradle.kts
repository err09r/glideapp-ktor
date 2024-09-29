plugins {
    id("com.apsl.glideapp.ktor-application")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.exposed)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)

    implementation(libs.glideapp.common.dto)
    implementation(libs.glideapp.common.util)

    implementation(libs.logback.classic)
    implementation(libs.commons.codec)
    implementation(libs.postgresql)

    testImplementation(libs.ktor.server.tests.jvm)
    testImplementation(libs.kotlin.junit)
}
