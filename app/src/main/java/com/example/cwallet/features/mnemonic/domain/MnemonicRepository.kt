package com.example.cwallet.features.mnemonic.domain

interface MnemonicRepository {
    fun generateMnemonic(): MnemonicWords
    fun validateMnemonic(words: MnemonicWords): Boolean
}