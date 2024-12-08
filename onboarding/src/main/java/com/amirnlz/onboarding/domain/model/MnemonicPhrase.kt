package com.amirnlz.onboarding.domain.model

data class MnemonicPhrase(
    val words: List<String>,
    val language: MnemonicLanguage = MnemonicLanguage.ENGLISH
)

enum class MnemonicLanguage {
    ENGLISH, SPANISH, FRENCH
}