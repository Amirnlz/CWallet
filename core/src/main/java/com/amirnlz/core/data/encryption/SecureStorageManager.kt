package com.amirnlz.core.data.encryption


import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SecureStorageManager(context: Context) {

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "secure_wallet_prefs",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveMnemonic(key: String, encryptedMnemonic: String) {
        sharedPreferences.edit().putString(key, encryptedMnemonic).apply()
    }

    fun getMnemonic(key: String): String? {
        return sharedPreferences.getString(key, null)
        return ""
    }
}