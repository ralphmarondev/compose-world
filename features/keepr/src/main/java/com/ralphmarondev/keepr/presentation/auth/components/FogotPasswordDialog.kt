package com.ralphmarondev.keepr.presentation.auth.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily

@Composable
fun ForgotPasswordDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Got it!",
                    fontFamily = FontFamily.Monospace
                )
            }
        },
        title = {
            Text(
                text = "Oops! Forgot Your Password?",
                fontFamily = FontFamily.Monospace
            )
        },
        text = {
            Text(
                text = "No worries! Take a deep breath, maybe sip some coffee, and give it another shot. You've got this!",
                fontFamily = FontFamily.Monospace
            )
        }
    )
}