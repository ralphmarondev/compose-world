package com.ralphmarondev.keepr.presentation.details

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
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

                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            item { Spacer(modifier = Modifier.height(100.dp)) }
        }

        if (showNewAccount) {
            NewAccountDialog(
                onDismiss = { viewModel.toggleShowNewAccount() },
                onSaveCategory = { name, username, password ->
                    viewModel.createNewAccount(
                        name = name,
                        username = username,
                        password = password
                    )
                }
            )
        }
    }
}