package com.ralphmarondev.auth.register.presentation

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.ralphmarondev.auth.register.presentation.components.Account
import com.ralphmarondev.auth.register.presentation.components.Confirm
import com.ralphmarondev.auth.register.presentation.components.ConfirmDialog
import com.ralphmarondev.core.util.LocalThemeState
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(
    navigateBack: () -> Unit,
    onRegistrationSuccessful: () -> Unit
) {
    val themeState = LocalThemeState.current
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val insetsController = window?.let {
                WindowCompat.getInsetsController(it, view)
            }
            insetsController?.isAppearanceLightStatusBars = !themeState.darkTheme.value
        }
    }
    val snackbar = remember { SnackbarHostState() }

    val viewModel: RegistrationViewModel = koinViewModel()
    val selectedScreen by viewModel.selectedScreen.collectAsState()
    val showConfirmDialog by viewModel.showConfirmDialog.collectAsState()

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbar)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (selectedScreen) {
                1 -> {
                    Confirm(
                        viewModel = viewModel,
                        navigateBack = {
                            viewModel.setSelectedScreen(0)
                        },
                        install = {
                            viewModel.toggleShowConfirmDialog()
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }

                else -> {
                    Account(
                        viewModel = viewModel,
                        navigateBack = {
                            navigateBack()
                        },
                        next = {
                            viewModel.setSelectedScreen(1)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        snackbar = snackbar
                    )
                }
            }
        }

        if (showConfirmDialog) {
            ConfirmDialog(
                onDismiss = {
                    viewModel.toggleShowConfirmDialog()
                },
                onConfirm = {
                    viewModel.toggleShowConfirmDialog()
                    viewModel.onRegister()
                    onRegistrationSuccessful()
                }
            )
        }
    }
}