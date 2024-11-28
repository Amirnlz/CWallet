package com.example.cwallet.features.walletSetup.domain

interface MnemonicRepository {
    fun generateMnemonic(): MnemonicWords
    fun validateMnemonic(words: MnemonicWords): Boolean
}