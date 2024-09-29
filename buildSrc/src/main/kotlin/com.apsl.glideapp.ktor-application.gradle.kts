import io.ktor.plugin.features.DockerPortMapping
import io.ktor.plugin.features.DockerPortMappingProtocol

plugins {
    application
    id("com.apsl.glideapp.kotlin-common")
    id("io.ktor.plugin")
}

application {
    mainClass = "${Config.group}.api.ApplicationKt"

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

ktor {
    fatJar {
        archiveFileName = "${rootProject.name}.jar"
    }
    docker {
        jreVersion = Config.javaVersion
        portMappings = listOf(
            DockerPortMapping(80, 8080, DockerPortMappingProtocol.TCP),
            DockerPortMapping(443, 8443, DockerPortMappingProtocol.TCP),
        )
    }
}

distributions {
    main {
        distributionBaseName = rootProject.name
    }
}

tasks {
    create("stage").dependsOn("installDist")
}
