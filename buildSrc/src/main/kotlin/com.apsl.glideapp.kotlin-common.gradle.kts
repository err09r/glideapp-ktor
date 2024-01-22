import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

group = Config.group
version = Config.version

tasks.withType<JavaCompile> {
    targetCompatibility = Config.javaVersion
    sourceCompatibility = Config.javaVersion
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = Config.javaVersion
        freeCompilerArgs += listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlin.time.ExperimentalTime",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
        )
    }
}
