package com.ralphmarondev.keepr.presentation.auth

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.data.local.PreferencesHelper
import com.ralphmarondev.keepr.domain.model.User
import com.ralphmarondev.keepr.presentation.auth.components.LoginComponent
import com.ralphmarondev.keepr.presentation.auth.components.RegisterComponent

@Composable
fun AuthScreen(
    backToHome: () -> Unit,
    navigateToHome: (String) -> Unit,
    keeprDao: KeeprDao,
    preferences: PreferencesHelper
) {
    val viewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(
            keeprDao = keeprDao,
            preferences = preferences
        )
    )
    val context = LocalContext.current
    var selectedScreen by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            AuthScreenTopBar(
                navigateToHome = backToHome
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVisibility(selectedScreen == 0) {
                        LoginComponent(
                            goToRegister = { selectedScreen = 1 },
                            onLogin = { username, password ->
                                viewModel.login(
                                    username = username,
                                    password = password,
                                    response = { success ->
                                        if (success) {
                                            navigateToHome(username)
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Login Failed! Invalid Credentials.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                )
                            }
                        )
                    }

                    AnimatedVisibility(selectedScreen == 1) {
                        RegisterComponent(
                            backToLogin = { selectedScreen = 0 },
                            onRegister = { fullName, username, password ->
                                val user = User(
                                    fullName = fullName,
                                    username = username,
                                    password = password
                                )
                                viewModel.createUser(
                                    user = user,
                                    response = { success, message ->
                                        if (success) {
                                            Toast.makeText(
                                                context,
                                                "Registered successfully!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            selectedScreen = 0
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Registration Failed. Error: $message",
                                                Toast.LENGTH_SHORT
                                            ).show()
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