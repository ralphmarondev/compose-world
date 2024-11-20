package com.ralphmarondev.onboarding.presentation.home.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        fontFamily = FontFamily.Monospace,
        fontSize = 24.sp,
        fontWeight = FontWeight.W600,
        maxLines = 2,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}