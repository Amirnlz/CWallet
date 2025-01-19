package com.amirnlz.core.data.encryption


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.web3j.crypto.MnemonicUtils
import java.security.SecureRandom


object MnemonicManager {

    /**
     * Generate a secure mnemonic phrase, typically 12 words (128-bit entropy).
     */
    suspend fun generateMnemonicPhrase(): String = withContext(Dispatchers.Default) {
        val entropy = ByteArray(16) // 128 bits for 12 words
        SecureRandom().nextBytes(entropy)
        MnemonicUtils.generateMnemonic(entropy)
    }

    /**
     * Validate a mnemonic phrase for correctness.
     */
    suspend fun validateMnemonicPhrase(mnemonicPhrase: String): Boolean =
        withContext(Dispatchers.Default) {
            try {
                MnemonicUtils.validateMnemonic(mnemonicPhrase)
                true
            } catch (e: Exception) {
                false
            }
        }
}
