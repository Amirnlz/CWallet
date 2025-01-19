package com.amirnlz.wallet_creation.data.datasource


import android.content.Context
import com.amirnlz.core.data.encryption.CryptographyManager
import com.amirnlz.core.data.encryption.MnemonicManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MnemonicEncryptionDataSource @Inject constructor(
    private val cryptographyManager: CryptographyManager,
    private val mnemonicManager: MnemonicManager,
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val MNEMONIC_FILE_NAME = "encrypted_mnemonic"
    }

    suspend fun retrieveOrGenerateMnemonic(): String = withContext(Dispatchers.IO) {
        // Try to get the saved mnemonic
        val existingMnemonic = getMnemonicInternal()
        if (existingMnemonic != null) {
            return@withContext existingMnemonic
        }

        // If not found, generate a new one
        val newMnemonic = mnemonicManager.generateMnemonicPhrase()
        saveMnemonic(newMnemonic)
        return@withContext newMnemonic
    }

    private suspend fun getMnemonicInternal(): String? {
        val file = context.getFileStreamPath(MNEMONIC_FILE_NAME) ?: return null
        if (!file.exists()) return null

        val encryptedMnemonic = file.readBytes().decodeToString()
        return cryptographyManager.decrypt(encryptedMnemonic)
    }

    private suspend fun saveMnemonic(mnemonic: String) = withContext(Dispatchers.IO) {
        val encryptedMnemonic = cryptographyManager.encrypt(mnemonic)
        context.openFileOutput(MNEMONIC_FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(encryptedMnemonic.toByteArray())
        }
    }

    suspend fun deleteMnemonic() = withContext(Dispatchers.IO) {
        context.deleteFile(MNEMONIC_FILE_NAME)
    }
}
