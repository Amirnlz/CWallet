package com.amirnlz.wallet_creation.presentation.models

import androidx.compose.runtime.Stable


@Stable
data class MnemonicWordUiModel(
    val word: String,
    val index: Int
)