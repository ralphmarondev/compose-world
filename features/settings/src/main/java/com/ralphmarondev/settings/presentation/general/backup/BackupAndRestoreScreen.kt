package com.ralphmarondev.settings.presentation.general.backup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AppRegistration
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ralphmarondev.core.presentation.components.NormalTextField
import com.ralphmarondev.core.presentation.components.PasswordTextField
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackupAndRestoreScreen(
    navigateBack: () -> Unit
) {
    val viewModel: BackupAndRestoreViewModel = koinViewModel()
    val email = viewModel.email.collectAsState().value
    val password = viewModel.password.collectAsState().value
    val result = viewModel.result.collectAsState().value
    val showRegisterDialog = viewModel.showRegisterDialog.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Backup and Restore"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = viewModel::toggleShowRegisterDialog
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AppRegistration,
                            contentDescription = "Register"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
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
                NormalTextField(
                    value = email,
                    onValueChange = viewModel::onEmailChange
                )
                PasswordTextField(
                    value = password,
                    onValueChange = viewModel::onPasswordChange
                )

                Button(
                    onClick = viewModel::onLogin
                ) {
                    Text(
                        text = "Login"
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = if (result) "Success" else "Failed"
                )
            }
        }

        if (showRegisterDialog) {
            RegisterDialog(
                onDismiss = viewModel::toggleShowRegisterDialog,
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun RegisterDialog(
    onDismiss: () -> Unit,
    viewModel: BackupAndRestoreViewModel
) {
    val email = viewModel.email.collectAsState().value
    val password = viewModel.password.collectAsState().value
    val result = viewModel.result.collectAsState().value

    Dialog(
        onDismissRequest = onDismiss
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Register")
                NormalTextField(
                    value = email,
                    onValueChange = viewModel::onEmailChange
                )
                PasswordTextField(
                    value = password,
                    onValueChange = viewModel::onPasswordChange
                )

                Button(
                    onClick = viewModel::onRegister
                ) {
                    Text(
                        text = "Register"
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = if (result) "Success" else "Failed"
                )
            }
        }
    }
}