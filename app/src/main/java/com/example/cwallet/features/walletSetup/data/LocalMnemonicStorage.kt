package com.example.cwallet.features.walletSetup.data

import android.content.Context
import com.example.cwallet.core.data.encryption.CryptographyManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalMnemonicStorage @Inject constructor(
    private val cryptographyManager: CryptographyManager,
    @ApplicationContext private val context: Context
) {


    companion object {
        private const val MNEMONIC_FILE_NAME = "encrypted_mnemonic"
    }

    fun saveMnemonic(mnemonic: String) {
        val encryptedMnemonic = cryptographyManager.encrypt(mnemonic)
        context.openFileOutput(MNEMONIC_FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(encryptedMnemonic.toByteArray())
        }
    }

    fun getMnemonic(): String? {
        val file = context.getFileStreamPath(MNEMONIC_FILE_NAME) ?: return null
        if (!file.exists()) return null

        val encryptedMnemonic = file.readBytes().decodeToString()
        return cryptographyManager.decrypt(encryptedMnemonic)
    }

    fun deleteMnemonic() {
        context.deleteFile(MNEMONIC_FILE_NAME)
    }
}