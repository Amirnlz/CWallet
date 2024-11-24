package com.example.cwallet.di

import com.example.cwallet.core.data.encryption.CryptographyManager
import com.example.cwallet.core.data.encryption.MnemonicUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMnemonicUtils(): MnemonicUtils = MnemonicUtils

    @Provides
    fun provideCryptographyManager(): CryptographyManager {
        return CryptographyManager()
    }
}