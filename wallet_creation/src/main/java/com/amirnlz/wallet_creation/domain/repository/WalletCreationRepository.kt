package com.amirnlz.wallet_creation.domain.repository

import com.amirnlz.core.common.Resource
import com.amirnlz.wallet_creation.domain.models.MnemonicPhrase

interface WalletCreationRepository {

    fun createWalletMnemonicPhrase(): Resource<MnemonicPhrase>
}