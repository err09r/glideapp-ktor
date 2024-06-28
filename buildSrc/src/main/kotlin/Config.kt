@file:Suppress("ConstPropertyName")

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Config {
    const val group = "com.apsl.glideapp"
    const val version = "0.1.1"
    val javaVersion = JavaVersion.VERSION_11
    val jvmTarget = JvmTarget.JVM_11
}
