package com.ralphmarondev.keepr.presentation.subcategories

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ralphmarondev.keepr.R
import com.ralphmarondev.keepr.data.local.KeeprDao
import com.ralphmarondev.keepr.presentation.components.CategoryCard

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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
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
                    image = getImage(subCategory.name),
                    text = subCategory.name,
                    onClick = {
                        navigateToDetails(subCategory.name)
                    },
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}

private fun getImage(subCategoryName: String): Int {
    return when (subCategoryName.lowercase()) {
        "tiktok" -> R.drawable.tiktok
        "facebook" -> R.drawable.facebook
        "instagram" -> R.drawable.instagram
        "linkedin" -> R.drawable.linkedin
        "youtube" -> R.drawable.youtube
        else -> R.drawable.android
    }
}