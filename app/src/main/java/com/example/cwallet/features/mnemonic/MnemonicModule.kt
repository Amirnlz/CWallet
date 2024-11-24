package com.example.cwallet.features.mnemonic

import com.example.cwallet.features.mnemonic.data.MnemonicRepositoryImpl
import com.example.cwallet.features.mnemonic.domain.MnemonicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MnemonicBindsModule {

    @Singleton
    @Binds
    abstract fun bindMnemonicRepository(
        mnemonicRepositoryImpl: MnemonicRepositoryImpl
    ): MnemonicRepository
}


