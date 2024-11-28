package com.example.cwallet.features.walletSetup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cwallet.features.walletSetup.domain.MnemonicRepository
import com.example.cwallet.features.walletSetup.domain.MnemonicWords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MnemonicViewModel @Inject constructor(private val repository: MnemonicRepository) :
    ViewModel() {

    private val _mnemonic = MutableStateFlow<MnemonicWords>(MnemonicWords(emptyList()))
    val mnemonic: StateFlow<MnemonicWords> = _mnemonic.onStart { generateMnemonic() }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        MnemonicWords(emptyList())
    )


    private fun generateMnemonic() {
        _mnemonic.value = repository.generateMnemonic()
    }

}