package com.ralphmarondev.composeworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ralphmarondev.composeworld.ui.theme.ComposeWorldTheme
import com.ralphmarondev.core.notification.requestNotificationPermission
import com.ralphmarondev.core.presentation.screens.GreetingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()

        requestNotificationPermission(this)

        setContent {
            var darkTheme by remember { mutableStateOf(false) }

            ComposeWorldTheme(darkTheme = darkTheme) {
                GreetingScreen(
                    darkTheme = darkTheme,
                    toggleDarkTheme = { darkTheme = !darkTheme }
                )
            }
        }
    }
}