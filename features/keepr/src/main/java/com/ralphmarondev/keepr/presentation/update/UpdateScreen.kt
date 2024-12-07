package com.ralphmarondev.keepr.presentation.update

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.DoneAll
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.keepr.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    title: String,
    navigateBack: () -> Unit
) {
    val viewModel: UpdateViewModel = viewModel()
    val selectedItem by viewModel.selectedItem.collectAsState()
    val showBottomSheet by viewModel.showBottomSheet.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
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
            item { Spacer(modifier = Modifier.height(4.dp)) }
            items(2) {
                CardDetails(
                    image = R.drawable.facebook,
                    text = "Facebook",
                    onClick = {
                        viewModel.onSelectedItemChange("Facebook")
                        viewModel.toggleShowBottomSheet()
                    }
                )
            }
            item { Spacer(modifier = Modifier.height(100.dp)) }
        }
    }

    if (showBottomSheet) {
        DetailsBottomSheet(
            onDismiss = {
                viewModel.toggleShowBottomSheet()
            },
            label = title,
            text = selectedItem,
            onUpdate = { value ->

            }
        )
    }
}

@Composable
private fun CardDetails(
    modifier: Modifier = Modifier,
    image: Int,
    text: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier
            .widthIn(max = 500.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(image),
                contentDescription = text,
                modifier = Modifier
                    .size(76.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = text,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsBottomSheet(
    onDismiss: () -> Unit,
    label: String,
    text: String,
    onUpdate: (String) -> Unit
) {
    var value by remember { mutableStateOf(text) }
    val labelStr = if (label == "Categories") "Category" else "Subcategory"

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = labelStr,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W500,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            Text(
                text = "$labelStr Name",
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp,
                fontWeight = FontWeight.W300,
                color = MaterialTheme.colorScheme.tertiary
            )
            OutlinedTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = {
                    Text(
                        text = "$labelStr name",
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.secondary
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            HorizontalDivider(modifier = Modifier.padding(top = 8.dp, bottom = 4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DeleteForever,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
                IconButton(
                    onClick = {
                        onUpdate(value)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DoneAll,
                        contentDescription = "Save",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}