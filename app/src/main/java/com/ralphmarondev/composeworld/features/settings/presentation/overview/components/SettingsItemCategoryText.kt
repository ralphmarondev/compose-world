package com.ralphmarondev.composeworld.features.settings.presentation.overview.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun SettingsItemCategoryText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        fontFamily = FontFamily.Monospace,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp),
        color = MaterialTheme.colorScheme.primary
    )
}