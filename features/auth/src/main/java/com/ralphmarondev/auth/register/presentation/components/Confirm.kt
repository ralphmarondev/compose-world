package com.ralphmarondev.auth.register.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.auth.R
import com.ralphmarondev.auth.register.presentation.RegistrationViewModel

@Composable
fun Confirm(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel,
    navigateBack: () -> Unit,
    install: () -> Unit
) {
    val fullName by viewModel.fullName.collectAsState()
    val username by viewModel.username.collectAsState()
    val passwordHint by viewModel.passwordHint.collectAsState()
    val enableAuth by viewModel.enableAuth.collectAsState()

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = navigateBack,
            modifier = Modifier
                .offset(16.dp)
                .align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIosNew,
                contentDescription = "Navigate back",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Image(
            painter = rememberAsyncImagePainter(R.drawable.app_logo),
            contentDescription = "app icon",
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Please confirm everything is correct before proceeding to installation.",
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(16.dp)
        )

        TextConfirm(
            title = "Full Name:",
            description = fullName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )
        TextConfirm(
            title = "Username:",
            description = username,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )
        TextConfirm(
            title = "Password Hint:",
            description = passwordHint,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(
                    vertical = 2.dp,
                    horizontal = 16.dp
                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = enableAuth,
                onCheckedChange = {
                    viewModel.onEnableAuthChange()
                }
            )
            Text(
                text = "Require password on login?",
                fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                install()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Install",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(4.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun TextConfirm(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.W500,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp
        )
        Text(
            text = description,
            fontWeight = FontWeight.W500,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 16.sp
        )
    }
}