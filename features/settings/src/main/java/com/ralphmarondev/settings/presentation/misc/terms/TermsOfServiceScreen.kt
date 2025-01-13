package com.ralphmarondev.settings.presentation.misc.terms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsOfServiceScreen(
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Terms of Service",
                        fontFamily = FontFamily.Monospace
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = "Navigate back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Terms of Service",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Last updated January 13, 2025",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W300,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        text = "Welcome to Compose World! These Terms of Service govern " +
                                "your use of the Compose World app, operated by Ralph Maron Eda. " +
                                "By accessing or using the App, you agree to these Terms.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Use of the App",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "- You must use the App in compliance with all applicable laws and regulations.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "- The App is intended for personal use only and should not be used for unlawful purposes.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Privacy",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "- Your use of the App is also governed by our [Privacy Policy], please " +
                                "review it to understand how we collect, use, and protect your information.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "User Responsibilities",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "- You are responsible for maintaining the security of your device and ensuring " +
                                "the confidentiality of your account.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "- You agree not to reverse-engineer, decompile, or tamper with the App in any way.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Content",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "- The App may allow you to store, share or upload content. You retain " +
                                "ownership of any content you submit, but by doing so, you grant us a license " +
                                "to use it for app-related purposes.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "- Any inappropriate or illegal content is strictly prohibited.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Disclaimers",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "- The App is provided 'as is' without warranties of any kind. We do not " +
                                "guarantee that the App will be error-free, secure, or continuously available.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "- We are not responsible for any data loss or damages arising from your use of the App.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Limitation of Liability",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "- To the fullest extent permitted by law, we shall not be liable for any " +
                                "indirect, incidental, or consequential damages arising from your use of the App.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Termination",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "- We may suspend or terminate your access to the App at any time for any reason, " +
                                "including violations of these Terms.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Changes to the Terms",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "- We reserve the right to update these Terms at any time. Changes will be effective " +
                                "immediately upon posting in the App. Your continued use of the App signifies " +
                                "your acceptance of the updated Terms.",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Contact Us",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "- If you have any questions about these Terms, please contact us at edaralphmaron@gmail.com",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(160.dp)) }
        }
    }
}