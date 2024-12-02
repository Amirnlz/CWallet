package com.example.cwallet.features.walletSetup.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cwallet.R
import com.example.cwallet.ui.theme.CWalletTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MnemonicScreen(
    modifier: Modifier = Modifier,
    viewModel: MnemonicViewModel,
    onNavigateValidateMnemonic: () -> Unit
) {

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

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(mnemonicWords.words.withIndex().toList()) { (index, word) ->
                    WordItem(word = word, index = index)
                }
            }
            Button(
                onClick = onNavigateValidateMnemonic,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.i_ve_written_it_down))

            }
        }
    }
}


@Composable
fun WordItem(
    modifier: Modifier = Modifier,
    word: String,
    index: Int,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, MaterialTheme.colorScheme.surfaceDim, RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Number section
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .width(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = (index + 1).toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Vertical Divider
        Box(
            modifier = Modifier
                .width(1.dp)
                .height(24.dp)
                .background(MaterialTheme.colorScheme.surfaceDim)
        )

        // Value section
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text(
                text = word,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}


@PreviewLightDark
@Composable
private fun PreviewWordBox() {
    CWalletTheme {
        WordItem(word = "Sky", index = 15)
    }
}
