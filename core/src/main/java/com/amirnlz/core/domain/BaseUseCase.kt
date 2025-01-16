package com.amirnlz.core.domain


import android.content.SharedPreferences
import javax.inject.Inject

class SecureStorageManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveMnemonic(key: String, encryptedMnemonic: String) {
        sharedPreferences.edit().putString(key, encryptedMnemonic).apply()
    }

    fun getMnemonic(key: String): String? {
        return sharedPreferences.getString(key, null)
    }
}