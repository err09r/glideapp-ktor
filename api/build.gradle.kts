import io.ktor.plugin.features.DockerPortMapping
import io.ktor.plugin.features.DockerPortMappingProtocol

plugins {
    application
    id("io.ktor.plugin") version Versions.ktor
    kotlin("jvm") version Versions.kotlin
    kotlin("plugin.serialization") version Versions.kotlin
}

group = Config.group
version = Config.version

application {
    mainClass.set("${Config.group}.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

ktor {
    docker {
        portMappings.set(listOf(DockerPortMapping(80, 8080, DockerPortMappingProtocol.TCP)))
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(Dependencies.Ktor.core)
    implementation(Dependencies.Ktor.netty)
    implementation(Dependencies.Ktor.contentNegotiation)
    implementation(Dependencies.Ktor.httpRedirect)
    implementation(Dependencies.Ktor.callLogging)
    implementation(Dependencies.Ktor.swagger)
    implementation(Dependencies.Ktor.auth)
    implementation(Dependencies.Ktor.authJwt)

    implementation(Dependencies.Exposed.core)
    implementation(Dependencies.Exposed.dao)
    implementation(Dependencies.Exposed.jdbc)
    implementation(Dependencies.Exposed.kotlinDatetime)

    implementation(Dependencies.Ktor.serialization)
    implementation(Dependencies.Kotlin.serializationJson)
    implementation(Dependencies.Kotlin.datetime)

    implementation(Dependencies.Koin.ktor)
    implementation(Dependencies.Koin.logger)

    implementation(Dependencies.postgresql)
    implementation(Dependencies.logback)
    implementation(Dependencies.commonsCodec)

    testImplementation(Dependencies.Ktor.serverTests)
    testImplementation(Dependencies.Kotlin.junit)
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = Config.jvmTarget
            freeCompilerArgs += listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xopt-in=kotlin.time.ExperimentalTime",
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
            )
        }
    }
}
