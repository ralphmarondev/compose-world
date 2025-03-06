package com.ralphmarondev.composeworld.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ralphmarondev.composeworld.home.presentation.HomeViewModel
import java.time.format.DateTimeFormatter

@Composable
fun DateTimeWidget(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val currentTime by viewModel.currentTime.collectAsState()
    val currentDate by viewModel.currentDate.collectAsState()

    val formattedTime = remember(currentTime) {
        currentTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a"))
    }
    val formattedDate = remember(currentDate) {
        currentDate.format(DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy"))
    }

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = formattedDate,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = formattedTime,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}