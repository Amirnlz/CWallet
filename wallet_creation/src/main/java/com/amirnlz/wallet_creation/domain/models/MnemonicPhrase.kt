package com.amirnlz.wallet_creation.domain.models


data class MnemonicPhrase(
    val words: List<String>,
    val language: MnemonicLanguage = MnemonicLanguage.ENGLISH
)

enum class MnemonicLanguage {
    ENGLISH, SPANISH, FRENCH
}