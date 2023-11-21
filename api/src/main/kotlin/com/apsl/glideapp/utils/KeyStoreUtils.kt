package com.apsl.glideapp.utils

import io.ktor.network.tls.certificates.KeyType
import io.ktor.network.tls.certificates.generateCertificate
import java.io.File

object KeyStoreUtils {

    private const val KEY_ALIAS = "alias"
    private const val KEY_PASSWORD = "password"
    private const val JKS_PASSWORD = "password"

    fun createKeyStoreFile() {
        val keyStoreFile = File("build/keystore.jks")
        generateCertificate(
            file = keyStoreFile,
            keyAlias = KEY_ALIAS,
            keyPassword = KEY_PASSWORD,
            jksPassword = JKS_PASSWORD,
            keyType = KeyType.Server
        )
    }
}
