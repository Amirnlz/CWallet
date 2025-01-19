package com.amirnlz.core


import com.amirnlz.core.data.encryption.CryptographyManager
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CryptographyManagerMockTest {

    @Test
    fun `encrypt returns stubbed value`() = runTest {
        // Given a mocked CryptographyManager
        val mockManager = mock(CryptographyManager::class.java)

        // Stub encrypt(...) to return a fake value
        `when`(mockManager.encrypt(anyString())).thenReturn("fake_encrypted")

        // When we call encrypt(...)
        val result = mockManager.encrypt("Hello World")

        // Then we get our stubbed result
        assertEquals("fake_encrypted", result)

        // And we verify the method was called exactly once
        verify(mockManager, times(1)).encrypt("Hello World")
    }

    @Test
    fun `decrypt returns stubbed value`() = runTest {
        val mockManager = mock(CryptographyManager::class.java)

        `when`(mockManager.decrypt(anyString())).thenReturn("fake_decrypted")

        val result = mockManager.decrypt("some_cipher_text")

        assertEquals("fake_decrypted", result)
        verify(mockManager).decrypt("some_cipher_text")
    }
}
