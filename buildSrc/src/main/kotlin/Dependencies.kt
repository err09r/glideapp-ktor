object Dependencies {
    const val glideAppCommonDto = "com.github.err09r.glideapp-common:dto:${Versions.glideAppCommon}"
    const val glideAppCommonUtil = "com.github.err09r.glideapp-common:util:${Versions.glideAppCommon}"
    const val postgresql = "org.postgresql:postgresql:${Versions.postgresql}"
    const val logback = "ch.qos.logback:logback-classic:${Versions.logback}"
    const val commonsCodec = "commons-codec:commons-codec:${Versions.commonsCodec}"

    object Ktor {
        const val core = "io.ktor:ktor-server-core-jvm:${Versions.ktor}"
        const val netty = "io.ktor:ktor-server-netty-jvm:${Versions.ktor}"
        const val contentNegotiation = "io.ktor:ktor-server-content-negotiation:${Versions.ktor}"
        const val httpRedirect = "io.ktor:ktor-server-http-redirect-jvm:${Versions.ktor}"
        const val callLogging = "io.ktor:ktor-server-call-logging-jvm:${Versions.ktor}"
        const val swagger = "io.ktor:ktor-server-swagger:${Versions.ktor}"
        const val auth = "io.ktor:ktor-server-auth:${Versions.ktor}"
        const val authJwt = "io.ktor:ktor-server-auth-jwt:${Versions.ktor}"
        const val serverTests = "io.ktor:ktor-server-tests-jvm:${Versions.ktor}"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        const val websockets = "io.ktor:ktor-server-websockets:${Versions.ktor}"
    }

    object Kotlin {
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.Kotlin.datetime}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
        const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
        const val serializationJson =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.serialization}"
    }

    object Exposed {
        const val core = "org.jetbrains.exposed:exposed-core:${Versions.exposed}"
        const val dao = "org.jetbrains.exposed:exposed-dao:${Versions.exposed}"
        const val jdbc = "org.jetbrains.exposed:exposed-jdbc:${Versions.exposed}"
        const val kotlinDatetime = "org.jetbrains.exposed:exposed-kotlin-datetime:${Versions.exposed}"
    }

    object Koin {
        const val ktor = "io.insert-koin:koin-ktor:${Versions.koin}"
        const val logger = "io.insert-koin:koin-logger-slf4j:${Versions.koin}"
    }
}
