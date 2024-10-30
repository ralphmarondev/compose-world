package com.ralphmarondev.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ralphmarondev.calculator.components.CalculatorButton

@Composable
fun CalculatorMainScreen() {
    val viewModel: CalculatorViewModel = viewModel()
    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()

    val buttonList = listOf(
        "C", "(", ")", "/",
        "7", "8", "9", "*",
        "4", "5", "6", "+",
        "1", "2", "3", "-",
        "AC", "0", ".", "="
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = equationText.value ?: "",
                fontFamily = FontFamily.Monospace,
                fontSize = 30.sp,
                textAlign = TextAlign.End,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = resultText.value ?: "",
                fontFamily = FontFamily.Monospace,
                fontSize = 60.sp,
                textAlign = TextAlign.End,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(4)
            ) {
                items(buttonList.size) {
                    CalculatorButton(
                        btn = buttonList[it],
                        onClick = {
                            viewModel.onButtonClick(buttonList[it])
                        }
                    )
                }
            }
        }
    }
}