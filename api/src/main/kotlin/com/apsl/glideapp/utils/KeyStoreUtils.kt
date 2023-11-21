package com.apsl.glideapp.utils

import io.ktor.network.tls.certificates.buildKeyStore
import io.ktor.network.tls.certificates.saveToFile
import java.io.File

object KeyStoreUtils {

    private const val CERTIFICATE_ALIAS = "alias"
    private const val CERTIFICATE_PASSWORD = "password"
    private const val FILE_PASSWORD = "password"

    fun createKeyStoreFile() {
        val keyStoreFile = File("build/keystore.jks")
        val keyStore = buildKeyStore {
            certificate(CERTIFICATE_ALIAS) {
                password = CERTIFICATE_PASSWORD
                domains = listOf("127.0.0.1", "0.0.0.0", "localhost")
            }
        }
        keyStore.saveToFile(keyStoreFile, FILE_PASSWORD)
    }
}
