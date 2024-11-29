package com.ralphmarondev.home

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ralphmarondev.home.components.TinyAppCard
import com.ralphmarondev.home.components.TinyAppCard2

@Composable
fun HomeScreen(
    navigateToBrowser: () -> Unit,
    navigateToNotes: () -> Unit,
    navigateToCalculator: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateToKeeper: () -> Unit,
    navigateToClock: () -> Unit
) {
    Scaffold(
        containerColor = Color.Transparent
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.welcome_message),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W500,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.description_home_screen),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                item {
                    TinyAppCard2(
                        image = R.drawable.keepr,
                        onClick = navigateToKeeper,
                        label = "Keepr",
                        modifier = Modifier.weight(1f)
                    )
                }
                item {
                    TinyAppCard2(
                        image = R.drawable.instagram,
                        onClick = {},
                        label = "Compose\ntagram",
                        modifier = Modifier.weight(1f)
                    )
                }
                item {
                    TinyAppCard2(
                        image = R.drawable.weather_news,
                        onClick = {},
                        label = "Weather",
                        modifier = Modifier.weight(1f)
                    )
                }
                item {
                    TinyAppCard2(
                        image = R.drawable.clock,
                        onClick = navigateToClock,
                        label = "Clock",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TinyAppCard(
                    image = R.drawable.browser,
                    onClick = navigateToBrowser
                )
                TinyAppCard(
                    image = R.drawable.notes,
                    onClick = navigateToNotes
                )

                TinyAppCard(
                    image = R.drawable.calculator,
                    onClick = navigateToCalculator
                )
                TinyAppCard(
                    image = R.drawable.setting,
                    onClick = navigateToSettings
                )
            }
        }
    }
}