package com.ralphmarondev.composeworld.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.composeworld.R
import com.ralphmarondev.composeworld.home.presentation.components.DateTimeWidget
import com.ralphmarondev.composeworld.home.presentation.components.TinyAppCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
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
            DateTimeWidget(viewModel = viewModel)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
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
                    onClick = { }
                )
            }
        }
    }
}