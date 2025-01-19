package com.amirnlz.core

import com.amirnlz.core.data.encryption.MnemonicManager
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class MnemonicManagerTest {

    @Test
    fun generateMnemonicPhrase_returnsValidMnemonic(): Unit = runTest {
        val mnemonic = MnemonicManager.generateMnemonicPhrase()
        assertNotNull("Generated mnemonic should not be null", mnemonic)
        assertTrue("Generated mnemonic should not be empty", mnemonic.isNotEmpty())

        // Optionally check word count. Typically 12 or 15/18/21/24 words for BIP39
        val words = mnemonic.split(" ")
        assertEquals("Expected 12 words in the mnemonic", 12, words.size)

        // Validate the generated mnemonic
        val isValid = MnemonicManager.validateMnemonicPhrase(mnemonic)
        assertTrue("Generated mnemonic should be valid", isValid)
    }

    @Test
    fun validateMnemonicPhrase_validPhrase_returnsTrue(): Unit = runTest {
        // This is an example BIP-39 12-word test vector
        // Make sure it's valid according to your BIP39 library
        val validMnemonic =
            "legal winner thank year wave sausage worth useful legal winner thank yellow"

        val isValid = MnemonicManager.validateMnemonicPhrase(validMnemonic)
        assertTrue("Expected this mnemonic to be valid", isValid)
    }

    @Test
    fun validateMnemonicPhrase_invalidPhrase_returnsFalse(): Unit = runTest {
        val invalidMnemonic = "this is definitely not a valid mnemonic phrase"

        val isValid = MnemonicManager.validateMnemonicPhrase(invalidMnemonic)
        assertFalse("Expected this mnemonic to be invalid", isValid)
    }
}
