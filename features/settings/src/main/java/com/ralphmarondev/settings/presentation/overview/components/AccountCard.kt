package com.ralphmarondev.settings.presentation.overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.settings.R
import com.ralphmarondev.user_settings.domain.model.User
import java.io.File

@Composable
fun AccountCard(
    user: User,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = when (user.imagePath) {
                        null -> R.drawable.app_logo
                        else -> File(user.imagePath)
                    }
                ),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = user.fullName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = user.username,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W300,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}