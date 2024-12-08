package com.amirnlz.onboarding.presentation.screens.generate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirnlz.onboarding.domain.model.MnemonicPhrase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


sealed interface MnemonicPhraseState {
    data object Loading : MnemonicPhraseState
    data class Success(val mnemonicPhrase: MnemonicPhrase) : MnemonicPhraseState
    data class Error(val message: String, val throwable: Throwable? = null) : MnemonicPhraseState
}

class MnemonicPhraseGenerationViewModel : ViewModel() {

    private var _mnemonicPhraseState =
        MutableStateFlow<MnemonicPhraseState>(MnemonicPhraseState.Loading)
    val mnemonicPhraseState: StateFlow<MnemonicPhraseState> =
        _mnemonicPhraseState.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(2000L),
            MnemonicPhraseState.Loading,
        )

    init {
        generateMnemonicPhrases()
    }

    fun generateMnemonicPhrases() {

        viewModelScope.launch {
            try {

            } catch (e: Exception) {
                _mnemonicPhraseState.value = MnemonicPhraseState.Error(
                    message = "Failed to generate mnemonic phrase",
                    throwable = e,
                )
            }
        }

    }

}
