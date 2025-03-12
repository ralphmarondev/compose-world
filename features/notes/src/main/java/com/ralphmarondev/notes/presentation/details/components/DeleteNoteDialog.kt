package com.ralphmarondev.notes.presentation.details.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily

@Composable
fun DeleteNoteDialog(
    onDismiss: (Boolean) -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss(false) },
        confirmButton = {
            TextButton(onClick = { onDismiss(true) }) {
                Text(
                    text = "Delete",
                    fontFamily = FontFamily.Monospace,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss(false) }) {
                Text(
                    text = "Cancel",
                    fontFamily = FontFamily.Monospace
                )
            }
        },
        title = {
            Text(
                text = "Deleting Note",
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        text = {
            Text(
                text = "This action cannot be undone.",
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    )
}