package com.ralphmarondev.keepr.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Menu
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ralphmarondev.keepr.R
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.presentation.components.CategoryCard
import com.ralphmarondev.keepr.presentation.home.components.NewCategoryDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    currentUser: String,
    keeprDao: KeeprDao,
    logout: () -> Unit,
    navigateToSubCategory: (String) -> Unit
) {
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(
            keeprDao = keeprDao
        )
    )
    val categories by viewModel.categories.collectAsState()
    val showNewDialog by viewModel.showNewDialog.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Hello, $currentUser",
                        fontFamily = FontFamily.Monospace,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleShowNewDialog() }) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "New Category"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            items(categories) { category ->
                CategoryCard(
                    image = getImage(category.name),
                    text = category.name,
                    onClick = {
                        navigateToSubCategory(category.name)
                    },
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }

        if (showNewDialog) {
            NewCategoryDialog(
                onDismiss = { viewModel.toggleShowNewDialog() },
                onSaveCategory = { name->
                    viewModel.createNewCategory(name)
                }
            )
        }
    }
}

private fun getImage(categoryName: String): Int {
    return when (categoryName.lowercase()) {
        "social" -> R.drawable.social
        "gaming" -> R.drawable.gaming
        "development" -> R.drawable.development
        "entertainment" -> R.drawable.entertainment
        else -> R.drawable.android
    }
}