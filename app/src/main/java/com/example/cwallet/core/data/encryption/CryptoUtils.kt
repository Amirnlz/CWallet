package com.example.cwallet.core.data.encryption

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.SecureRandom
import java.security.Signature

object CryptoUtils {

    // Generates a BIP-39 mnemonic phrase
    fun generateMnemonicPhrase(): List<String> {
        val entropy = ByteArray(16) // 128-bit entropy
        SecureRandom().nextBytes(entropy)

        return MnemonicUtils.generateMnemonic(entropy).split(" ")
    }

    // Hashing example using SHA-256
    fun sha256(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(input.toByteArray(StandardCharsets.UTF_8))
        return hash.joinToString("") { "%02x".format(it) }
    }

    // Signs a transaction with a private key (for demonstration)
    fun signTransaction(transactionData: String, privateKey: PrivateKey): ByteArray {
        val signature = Signature.getInstance("SHA256withECDSA")
        signature.initSign(privateKey)
        signature.update(transactionData.toByteArray(StandardCharsets.UTF_8))
        return signature.sign()
    }

}