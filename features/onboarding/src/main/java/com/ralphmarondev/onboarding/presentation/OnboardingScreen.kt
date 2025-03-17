package com.ralphmarondev.onboarding.presentation

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.core.util.LocalThemeState
import com.ralphmarondev.onboarding.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingScreen(
    onTryComposeWorld: () -> Unit,
    onInstallComposeWorld: () -> Unit
) {
    val themeState = LocalThemeState.current
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val insetsController = window?.let {
                WindowCompat.getInsetsController(it, view)
            }
            insetsController?.isAppearanceLightStatusBars = !themeState.darkTheme.value
        }
    }

    val viewModel: OnboardingViewModel = koinViewModel()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = rememberAsyncImagePainter(R.drawable.app_logo),
                contentDescription = "app icon",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Welcome to Compose World!",
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(16.dp)
            )

            Text(
                text = "Breezy Berry",
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))
            ElevatedButton(
                onClick = {
                    viewModel.createDefaultUser()
                    onTryComposeWorld()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Try Compose World",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(4.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text(
                    text = "OR",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(8.dp)
                )
                HorizontalDivider(modifier = Modifier.weight(1f))
            }

            Button(
                onClick = onInstallComposeWorld,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Install Compose World",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}