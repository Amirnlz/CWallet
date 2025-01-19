package com.amirnlz.wallet_creation.data.repository

import android.util.Log
import com.amirnlz.core.common.Resource
import com.amirnlz.wallet_creation.data.datasource.MnemonicEncryptionDataSource
import com.amirnlz.wallet_creation.domain.models.MnemonicPhrase
import com.amirnlz.wallet_creation.domain.repository.WalletCreationRepository
import javax.inject.Inject

class WalletCreationRepositoryImpl @Inject constructor(
    private val dataSource: MnemonicEncryptionDataSource
) :
    WalletCreationRepository {

    override suspend fun createWalletMnemonicPhrase(): Resource<MnemonicPhrase> {
        return try {
            val phrases = dataSource.retrieveOrGenerateMnemonic()
                .split(" ")
                .let { MnemonicPhrase(it) }
            Log.v("1# REPO", phrases.words.size.toString())
            Log.v("2# REPO", phrases.words.toString())

            Resource.Success(phrases)

        } catch (e: Exception) {
            Resource.Error(e.message.orEmpty(), e)
        }
    }
}