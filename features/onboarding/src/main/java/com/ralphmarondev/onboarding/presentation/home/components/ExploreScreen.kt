package com.ralphmarondev.onboarding.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.onboarding.R

@Composable
fun ExploreScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.android),
                contentDescription = "Welcome Image",
                modifier = Modifier
                    .size(100.dp),
                contentScale = ContentScale.Crop
            )
        }

        TextTitle(text = stringResource(R.string.explore))
        Spacer(modifier = Modifier.height(8.dp))
        TextDescription(text = stringResource(R.string.explore_description))
    }
}