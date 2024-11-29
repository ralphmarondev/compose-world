package com.ralphmarondev.onboarding.presentation.register.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    label: String,
    keyboardAction: () -> Unit,
    isFinalField: Boolean = false,
    maxLines: Int = 1,
    leadingIcon: ImageVector = Icons.Outlined.Password
) {
    var show by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        modifier = modifier,
        label = {
            Text(
                text = label,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W500,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        textStyle = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W500
        ),
        singleLine = true,
        maxLines = maxLines,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = ""
            )
        },
        trailingIcon = {
            AnimatedVisibility(value.isNotEmpty()) {
                IconButton(onClick = { show = !show }) {
                    val icon = if (show) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff

                    Icon(
                        imageVector = icon,
                        contentDescription = ""
                    )
                }
            }
        },
        visualTransformation = if (show) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = if (isFinalField) ImeAction.Done else ImeAction.Next
        ),
        keyboardActions = if (isFinalField) KeyboardActions(
            onDone = { keyboardAction() }
        ) else KeyboardActions(
            onNext = { keyboardAction() }
        )
    )
}