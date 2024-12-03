package com.example.cwallet.features.walletSetup.presentation

import androidx.lifecycle.ViewModel
import com.example.cwallet.features.walletSetup.domain.MnemonicWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ValidateMnemonicViewModel : ViewModel() {

    private val _validationResult = MutableStateFlow<Boolean?>(null)
    val validationResult: StateFlow<Boolean?> = _validationResult


    private var _enteredMnemonic =
        MutableStateFlow<List<String>>(mutableListOf<String>().apply {
            repeat(12) { add("") }
        })
    val enteredMnemonic: StateFlow<List<String>> = _enteredMnemonic

    private var correctMnemonic: MnemonicWords = MnemonicWords(emptyList())

    fun setCorrectMnemonic(mnemonic: MnemonicWords) {
        correctMnemonic = correctMnemonic.apply { mnemonic }
    }

    fun onEnteringMnemonicWord(index: Int, word: String) {
        val updatedList = _enteredMnemonic.value.toMutableList()
        updatedList[index] = word
        _enteredMnemonic.value = updatedList
    }

    fun validateMnemonic() {
        _validationResult.value = enteredMnemonic == correctMnemonic.words
    }

    fun clearValidationResult() {
        _validationResult.value = null
    }
}
