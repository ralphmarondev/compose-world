package com.ralphmarondev.keepr.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AuthScreen(
    currentUser: String,
    navigateBack: () -> Unit,
    navigateToHome: () -> Unit
) {
    val viewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(currentUser)
    )
    val password by viewModel.passwordIndicator.collectAsState()

    Scaffold(
        topBar = {
            AuthScreenTopBar(
                navigateToHome = navigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Please enter your 4-digit security PIN to continue.",
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = password,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.2.em,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ButtonCard(
                        text = "1",
                        onClick = {
                            viewModel.onButtonClick("1")
                        }
                    )
                    ButtonCard(
                        text = "2",
                        onClick = {
                            viewModel.onButtonClick("2")
                        }
                    )
                    ButtonCard(
                        text = "3",
                        onClick = {
                            viewModel.onButtonClick("3")
                        }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ButtonCard(
                        text = "4",
                        onClick = {
                            viewModel.onButtonClick("4")
                        }
                    )
                    ButtonCard(
                        text = "5",
                        onClick = {
                            viewModel.onButtonClick("5")
                        }
                    )
                    ButtonCard(
                        text = "6",
                        onClick = {
                            viewModel.onButtonClick("6")
                        }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ButtonCard(
                        text = "7",
                        onClick = {
                            viewModel.onButtonClick("7")
                        }
                    )
                    ButtonCard(
                        text = "8",
                        onClick = {
                            viewModel.onButtonClick("8")
                        }
                    )
                    ButtonCard(
                        text = "9",
                        onClick = {
                            viewModel.onButtonClick("9")
                        }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ButtonCard(
                        text = "C",
                        onClick = {
                            viewModel.onButtonClick("C")
                        }
                    )
                    ButtonCard(
                        text = "0",
                        onClick = {
                            viewModel.onButtonClick("0")
                        }
                    )
                    ButtonCard(
                        text = "D",
                        onClick = {
                            viewModel.onLogin(
                                response = { isSuccess, _ ->
                                    if (isSuccess) {
                                        navigateToHome()
                                    }
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ButtonCard(
    text: String,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        shape = CircleShape
    ) {
        Box(
            modifier = Modifier
                .size(80.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier
                    .padding(8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AuthScreenTopBar(
    navigateToHome: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Authentication",
                fontFamily = FontFamily.Monospace
            )
        },
        navigationIcon = {
            IconButton(onClick = navigateToHome) {
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