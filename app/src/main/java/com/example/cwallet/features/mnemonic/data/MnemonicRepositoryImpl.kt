package com.example.cwallet.features.mnemonic.data

import com.example.cwallet.core.data.encryption.MnemonicUtils
import com.example.cwallet.features.mnemonic.domain.MnemonicRepository
import com.example.cwallet.features.mnemonic.domain.MnemonicWords

class MnemonicRepositoryImpl(
    private val mnemonicUtils: MnemonicUtils,
    private val localMnemonicStorage: LocalMnemonicStorage
) : MnemonicRepository {


    override fun generateMnemonic(): MnemonicWords {
        val words = mnemonicUtils.generateMnemonic().split(" ")
        return MnemonicWords(words)
    }

    override fun validateMnemonic(words: MnemonicWords): Boolean {
        return mnemonicUtils.validateMnemonic(words.words.joinToString(" "))
    }
}