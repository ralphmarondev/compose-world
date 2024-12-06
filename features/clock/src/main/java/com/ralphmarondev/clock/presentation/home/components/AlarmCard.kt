package com.ralphmarondev.clock.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlarmCard(
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = {},
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W500,
                                color = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            append("12:48")
                        }
                        append(" ")
                        withStyle(
                            style = SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W300,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        ) {
                            append("AM")
                        }
                    },
                    fontFamily = FontFamily.Monospace
                )

                Switch(
                    checked = false,
                    onCheckedChange = {}
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Everyday",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W300,
                    color = MaterialTheme.colorScheme.secondary
                )

                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowDropDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}