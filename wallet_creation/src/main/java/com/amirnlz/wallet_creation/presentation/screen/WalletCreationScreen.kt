package com.amirnlz.wallet_creation.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amirnlz.wallet_creation.R
import com.amirnlz.wallet_creation.domain.models.MnemonicPhrase
import com.amirnlz.wallet_creation.presentation.component.MnemonicPhraseGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletCreationScreen(
    modifier: Modifier = Modifier,
    viewModel: WalletCreationViewModel,
    navigateValidationScreen: () -> Unit
) {
    val mnemonicPhraseState by remember { viewModel.mnemonicPhraseState }
        .collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(R.string.secret_words)) },

            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        )
    }) { paddingValues ->
        Box(
            modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (val state = mnemonicPhraseState) {
                is MnemonicPhraseState.Loading -> CircularProgressIndicator()
                is MnemonicPhraseState.Success -> MnemonicPhraseSuccess(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    mnemonicPhrase = state.mnemonicPhrase,
                    navigateValidationScreen = navigateValidationScreen
                )

                is MnemonicPhraseState.Error -> ErrorContent(
                    message = state.message,
                    onRetry = { viewModel.generateMnemonicPhrases() }
                )
            }
        }

    }
}


@Composable
fun MnemonicPhraseSuccess(
    modifier: Modifier = Modifier,
    mnemonicPhrase: MnemonicPhrase,
    navigateValidationScreen: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.write_12_secret_words),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
        MnemonicPhraseGrid(mnemonicPhrase = mnemonicPhrase)
        Button(
            onClick = navigateValidationScreen,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(R.string.i_ve_written_it_down))
        }
    }
}

@Composable
fun ErrorContent(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text(stringResource(R.string.retry))
        }
    }
}
