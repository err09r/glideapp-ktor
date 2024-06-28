plugins {
    kotlin("jvm")
}

group = Config.group
version = Config.version

java {
    targetCompatibility = Config.javaVersion
    sourceCompatibility = Config.javaVersion
}

kotlin {
    compilerOptions {
        jvmTarget.set(Config.jvmTarget)
        freeCompilerArgs.addAll(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlin.time.ExperimentalTime",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
        )
    }
}
