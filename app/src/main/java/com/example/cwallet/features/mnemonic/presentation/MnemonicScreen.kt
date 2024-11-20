package com.example.cwallet.features.mnemonic.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cwallet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MnemonicScreen(modifier: Modifier = Modifier, viewModel: MnemonicViewModel) {

    val mnemonicWords by viewModel.mnemonic.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Mnemonic Phrase") })
    }) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = stringResource(R.string.saveMnemonicWords),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(mnemonicWords.words) { index, word ->
                    Text(
                        text = "${index + 1}. $word",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.i_ve_written_it_down))

            }
        }
    }
}
