package com.amirnlz.core

import com.amirnlz.core.data.encryption.CryptographyManager
import com.amirnlz.core.data.encryption.MnemonicManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideMnemonicManager(): MnemonicManager = MnemonicManager

    @Provides
    @Singleton
    fun provideCryptographyManager(): CryptographyManager = CryptographyManager()

}