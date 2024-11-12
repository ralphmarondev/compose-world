package com.ralphmarondev.keepr.presentation.details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun NewAccountDialog(
    onDismiss: () -> Unit,
    onSaveCategory: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var usernameOrEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
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
                    text = "New Account",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W500,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )

                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Close"
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Text(
                        text = "Account name",
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500
                ),
                maxLines = 1,
                trailingIcon = {
                    AnimatedVisibility(name.isNotEmpty()) {
                        IconButton(onClick = { name = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )
            OutlinedTextField(
                value = usernameOrEmail,
                onValueChange = { usernameOrEmail = it },
                label = {
                    Text(
                        text = "Username",
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500
                ),
                maxLines = 1,
                trailingIcon = {
                    AnimatedVisibility(usernameOrEmail.isNotEmpty()) {
                        IconButton(onClick = { usernameOrEmail = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = "Password",
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500
                ),
                maxLines = 1,
                trailingIcon = {
                    AnimatedVisibility(password.isNotEmpty()) {
                        IconButton(onClick = { password = "" }) {
                            Icon(
                                imageVector = Icons.Outlined.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    }
                    IconButton(
                        onClick = { showPassword = !showPassword }
                    ) {
                        val icon =
                            if (showPassword) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
                        Icon(
                            imageVector = icon,
                            contentDescription = "Password Visibility"
                        )
                    }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )

            Button(
                onClick = {
                    onSaveCategory(name)
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "SAVE",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }
    }
}