plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ktor.gradlePlugin)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
