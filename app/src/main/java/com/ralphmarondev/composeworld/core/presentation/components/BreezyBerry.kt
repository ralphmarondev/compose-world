package com.ralphmarondev.composeworld.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ralphmarondev.composeworld.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreezyBerry(
    darkTheme: Boolean,
    toggleDarkTheme: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name)
                    )
                },
                actions = {
                    IconButton(onClick = toggleDarkTheme) {
                        val imageVector = if (darkTheme) {
                            Icons.Outlined.LightMode
                        } else {
                            Icons.Outlined.DarkMode
                        }

                        Icon(
                            imageVector = imageVector,
                            contentDescription = "Theme toggle"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Compose World v2.0",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Breezy Berry",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Developed by: Ralph Maron Eda",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W300,
                    color = MaterialTheme.colorScheme.tertiary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}