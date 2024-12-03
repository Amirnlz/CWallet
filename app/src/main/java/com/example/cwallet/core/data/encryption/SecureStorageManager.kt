package com.example.cwallet.core.data.encryption

import android.content.Context

class SecureStorageManager(context: Context) {

//    TODO: can't get this dependency
//    private val sharedPreferences = EncryptedSharedPreferences.create(
//        "secure_wallet_prefs",
//        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
//        context,
//        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//    )

    fun saveMnemonic(key: String, encryptedMnemonic: String) {
//        sharedPreferences.edit().putString(key, encryptedMnemonic).apply()
    }

    fun getMnemonic(key: String): String? {
//        return sharedPreferences.getString(key, null)
        return  ""
    }
}
