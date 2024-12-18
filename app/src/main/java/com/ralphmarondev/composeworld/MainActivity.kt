package com.ralphmarondev.composeworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.composeworld.navigation.AppNavigation
import com.ralphmarondev.composeworld.ui.theme.ComposeWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val preferences = MyApp.appPreferences
            var darkTheme by remember { mutableStateOf(preferences.isDarkTheme()) }

            ComposeWorldTheme(darkTheme = darkTheme) {
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
                    AppNavigation(
                        darkTheme = darkTheme,
                        toggleDarkTheme = {
                            darkTheme = !darkTheme
                            preferences.toggleDarkTheme()
                        },
                        preferences = preferences
                    )
                }
            }
        }
    }
}