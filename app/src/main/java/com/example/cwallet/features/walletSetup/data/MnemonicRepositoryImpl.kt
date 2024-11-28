package com.example.cwallet.features.walletSetup.data

import com.example.cwallet.core.data.encryption.MnemonicManager
import com.example.cwallet.features.walletSetup.domain.MnemonicRepository
import com.example.cwallet.features.walletSetup.domain.MnemonicWords
import javax.inject.Inject


class MnemonicRepositoryImpl @Inject constructor(
    private val mnemonicManager: MnemonicManager,
    private val localMnemonicStorage: LocalMnemonicStorage
) : MnemonicRepository {


    override fun generateMnemonic(): MnemonicWords {
        val words = mnemonicManager.generateMnemonicPhrase().split(" ")
        return MnemonicWords(words)
    }

    override fun validateMnemonic(words: MnemonicWords): Boolean {
        return mnemonicManager.validateMnemonicPhrase(words.words.joinToString(" "))
    }
}