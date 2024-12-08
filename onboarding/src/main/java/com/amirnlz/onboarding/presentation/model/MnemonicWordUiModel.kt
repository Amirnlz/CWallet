package com.amirnlz.onboarding.presentation.model

import androidx.compose.runtime.Stable


@Stable
data class MnemonicWordUiModel(
    val word: String,
    val index: Int
)
