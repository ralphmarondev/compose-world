package com.ralphmarondev.keepr.presentation.subcategories

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.ralphmarondev.keepr.R
import com.ralphmarondev.keepr.presentation.components.Categories
import com.ralphmarondev.keepr.presentation.components.CategoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubCategories(
    category: String,
    backToHome: () -> Unit
) {
    val categories = listOf(
        Categories(
            image = R.drawable.tiktok,
            text = "Tiktok",
            onClick = {}
        ),
        Categories(
            image = R.drawable.facebook,
            text = "Facebook",
            onClick = {}
        ),
        Categories(
            image = R.drawable.instagram,
            text = "Instagram",
            onClick = {}
        ),
        Categories(
            image = R.drawable.linkedin,
            text = "LinkedIn",
            onClick = {}
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = category,
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
            items(categories.size) { index ->
                CategoryCard(
                    image = categories[index].image,
                    text = categories[index].text,
                    onClick = categories[index].onClick,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}