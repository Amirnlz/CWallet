package com.amirnlz.cwallet.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Onboarding : Screen

    @Serializable
    data object WalletCreation : Screen

    @Serializable
    data object WalletRecovery : Screen

}
