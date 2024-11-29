package com.example.cwallet.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object SetupScreen : Screen

    @Serializable
    data object GenerateMnemonicScreen : Screen

    @Serializable
    data object ValidateMnemonicScreen : Screen
}