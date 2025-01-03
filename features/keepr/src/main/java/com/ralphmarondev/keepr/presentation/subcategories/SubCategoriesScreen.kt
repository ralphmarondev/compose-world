package com.ralphmarondev.keepr.presentation.subcategories

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.presentation.components.CategoryCard
import com.ralphmarondev.keepr.presentation.subcategories.components.NewSubCategoryDialog
import com.ralphmarondev.keepr.util.getCardImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubCategories(
    categoryName: String,
    keeprDao: KeeprDao,
    backToHome: () -> Unit,
    navigateToDetails: (String) -> Unit
) {
    val viewModel: SubCategoriesViewModel = viewModel(
        factory = SubCategoriesViewModelFactory(
            keeprDao = keeprDao,
            categoryName = categoryName
        )
    )
    val subCategories by viewModel.subCategories.collectAsState()
    val showNewSubCategory by viewModel.showNewSubCategory.collectAsState()

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = categoryName,
                        fontFamily = FontFamily.Monospace
                    )
                },
                navigationIcon = {
                    IconButton(onClick = backToHome) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleShowNewSubCategory() }) {
                        Icon(
                            imageVector = Icons.Outlined.AddCircleOutline,
                            contentDescription = "New SubCategory"
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
            items(subCategories) { subCategory ->
                CategoryCard(
                    image = getCardImage(subCategory.name),
                    text = subCategory.name,
                    onClick = {
                        navigateToDetails(subCategory.name)
                    },
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }

        if (showNewSubCategory) {
            NewSubCategoryDialog(
                onDismiss = { viewModel.toggleShowNewSubCategory() },
                onSaveSubCategory = { name ->
                    viewModel.createNewSubCategory(
                        name = name,
                        response = { isSuccess, msg ->
                            if (isSuccess) {
                                viewModel.toggleShowNewSubCategory()
                            } else {
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            )
        }
    }
}