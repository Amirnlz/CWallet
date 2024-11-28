package com.example.cwallet.features.walletSetup

import com.example.cwallet.features.walletSetup.data.MnemonicRepositoryImpl
import com.example.cwallet.features.walletSetup.domain.MnemonicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WalletSetupBindsModule {

    @Singleton
    @Binds
    abstract fun bindMnemonicRepository(
        mnemonicRepositoryImpl: MnemonicRepositoryImpl
    ): MnemonicRepository
}


