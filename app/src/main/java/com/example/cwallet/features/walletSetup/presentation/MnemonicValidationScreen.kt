package com.example.cwallet.features.walletSetup.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cwallet.R
import com.example.cwallet.ui.theme.CWalletTheme


@Composable
fun MnemonicValidationScreen(
    modifier: Modifier = Modifier,
    viewModel: ValidateMnemonicViewModel,
    onValidationComplete: (Boolean) -> Unit
) {
    val validationResult by viewModel.validationResult.collectAsState()
    val userInputs by viewModel.enteredMnemonic.collectAsState()



    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.verify_your_recovery_phrase),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = stringResource(R.string.write_12_words_from_backup),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(userInputs.size) { index ->
                SecretKeyTextField(index = index,
                    userInput = userInputs[index],
                    onValueChanged = {
                        viewModel.onEnteringMnemonicWord(index, it)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.validateMnemonic()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.verify_recovery_phrase))
        }

        when (validationResult) {
            true -> {
                Text("Validation Successful!")
                onValidationComplete(true)
            }

            false -> {
                Text("Validation Failed. Try again.")
                onValidationComplete(false)
            }

            null -> {
                // No result yet
            }
        }

    }
}

@Composable
private fun SecretKeyTextField(
    index: Int,
    userInput: String?,
    onValueChanged: (String) -> Unit,
) {
    OutlinedTextField(
        value = userInput ?: "",
        onValueChange = onValueChanged,
        label = { Text("Word ${index + 1}") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Text
        ),
        modifier = Modifier.width(100.dp),
    )
}

@Preview
@Composable
private fun SecretKeyValidationScreenPreview() {
    CWalletTheme {
        Surface {
//            MnemonicValidationScreen()
        }
    }
}