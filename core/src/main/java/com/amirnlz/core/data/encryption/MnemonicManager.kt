package com.amirnlz.core.data.encryption


import org.web3j.crypto.MnemonicUtils
import java.security.SecureRandom


object MnemonicManager {

    // Generate a secure mnemonic phrase
    fun generateMnemonicPhrase(): String {
        val entropy = ByteArray(16) // 128 bits for 12 words
        SecureRandom().nextBytes(entropy)
        return MnemonicUtils.generateMnemonic(entropy)
    }

    // Validate a mnemonic phrase
    fun validateMnemonicPhrase(mnemonicPhrase: String): Boolean {
        return try {
            MnemonicUtils.validateMnemonic(mnemonicPhrase)
        } catch (e: Exception) {
            false
        }
    }
}