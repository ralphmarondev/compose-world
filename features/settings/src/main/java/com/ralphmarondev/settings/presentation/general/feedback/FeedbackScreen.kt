package com.ralphmarondev.settings.presentation.general.feedback

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    val viewModel = viewModel<FeedbackViewModel>()
    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val feedback by viewModel.feedback.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Feedback",
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
                .padding(innerPadding)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Give us Feedback",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { viewModel.onNameChange(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        textStyle = TextStyle(
                            fontFamily = FontFamily.Monospace
                        ),
                        label = {
                            Text(
                                text = "Enter your name",
                                fontFamily = FontFamily.Monospace,
                                maxLines = 1
                            )
                        },
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = "Person",
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { viewModel.onEmailChange(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        textStyle = TextStyle(
                            fontFamily = FontFamily.Monospace
                        ),
                        label = {
                            Text(
                                text = "Enter your email",
                                fontFamily = FontFamily.Monospace,
                                maxLines = 1
                            )
                        },
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Mail,
                                contentDescription = "Mail",
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                    )

                    OutlinedTextField(
                        value = feedback,
                        onValueChange = { viewModel.onFeedbackChange(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        textStyle = TextStyle(
                            fontFamily = FontFamily.Monospace
                        ),
                        label = {
                            Text(
                                text = "How can we help you?",
                                fontFamily = FontFamily.Monospace,
                                maxLines = 1
                            )
                        },
                        minLines = 4,
                        maxLines = 4
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.sendFeedback(context) }
                    ) {
                        Text(
                            text = "Send Feedback",
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.W500
                        )
                    }
                }
            }
        }
    }
}