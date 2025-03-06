package com.ralphmarondev.auth.register.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ralphmarondev.auth.register.presentation.components.Account
import com.ralphmarondev.auth.register.presentation.components.Confirm
import com.ralphmarondev.auth.register.presentation.components.ConfirmDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(
    navigateBack: () -> Unit,
    onRegistrationSuccessful: () -> Unit
) {
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