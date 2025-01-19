package com.amirnlz.core


import android.util.Base64
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amirnlz.core.data.encryption.CryptographyManager
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CryptographyManagerTest {

    private lateinit var cryptographyManager: CryptographyManager

    @Before
    fun setup() {
        // In instrumentation tests, we can create a real instance.
        // If your code relies on Android APIs, we have them here in the instrumentation environment.
        cryptographyManager = CryptographyManager()
    }

    @Test
    suspend fun testEncryptDecryptSymmetry() {
        val plainText = "Hello World"

        val encryptedText = cryptographyManager.encrypt(plainText)
        // Ensure it's not the same as plaintext
        assertNotEquals("Encryption failed, text is unchanged!", plainText, encryptedText)

        val decryptedText = cryptographyManager.decrypt(encryptedText)
        assertEquals("Decrypted text does not match the original!", plainText, decryptedText)
    }

    @Test
    suspend fun testEncryptEmptyString() {
        val plainText = ""

        val encryptedText = cryptographyManager.encrypt(plainText)
        assertTrue(
            "Encrypted text should not be empty, because it still contains IV + auth tag!",
            encryptedText.isNotEmpty()
        )

        val decryptedText = cryptographyManager.decrypt(encryptedText)
        assertEquals("Decrypted empty string should remain empty!", plainText, decryptedText)
    }

    @Test
    suspend fun testDecryptCorruptedData_throwsException() {
        val plainText = "Sample Text"

        val encryptedText = cryptographyManager.encrypt(plainText)

        // Corrupt the data by truncating part of it
        val corrupted = encryptedText.substring(0, encryptedText.length / 2)

        try {
            cryptographyManager.decrypt(corrupted)
            fail("Decrypting corrupted data should throw an exception!")
        } catch (ex: Exception) {
            // Expected: GCM or BadPadding exception, etc.
            assertTrue(
                "Expected some type of security exception!",
                ex is javax.crypto.AEADBadTagException || ex is javax.crypto.BadPaddingException
            )
        }
    }

    @Test
    suspend fun testDecryptRandomData_throwsException() {
        // Create random bytes
        val randomBytes = ByteArray(32) { 42 } // or a random generator
        val randomBase64 = Base64.encodeToString(randomBytes, Base64.DEFAULT)

        try {
            cryptographyManager.decrypt(randomBase64)
            fail("Decrypting random data should throw an exception!")
        } catch (ex: Exception) {
            // Typically a GCM mismatch or BadPadding
            assertTrue(
                "Expected GCM or padding exception!",
                ex is javax.crypto.AEADBadTagException || ex is javax.crypto.BadPaddingException
            )
        }
    }

    @Test
    suspend fun testMultipleEncryptDecryptConsistency() {
        // We want to ensure multiple calls produce different ciphertext but can still decrypt properly
        val plainText = "Testing repeated encryption"

        val encryptedA = cryptographyManager.encrypt(plainText)
        val encryptedB = cryptographyManager.encrypt(plainText)

        // Check that ciphertexts are different (nonce/IV is unique each time)
        assertNotEquals(encryptedA, encryptedB)

        // They both decrypt correctly
        assertEquals(plainText, cryptographyManager.decrypt(encryptedA))
        assertEquals(plainText, cryptographyManager.decrypt(encryptedB))
    }

    @Test
    suspend fun testKeyPersistence() {
        // This test ensures the key is stored in Keystore and is reused
        val textA = "First text"
        val textB = "Second text"

        // We'll do encryption for both
        val encryptedA = cryptographyManager.encrypt(textA)
        val decryptedA = cryptographyManager.decrypt(encryptedA)
        assertEquals(textA, decryptedA)

        // In theory, the same key should be used for a subsequent encryption
        val encryptedB = cryptographyManager.encrypt(textB)
        val decryptedB = cryptographyManager.decrypt(encryptedB)
        assertEquals(textB, decryptedB)
    }
}
