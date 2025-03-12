package com.ralphmarondev.settings.presentation.account.components

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun NewUsernameDialog(
    oldUsername: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    val context = LocalContext.current
    var newUsername by rememberSaveable { mutableStateOf(oldUsername) }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "New Username",
                    fontWeight = FontWeight.W500,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )

                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

            OutlinedTextField(
                value = newUsername,
                onValueChange = { newUsername = it },
                label = {
                    Text(
                        text = "Enter username",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    color = MaterialTheme.colorScheme.secondary
                ),
                singleLine = true,
                trailingIcon = {
                    AnimatedVisibility(newUsername.isNotEmpty()) {
                        IconButton(onClick = { newUsername = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = "Clear",
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )

            Button(
                onClick = {
                    if (newUsername.trim().isNotEmpty()) {
                        onSave(newUsername.trim())
                    } else {
                        Toast.makeText(
                            context,
                            "Please fill in all fields!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "SAVE",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }
    }
}