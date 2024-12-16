package com.amirnlz.onboarding.presentation.screens.generate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirnlz.core.common.Resource
import com.amirnlz.onboarding.domain.model.MnemonicPhrase
import com.amirnlz.onboarding.domain.repository.MnemonicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface MnemonicPhraseState {
    data object Loading : MnemonicPhraseState
    data class Success(val mnemonicPhrase: MnemonicPhrase) : MnemonicPhraseState
    data class Error(val message: String, val throwable: Throwable? = null) : MnemonicPhraseState
}

@HiltViewModel
class MnemonicPhraseGenerationViewModel @Inject constructor(private val repository: MnemonicRepository) :
    ViewModel() {

    private var _mnemonicPhraseState =
        MutableStateFlow<MnemonicPhraseState>(MnemonicPhraseState.Loading)
    val mnemonicPhraseState: StateFlow<MnemonicPhraseState> =
        _mnemonicPhraseState.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(2000L),
            MnemonicPhraseState.Loading,
        )


    fun generateMnemonicPhrases() {

        viewModelScope.launch {
            try {
                when (val phrases = repository.generateMnemonicPhrase()) {
                    is Resource.Success -> _mnemonicPhraseState.value =
                        MnemonicPhraseState.Success(phrases.data)

                    is Resource.Error -> _mnemonicPhraseState.value =
                        MnemonicPhraseState.Error(phrases.message)

                    else -> _mnemonicPhraseState.value = MnemonicPhraseState.Loading
                }
            } catch (e: Exception) {
                _mnemonicPhraseState.value = MnemonicPhraseState.Error(
                    message = "Failed to generate mnemonic phrase",
                    throwable = e,
                )
            }
        }

    }

}
