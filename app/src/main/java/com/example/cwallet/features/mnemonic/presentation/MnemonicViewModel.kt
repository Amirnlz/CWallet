package com.example.cwallet.features.mnemonic.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cwallet.features.mnemonic.domain.MnemonicWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class MnemonicViewModel() : ViewModel() {

    private val _mnemonic = MutableStateFlow<MnemonicWords>(MnemonicWords(emptyList()))
    val mnemonic: StateFlow<MnemonicWords> = _mnemonic.onStart { generateMnemonic() }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        MnemonicWords(emptyList())
    )


    private fun generateMnemonic() {}

}