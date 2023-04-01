import io.ktor.plugin.features.DockerPortMapping
import io.ktor.plugin.features.DockerPortMappingProtocol

plugins {
    application
    id("io.ktor.plugin") version Versions.ktor
    kotlin("jvm")
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
    fatJar {
        archiveFileName.set("glideapp-ktor.jar")
    }
}

distributions {
    main {
        distributionBaseName.set(rootProject.name)
    }
}

dependencies {
    implementation(project(":vehicle-service"))

    implementation(Dependencies.Ktor.core)
    implementation(Dependencies.Ktor.netty)
    implementation(Dependencies.Ktor.contentNegotiation)
    implementation(Dependencies.Ktor.httpRedirect)
    implementation(Dependencies.Ktor.callLogging)
    implementation(Dependencies.Ktor.swagger)
    implementation(Dependencies.Ktor.auth)
    implementation(Dependencies.Ktor.authJwt)
    implementation(Dependencies.Ktor.serialization)

    implementation(Dependencies.Exposed.core)
    implementation(Dependencies.Exposed.dao)
    implementation(Dependencies.Exposed.jdbc)
    implementation(Dependencies.Exposed.kotlinDatetime)

    implementation(Dependencies.Kotlin.serializationJson)
    implementation(Dependencies.Kotlin.datetime)
    implementation(Dependencies.Kotlin.coroutines)

    implementation(Dependencies.Koin.ktor)
    implementation(Dependencies.Koin.logger)

    implementation(Dependencies.glideAppCommonDto)
    implementation(Dependencies.glideAppCommonUtil)

    implementation(Dependencies.postgresql)
    implementation(Dependencies.logback)
    implementation(Dependencies.commonsCodec)

    testImplementation(Dependencies.Ktor.serverTests)
    testImplementation(Dependencies.Kotlin.junit)
}

tasks {
    create("stage").dependsOn("installDist")
}
