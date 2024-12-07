package com.ralphmarondev.keepr.presentation.details

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.DoneAll
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.domain.model.Account
import com.ralphmarondev.keepr.presentation.details.components.AccountCard
import com.ralphmarondev.keepr.presentation.details.components.NewAccountDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    subCategoryName: String,
    keeprDao: KeeprDao,
    backToSubCategories: () -> Unit
) {
    val viewModel: DetailViewModel = viewModel(
        factory = DetailViewModelFactory(
            keeprDao = keeprDao,
            subCategory = subCategoryName
        )
    )

    val showNewAccount by viewModel.showNewAccount.collectAsState()
    val accounts by viewModel.accounts.collectAsState()

    val context = LocalContext.current
    var showAccountDetailsDialog by remember { mutableStateOf(false) }
    var selectedAccount by remember {
        mutableStateOf(
            Account(
                subCategoryName = "",
                name = "",
                username = "",
                password = ""
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = subCategoryName,
                        fontFamily = FontFamily.Monospace
                    )
                },
                navigationIcon = {
                    IconButton(onClick = backToSubCategories) {
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
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.toggleShowNewAccount() }) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "New Account"
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }
            items(accounts) { account ->
                AccountCard(
                    keeprAccount = Account(
                        name = account.name,
                        username = account.username,
                        password = account.password,
                        subCategoryName = account.subCategoryName
                    ),
                    onClick = {
                        selectedAccount = Account(
                            id = account.id,
                            name = account.name,
                            username = account.username,
                            password = account.password,
                            subCategoryName = account.subCategoryName
                        )
                        showAccountDetailsDialog = !showAccountDetailsDialog
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            item { Spacer(modifier = Modifier.height(100.dp)) }
        }

        if (showNewAccount) {
            NewAccountDialog(
                subCategory = subCategoryName,
                onDismiss = { viewModel.toggleShowNewAccount() },
                onSaveCategory = { name, username, password ->
                    viewModel.createNewAccount(
                        name = name,
                        username = username,
                        password = password,
                        response = { isSuccess, msg ->
                            if (isSuccess) {
                                viewModel.toggleShowNewAccount()
                            } else {
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            )
        }

        if (showAccountDetailsDialog) {
            AccountDetailsBottomSheet(
                account = selectedAccount,
                onDismiss = { showAccountDetailsDialog = !showAccountDetailsDialog }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountDetailsBottomSheet(
    onDismiss: () -> Unit,
    account: Account
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var accountLabel by remember { mutableStateOf(account.name) }
    var username by remember { mutableStateOf(account.username) }
    var password by remember { mutableStateOf(account.password) }
    var showPassword by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Details",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                text = "Account Label",
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp,
                fontWeight = FontWeight.W300,
                color = MaterialTheme.colorScheme.tertiary
            )
            OutlinedTextField(
                value = accountLabel,
                onValueChange = { accountLabel = it },
                placeholder = {
                    Text(
                        text = "Account label",
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.secondary
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                text = "Username",
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp,
                fontWeight = FontWeight.W300,
                color = MaterialTheme.colorScheme.tertiary
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = {
                    Text(
                        text = "Username",
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.secondary
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                text = "Password",
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp,
                fontWeight = FontWeight.W300,
                color = MaterialTheme.colorScheme.tertiary
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        text = "Username",
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.secondary
                ),
                singleLine = true,
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AnimatedVisibility(account.password.isNotEmpty()) {
                            IconButton(
                                onClick = { showPassword = !showPassword }
                            ) {
                                val icon =
                                    if (showPassword) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Password Visibility"
                                )
                            }
                        }
                    }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            HorizontalDivider(modifier = Modifier.padding(top = 8.dp, bottom = 4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = {
                        clipboardManager.setText(
                            AnnotatedString(
                                text = "Username: ${account.username}\nPassword: ${account.password}"
                            )
                        )
                        Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ContentCopy,
                        contentDescription = "Copy",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
                IconButton(
                    onClick = {
                        Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
                        onDismiss()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DeleteForever,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()
                        onDismiss()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DoneAll,
                        contentDescription = "Save",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}