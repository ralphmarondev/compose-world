package com.ralphmarondev.composeworld.features.calculator.model

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class CardButtonModel(
    val text: String,
    val modifier: Modifier = Modifier,
    val onClick: () -> Unit,
    val containerColor: Color = Color.Transparent,
    val contentColor: Color = Color.Transparent
)
