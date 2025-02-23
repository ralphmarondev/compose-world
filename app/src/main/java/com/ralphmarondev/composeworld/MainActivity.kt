package com.ralphmarondev.composeworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ralphmarondev.composeworld.core.preferences.AppPreferences
import com.ralphmarondev.composeworld.navigation.AppNavigation
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val preferences: AppPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation(preferences)
        }
    }
}