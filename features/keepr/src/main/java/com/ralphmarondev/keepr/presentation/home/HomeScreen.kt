package com.ralphmarondev.keepr.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LightMode
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.ralphmarondev.keepr.R
import com.ralphmarondev.keepr.presentation.home.components.CategoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    currentUser: String,
    logout: () -> Unit
) {
    val categories = listOf(
        Categories(
            image = R.drawable.social,
            text = "Social",
            onClick = {}
        ),
        Categories(
            image = R.drawable.gaming,
            text = "Gaming",
            onClick = {}
        ),
        Categories(
            image = R.drawable.development,
            text = "Development",
            onClick = {}
        ),
        Categories(
            image = R.drawable.entertainment,
            text = "Entertainment",
            onClick = {}
        ),
        Categories(
            image = R.drawable.new_image,
            text = "New",
            onClick = {}
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Home",
                        fontFamily = FontFamily.Monospace
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
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Outlined.LightMode,
                            contentDescription = "Theme Switcher"
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

private data class Categories(
    val image: Int,
    val text: String,
    val onClick: () -> Unit
)