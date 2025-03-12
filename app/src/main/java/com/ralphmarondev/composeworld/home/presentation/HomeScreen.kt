package com.ralphmarondev.composeworld.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.composeworld.R
import com.ralphmarondev.composeworld.home.presentation.components.DateTimeWidget
import com.ralphmarondev.composeworld.home.presentation.components.TinyAppCard
import com.ralphmarondev.composeworld.navigation.Routes
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val viewModel: HomeViewModel = koinViewModel()

    Scaffold { innerPadding ->
        // wallpaper
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.wallpaper1),
                contentDescription = "Wallpaper",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // mini apps
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val pagerState = rememberPagerState { 2 }
            HorizontalPager(
                state = pagerState
            ) { currentPage ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    when (currentPage) {
                        0 -> FirstPage(
                            navController = navController,
                            viewModel = viewModel
                        )

                        else -> SecondPage(
                            navController = navController
                        )
                    }
                }
            }

            // taskbar and page indicator
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(pagerState.pageCount) {
                        val color = if (pagerState.currentPage == it) {
                            MaterialTheme.colorScheme.primaryContainer
                        } else {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        }
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(color)
                                .padding(4.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TinyAppCard(
                        image = R.drawable.notes,
                        onClick = {}
                    )
                    TinyAppCard(
                        image = R.drawable.calculator,
                        onClick = {}
                    )

                    TinyAppCard(
                        image = R.drawable.weather_news,
                        onClick = {}
                    )
                    TinyAppCard(
                        image = R.drawable.setting,
                        onClick = {
                            navController.navigate(Routes.Settings) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun FirstPage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        DateTimeWidget(viewModel = viewModel)
    }
}

@Composable
private fun SecondPage(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Compose World is a virtual machine operating experience in an android app developed by Ralph Maron Eda. Just a hobby.",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight
        )
    }
}