package com.ralphmarondev.composeworld.features.calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.composeworld.R
import com.ralphmarondev.composeworld.features.calculator.components.CardButtons
import com.ralphmarondev.composeworld.features.calculator.model.CardButtonModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    backToHome: () -> Unit
) {
    val buttons = listOf(
        CardButtonModel(
            text = "C",
            onClick = {}
        ),
        CardButtonModel(
            text = "%",
            onClick = {}
        ),
        CardButtonModel(
            text = "D",
            onClick = {}
        ),
        CardButtonModel(
            text = "/",
            onClick = {}
        ),
        CardButtonModel(
            text = "7",
            onClick = {}
        ),
        CardButtonModel(
            text = "8",
            onClick = {}
        ),
        CardButtonModel(
            text = "9",
            onClick = {}
        ),
        CardButtonModel(
            text = "x",
            onClick = {}
        ),
        CardButtonModel(
            text = "4",
            onClick = {}
        ),
        CardButtonModel(
            text = "5",
            onClick = {}
        ),
        CardButtonModel(
            text = "6",
            onClick = {}
        ),
        CardButtonModel(
            text = "-",
            onClick = {}
        ),
        CardButtonModel(
            text = "1",
            onClick = {}
        ),
        CardButtonModel(
            text = "2",
            onClick = {}
        ),
        CardButtonModel(
            text = "3",
            onClick = {}
        ),
        CardButtonModel(
            text = "+",
            onClick = {}
        ),
        CardButtonModel(
            text = "00",
            onClick = {}
        ),
        CardButtonModel(
            text = "0",
            onClick = {}
        ),
        CardButtonModel(
            text = ".",
            onClick = {}
        ), CardButtonModel(
            text = "=",
            onClick = {}
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Calculator",
                        fontFamily = FontFamily.Monospace
                    )
                },
                navigationIcon = {
                    Box(modifier = Modifier.padding(4.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(R.drawable.notes),
                            contentDescription = "Notes",
                            modifier = Modifier
                                .size(28.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                },
                actions = {
                    IconButton(onClick = backToHome) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Close"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(buttons.size) {
                CardButtons(
                    text = buttons[it].text,
                    onClick = buttons[it].onClick,
                    containerColor = buttons[it].containerColor,
                    contentColor = buttons[it].contentColor
                )
            }
        }
    }
}