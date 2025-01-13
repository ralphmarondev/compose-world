package com.ralphmarondev.settings.presentation.general.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppThemeScreen(
    darkTheme: Boolean,
    toggleDarkTheme: () -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "App Theme",
                        fontFamily = FontFamily.Monospace
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                ElevatedCard(
                    onClick = toggleDarkTheme,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (darkTheme) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
                            contentDescription = "App Theme",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                        ) {
                            Text(
                                text = "Theme",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.W400,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = if (darkTheme) "Dark Theme" else "Light Theme",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.W300,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Switch(
                            checked = darkTheme,
                            onCheckedChange = { toggleDarkTheme() }
                        )
                    }
                }
            }
        }
    }
}