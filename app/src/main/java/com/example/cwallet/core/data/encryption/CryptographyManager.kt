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


    fun encryptMnemonic(plainText: String): String {
        val key = getKey()
        val cipher = Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.ENCRYPT_MODE, key)
        }
        val iv = cipher.iv
        val encryptedBytes = cipher.doFinal(plainText.toByteArray())
        return Base64.encodeToString(iv + encryptedBytes, Base64.DEFAULT)
    }

    fun decryptMnemonic(cipherText: String): String {
        val key = getKey()
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val cipherBytes = Base64.decode(cipherText, Base64.DEFAULT)

        val iv = cipherBytes.copyOfRange(0, 12)
        val encryptedData = cipherBytes.copyOfRange(12, cipherBytes.size)

        cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(128, iv))
        return String(cipher.doFinal(encryptedData))
    }


    private fun getKey(): SecretKey {

        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }

        return if (!keyStore.containsAlias(KEYSTORE_ALIAS)) {
            createKey()
        } else {
            (keyStore.getEntry(KEYSTORE_ALIAS, null) as KeyStore.SecretKeyEntry).secretKey
        }
    }

    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(ALGORITHM, ANDROID_KEYSTORE).apply {
            init(
                KeyGenParameterSpec.Builder(
                    KEYSTORE_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                ).setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setKeySize(SIZE)
                    .build()
            )
        }.generateKey()
    }

    companion object {
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_GCM
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_NONE
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
        private const val SIZE = 256

        private const val KEYSTORE_ALIAS = "mnemonic_key"
        private const val ANDROID_KEYSTORE = "AndroidKeyStore"
    }

}