package com.amirnlz.onboarding.data.repository

import com.amirnlz.core.common.Resource
import com.amirnlz.onboarding.data.datasource.MnemonicEncryptionDataSource
import com.amirnlz.onboarding.domain.model.MnemonicPhrase
import com.amirnlz.onboarding.domain.repository.MnemonicRepository
import javax.inject.Inject


class MnemonicRepositoryImpl @Inject constructor(
    private val dataSource: MnemonicEncryptionDataSource
) : MnemonicRepository {
    override fun generateMnemonicPhrase(): Resource<MnemonicPhrase> {
        return try {
            val phrases = dataSource.getMnemonic()?.split(" ")

            return Resource.Success(MnemonicPhrase(phrases!!))

        } catch (e: Exception) {
            return Resource.Error(e.message.toString(), e)
        }
    }
}
