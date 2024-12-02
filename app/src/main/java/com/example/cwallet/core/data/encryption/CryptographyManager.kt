package com.example.cwallet.core.data.encryption


import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class CryptographyManager {

    /**
     * Encrypts plaintext using a secret key from the Android Keystore.
     * @param plainText The data to encrypt.
     * @return The encrypted data in Base64-encoded format.
     */
    fun encrypt(plainText: String): String {
        val key = getOrCreateKey()
        val cipher = Cipher.getInstance(CRYPTO_TRANSFORMATION).apply {
            init(Cipher.ENCRYPT_MODE, key)
        }
        val iv = cipher.iv
        val encryptedBytes = cipher.doFinal(plainText.toByteArray())
        return Base64.encodeToString(iv + encryptedBytes, Base64.DEFAULT)
    }

    /**
     * Decrypts ciphertext using a secret key from the Android Keystore.
     * @param cipherText The Base64-encoded encrypted data.
     * @return The original plaintext.
     */
    fun decrypt(cipherText: String): String {
        val key = getOrCreateKey()
        val cipherBytes = Base64.decode(cipherText, Base64.DEFAULT)

        // Extract IV and encrypted data
        val iv = cipherBytes.copyOfRange(0, IV_SIZE)
        val encryptedData = cipherBytes.copyOfRange(IV_SIZE, cipherBytes.size)

        val cipher = Cipher.getInstance(CRYPTO_TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(AUTH_TAG_SIZE, iv))
        }
        return String(cipher.doFinal(encryptedData))
    }

    /**
     * Retrieves or creates the AES key stored in the Android Keystore.
     * @return The secret key for encryption/decryption.
     */
    private fun getOrCreateKey(): SecretKey {
        val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER).apply { load(null) }

        return if (keyStore.containsAlias(KEY_ALIAS)) {
            (keyStore.getEntry(KEY_ALIAS, null) as KeyStore.SecretKeyEntry).secretKey
        } else {
            createKey()
        }
    }

    /**
     * Creates a new AES key and stores it in the Android Keystore.
     * @return The newly created secret key.
     */
    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(CRYPTO_ALGORITHM, KEYSTORE_PROVIDER).apply {
            init(
                KeyGenParameterSpec.Builder(
                    KEY_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(CRYPTO_BLOCK_MODE)
                    .setEncryptionPaddings(CRYPTO_PADDING)
                    .setKeySize(KEY_SIZE)
                    .build()
            )
        }.generateKey()
    }

    companion object {
        // Keystore and Cryptography Constants
        private const val KEY_ALIAS = "mnemonic_key"
        private const val KEYSTORE_PROVIDER = "AndroidKeyStore"
        private const val CRYPTO_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val CRYPTO_BLOCK_MODE = KeyProperties.BLOCK_MODE_GCM
        private const val CRYPTO_PADDING = KeyProperties.ENCRYPTION_PADDING_NONE
        private const val CRYPTO_TRANSFORMATION =
            "$CRYPTO_ALGORITHM/$CRYPTO_BLOCK_MODE/$CRYPTO_PADDING"

        // Sizes
        private const val IV_SIZE = 12 // 12 bytes for AES-GCM IV
        private const val AUTH_TAG_SIZE = 128 // 16 bytes for AES-GCM authentication tag
        private const val KEY_SIZE = 256 // 256-bit AES key
    }
}

