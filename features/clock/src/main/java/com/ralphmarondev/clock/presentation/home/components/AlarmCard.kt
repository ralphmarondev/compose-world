package com.ralphmarondev.clock.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Label
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlarmCard(
    modifier: Modifier = Modifier
) {
    var isChecked by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }

    ElevatedCard(
        onClick = { isExpanded = !isExpanded },
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 28.sp,
                                fontWeight = FontWeight.W500,
                                color = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            append("12:48")
                        }
                        append(" ")
                        withStyle(
                            style = SpanStyle(
                                fontSize = 18.sp,
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
                    checked = isChecked,
                    onCheckedChange = { isChecked = !isChecked }
                )
            }
            AnimatedVisibility(visible = !isExpanded) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                        .clickable { isExpanded = !isExpanded },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Everyday")
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            ) {
                                append(" • ")
                            }
                            append("Code Compose World")
                        },
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    IconButton(onClick = { isExpanded = !isExpanded }) {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
            AnimatedVisibility(isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { isExpanded = !isExpanded }
                ) {
                    Row(
                        modifier = Modifier
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = {},
                            colors = CheckboxDefaults.colors(
                                uncheckedColor = MaterialTheme.colorScheme.secondary
                            )
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Repeat",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.W500,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .clickable { }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(4.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Default (Cesium)",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.W500,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Label,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.padding(4.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Label",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.W500,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(vertical = 2.dp, horizontal = 8.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = {}) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.DeleteOutline,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Delete",
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }

                        IconButton(onClick = { isExpanded = !isExpanded }) {
                            Icon(
                                imageVector = Icons.Outlined.KeyboardArrowUp,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }
        }
    }
}