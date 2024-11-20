package com.ralphmarondev.calculator.presentation.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    btn: String,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
            .size(80.dp)
            .padding(8.dp),
        shape = CircleShape,
        contentColor = Color.White,
        containerColor = getColor(btn)
    ) {
        Text(
            text = btn,
            fontFamily = FontFamily.Monospace,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

fun getColor(btn: String): Color {
    if (btn == "C" || btn == "AC")
        return Color(0xFFF44336)
    if (btn == "(" || btn == ")")
        return Color.Gray
    if (btn == "/" || btn == "*" || btn == "+" || btn == "-")
        return Color(0xFFFF9800)
    return Color(0xFF00C8C9)
}