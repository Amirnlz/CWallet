package com.amirnlz.cwallet.navigation


sealed interface Screen {

    @Serializable
    data object Welcome : Screen

    @Serializable
    data object MnemonicGeneration : Screen

    @Serializable
    data object MnemonicPhraseRecovery : Screen
}
