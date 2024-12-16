package com.amirnlz.onboarding.di

import android.content.Context
import com.amirnlz.core.data.encryption.CryptographyManager
import com.amirnlz.onboarding.data.datasource.MnemonicEncryptionDataSource
import com.amirnlz.onboarding.data.repository.MnemonicRepositoryImpl
import com.amirnlz.onboarding.domain.repository.MnemonicRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindOnboardingModule {
    @Binds
    @Singleton
    abstract fun bindMnemonicRepository(
        mnemonicRepositoryImpl: MnemonicRepositoryImpl
    ): MnemonicRepository

}


@Module
@InstallIn(SingletonComponent::class)
object OnboardingModule {
    @Provides
    @Singleton
    fun provideMnemonicEncryptionDataSource(
        cryptographyManager: CryptographyManager,
        @ApplicationContext context: Context
    ): MnemonicEncryptionDataSource {
        return MnemonicEncryptionDataSource(cryptographyManager, context)
    }
}