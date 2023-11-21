package com.apsl.glideapp.features.auth.security.hashing

import java.security.SecureRandom
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils

object SHA256HashingService : HashingService {

    private const val ALGORITHM = "SHA1PRNG"

    override fun generateSaltedHash(value: String, length: Int): SaltedHash {
        val salt = SecureRandom.getInstance(ALGORITHM).generateSeed(length)
        val saltAsHex = Hex.encodeHexString(salt)
        val hash = DigestUtils.sha256Hex("$saltAsHex$value")
        return SaltedHash(hash = hash, salt = saltAsHex)
    }

    override fun verify(value: String, saltedHash: SaltedHash): Boolean {
        return DigestUtils.sha256Hex(saltedHash.salt + value) == saltedHash.hash
    }
}
