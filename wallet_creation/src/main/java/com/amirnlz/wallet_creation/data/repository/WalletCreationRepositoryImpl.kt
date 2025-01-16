package com.amirnlz.wallet_creation.data.repository

import com.amirnlz.core.common.Resource
import com.amirnlz.wallet_creation.data.datasource.MnemonicEncryptionDataSource
import com.amirnlz.wallet_creation.domain.models.MnemonicPhrase
import com.amirnlz.wallet_creation.domain.repository.WalletCreationRepository
import javax.inject.Inject

class WalletCreationRepositoryImpl @Inject constructor(
    private val dataSource: MnemonicEncryptionDataSource
) :
    WalletCreationRepository {

    override fun createWalletMnemonicPhrase(): Resource<MnemonicPhrase> {
        try {
            val phrases = dataSource.getMnemonic()?.split(" ")

            return Resource.Success(MnemonicPhrase(phrases!!))

        } catch (e: Exception) {
            return Resource.Error(e.message.toString(), e)
        }
    }
}