package com.ralphmarondev.onboarding.presentation.home

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ralphmarondev.onboarding.presentation.home.components.ExploreScreen
import com.ralphmarondev.onboarding.presentation.home.components.MakeItYourOwnScreen
import com.ralphmarondev.onboarding.presentation.home.components.WelcomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    register: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val selectedScreen by viewModel.selectedScreen.collectAsState()

    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = Color.Transparent) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    ElevatedCard(
                        onClick = { viewModel.decrementSelectedScreen() },
                        enabled = selectedScreen > 0,
                        modifier = Modifier
                            .animateContentSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 12.dp, horizontal = 24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Prev",
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                    ElevatedCard(
                        onClick = {
                            if (selectedScreen == 2) {
                                register()
                            } else {
                                viewModel.incrementSelectedScreen()
                            }
                        },
                        modifier = Modifier
                            .animateContentSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 12.dp, horizontal = 24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (selectedScreen == 2) "Done" else "Next",
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (selectedScreen) {
                0 -> WelcomeScreen()
                1 -> ExploreScreen()
                2 -> MakeItYourOwnScreen()
            }
        }
    }
}