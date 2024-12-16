package com.amirnlz.onboarding.domain.repository

import com.amirnlz.core.common.Resource
import com.amirnlz.onboarding.domain.model.MnemonicPhrase

interface MnemonicRepository {

    fun generateMnemonicPhrase(): Resource<MnemonicPhrase>
}