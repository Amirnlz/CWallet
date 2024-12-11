package com.amirnlz.onboarding.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amirnlz.onboarding.domain.model.MnemonicPhrase
import com.amirnlz.onboarding.presentation.model.MnemonicWordUiModel


@Composable
fun MnemonicPhraseGrid(
    modifier: Modifier = Modifier, mnemonicPhrase: MnemonicPhrase
) {
    val mnemonicWords = remember(mnemonicPhrase) {
        mnemonicPhrase.words.mapIndexed { index, word ->
            MnemonicWordUiModel(word, index)
        }
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = mnemonicWords, key = { it.index }) { wordModel ->
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(durationMillis = 300)),
                exit = fadeOut(animationSpec = tween(300)) + shrinkVertically()
            ) {
                MnemonicWordItem(
                    wordModel = wordModel, modifier = Modifier.animateItem()
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF,
)
@Composable
private fun MnemonicPhraseGridPreview() {
    val previewMnemonicPhrase = MnemonicPhrase(
        words = listOf(
            "apple",
            "banana",
            "cherry",
            "date",
            "elderberry",
            "fig",
            "grape",
            "honeydew",
            "kiwi",
            "lemon",
            "mango",
            "nectarine"
        )
    )

    MnemonicPhraseGrid(mnemonicPhrase = previewMnemonicPhrase)
}

