import io.ktor.plugin.features.DockerPortMapping
import io.ktor.plugin.features.DockerPortMappingProtocol

plugins {
    application
    id("com.apsl.glideapp.kotlin-common")
    id("io.ktor.plugin")
}

application {
    mainClass.set("${Config.group}.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

ktor {
    fatJar {
        archiveFileName.set("glideapp-ktor.jar")
    }
    docker {
        portMappings.set(listOf(DockerPortMapping(80, 8080, DockerPortMappingProtocol.TCP)))
    }

}

distributions {
    main {
        distributionBaseName.set(rootProject.name)
    }
}
