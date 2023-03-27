plugins {
    `java-library`
    kotlin("jvm")
}

group = "com.apsl.vehicle-service"
version = "0.0.1"

dependencies {
    implementation(Dependencies.Kotlin.coroutines)
}
