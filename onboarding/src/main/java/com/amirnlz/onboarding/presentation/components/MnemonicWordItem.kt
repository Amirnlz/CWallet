package com.amirnlz.onboarding.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amirnlz.onboarding.presentation.model.MnemonicWordUiModel

@Composable
fun MnemonicWordItem(
    modifier: Modifier = Modifier,
    wordModel: MnemonicWordUiModel,
) {
    val typography = MaterialTheme.typography
    val colorScheme = MaterialTheme.colorScheme

    Row(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = colorScheme.outline,
                shape = RoundedCornerShape(8.dp)
            )
            .background(colorScheme.surfaceContainerLow)
            .semantics {
                contentDescription = "Secret word ${wordModel.index + 1}: ${wordModel.word}"
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val indexString = (wordModel.index + 1).toString()
        val paddedIndex = if (indexString.length < 2) "0$indexString" else indexString
        Text(
            text = paddedIndex,
            style = typography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = colorScheme.onSurface
            ),
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .testTag("word_index_${wordModel.index}")
        )

        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp),
            color = colorScheme.outline
        )

        Text(
            text = wordModel.word,
            style = typography.bodyMedium.copy(
                color = colorScheme.onSurface
            ),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .testTag("word_text_${wordModel.index}")
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun MnemonicWordItemPreview() {
    MnemonicWordItem(
        wordModel = MnemonicWordUiModel(
            word = "Banana",
            index = 1
        )
    )
}