package com.amirnlz.wallet_creation


import android.content.Context
import com.amirnlz.core.data.encryption.CryptographyManager
import com.amirnlz.core.data.encryption.MnemonicManager
import com.amirnlz.wallet_creation.data.datasource.MnemonicEncryptionDataSource
import com.amirnlz.wallet_creation.data.repository.WalletCreationRepositoryImpl
import com.amirnlz.wallet_creation.domain.repository.WalletCreationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindWalletCreationModule {
    @Binds
    @ViewModelScoped
    abstract fun bindWalletCreationRepository(
        mnemonicRepositoryImpl: WalletCreationRepositoryImpl
    ): WalletCreationRepository
}


@Module
@InstallIn(ViewModelComponent::class)
object WalletCreationModule {
    @Provides
    @ViewModelScoped
    fun provideMnemonicEncryptionDataSource(
        cryptographyManager: CryptographyManager,
        mnemonicManager: MnemonicManager,
        @ApplicationContext context: Context
    ): MnemonicEncryptionDataSource {
        return MnemonicEncryptionDataSource(cryptographyManager, mnemonicManager, context)
    }
}