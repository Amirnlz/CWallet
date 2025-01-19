package com.amirnlz.wallet_creation.presentation.screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirnlz.core.common.Resource
import com.amirnlz.wallet_creation.domain.models.MnemonicPhrase
import com.amirnlz.wallet_creation.domain.repository.WalletCreationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed interface MnemonicPhraseState {
    data object Loading : MnemonicPhraseState
    data class Success(val mnemonicPhrase: MnemonicPhrase) : MnemonicPhraseState
    data class Error(val message: String, val throwable: Throwable? = null) : MnemonicPhraseState
}

@HiltViewModel
class WalletCreationViewModel @Inject constructor
    (private val repository: WalletCreationRepository) : ViewModel() {

    private var _mnemonicPhraseState =
        MutableStateFlow<MnemonicPhraseState>(MnemonicPhraseState.Loading)
    val mnemonicPhraseState: StateFlow<MnemonicPhraseState> =
        _mnemonicPhraseState.onStart { generateMnemonicPhrases() }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(2000L),
            MnemonicPhraseState.Loading,
        )


    fun generateMnemonicPhrases() {
        viewModelScope.launch {
            _mnemonicPhraseState.value = MnemonicPhraseState.Loading

            try {
                when (val phrases = repository.createWalletMnemonicPhrase()) {
                    is Resource.Success ->
                        _mnemonicPhraseState.value =
                            MnemonicPhraseState.Success(phrases.data)


                    is Resource.Error -> _mnemonicPhraseState.value =
                        MnemonicPhraseState.Error(phrases.message)
                }
            } catch (e: Exception) {
                _mnemonicPhraseState.value = MnemonicPhraseState.Error(
                    message = "Failed to generate mnemonic phrase",
                    throwable = e,
                )
            }
        }
    }

    fun validateWords(userWords: List<String>): Boolean {
        val currentState = _mnemonicPhraseState.value
        return if (currentState is MnemonicPhraseState.Success) {
            currentState.mnemonicPhrase.words == userWords

        } else {
            false
        }

    }
}