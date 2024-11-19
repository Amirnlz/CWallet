package com.example.cwallet.core.data.encryption

import org.bitcoinj.crypto.MnemonicCode
import java.security.SecureRandom

object MnemonicUtils {

    fun generateMnemonic(entropy: ByteArray = ByteArray(16)): String {
        SecureRandom().nextBytes(entropy) //Generate random 128-bit entropy (16 bytes)

        return MnemonicCode.INSTANCE.toMnemonic(entropy).joinToString(" ")
    }

    fun validateMnemonic(mnemonic: String): Boolean {
        return try {
            MnemonicCode.INSTANCE.check(mnemonic.split(" "))
            true
        } catch (e: Exception) {
            false
        }
    }


    fun mnemonicToSeed(mnemonic: String, passPhrase: String = ""): ByteArray {
        return MnemonicCode.toSeed(mnemonic.split(" "), passPhrase)
    }
}