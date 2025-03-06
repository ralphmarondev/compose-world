package com.ralphmarondev.settings.presentation.misc.developer

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.settings.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutDeveloperScreen(
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "About Developer",
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
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(R.drawable.cute_me),
                        contentDescription = "Developer Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(140.dp)
                            .border(
                                width = 4.dp,
                                brush = Brush.sweepGradient(
                                    listOf(
                                        Color(0xFF9575CD),
                                        Color(0xFFBA68C8),
                                        Color(0xFFE57373),
                                        Color(0xFFFFB74D),
                                        Color(0xFFFFF176),
                                        Color(0xFFAED581),
                                        Color(0xFF4DD0E1),
                                        Color(0xFF9575CD),
                                    )
                                ),
                                shape = CircleShape
                            )
                            .padding(4.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ralph Maron A. Eda",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W500,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Computer Engineer",
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W300,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = buildAnnotatedString {
                            append("I love ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append("building mobile apps, ")
                            }
                            append("exploring modern tech, and ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append("learning new skills ")
                            }
                            append("in ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append("software development.")
                            }
                        },
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}