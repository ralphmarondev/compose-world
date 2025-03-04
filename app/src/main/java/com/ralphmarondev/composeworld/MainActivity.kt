package com.ralphmarondev.composeworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ralphmarondev.composeworld.navigation.AppNavigation
import com.ralphmarondev.composeworld.ui.theme.ComposeWorldTheme
import com.ralphmarondev.core.data.local.preferences.AppPreferences
import com.ralphmarondev.core.notification.requestNotificationPermission
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val preferences: AppPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        requestNotificationPermission(this)

        setContent {
            ComposeWorldTheme {
                AppNavigation(preferences)
            }
        }
    }
}