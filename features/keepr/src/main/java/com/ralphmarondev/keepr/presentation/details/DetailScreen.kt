package com.ralphmarondev.keepr.presentation.details

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.ralphmarondev.keepr.domain.model.KeeprAccount
import com.ralphmarondev.keepr.presentation.details.components.AccountCard
import com.ralphmarondev.keepr.presentation.details.components.AccountCardItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    subCategory: String,
    backToSubCategories: () -> Unit
) {
    val listOfAccounts = listOf(
        AccountCardItems(
            keeprAccount = KeeprAccount(
                category = "Social",
                subCategory = subCategory,
                usernameOrEmail = "edaralphmaron@gmail.com",
                password = "somepassword-hehez",
                owner = "ralphmaron"
            ),
            onClick = {}
        ),
        AccountCardItems(
            keeprAccount = KeeprAccount(
                category = "Social",
                subCategory = subCategory,
                usernameOrEmail = "edaralphmaron2@gmail.com",
                password = "somepassword-hehez2",
                owner = "ralphmaron"
            ),
            onClick = {}
        ),
        AccountCardItems(
            keeprAccount = KeeprAccount(
                category = subCategory,
                subCategory = "Tiktok",
                usernameOrEmail = "edaralphmaron3@gmail.com",
                password = "somepassword-hehez3",
                owner = "ralphmaron"
            ),
            onClick = {}
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = subCategory,
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
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }
            items(listOfAccounts.size) { index ->
                AccountCard(
                    keeprAccount = listOfAccounts[index].keeprAccount,
                    onClick = listOfAccounts[index].onClick,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
            item { Spacer(modifier = Modifier.height(100.dp)) }
        }
    }
}