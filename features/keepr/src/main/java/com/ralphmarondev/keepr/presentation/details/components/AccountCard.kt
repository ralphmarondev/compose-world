package com.ralphmarondev.keepr.presentation.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.keepr.R
import com.ralphmarondev.keepr.domain.model.KeeprAccount

data class AccountCardItems(
    val keeprAccount: KeeprAccount,
    val onClick: () -> Unit
)

@Composable
fun AccountCard(
    keeprAccount: KeeprAccount,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(R.drawable.tiktok),
                contentDescription = "Sub Category Image",
                modifier = Modifier
                    .size(65.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "${keeprAccount.subCategory}",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W500,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = keeprAccount.usernameOrEmail,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W300,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}