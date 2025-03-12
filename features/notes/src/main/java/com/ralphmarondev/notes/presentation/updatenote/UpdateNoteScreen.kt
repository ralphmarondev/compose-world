package com.ralphmarondev.notes.presentation.updatenote

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.presentation.components.DescriptionTextField
import com.ralphmarondev.notes.presentation.components.TitleTextField
import com.ralphmarondev.notes.utils.getCurrentDate
import com.ralphmarondev.notes.utils.getCurrentTime
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateNoteScreen(
    backToNoteDetails: () -> Unit,
    noteId: Int
) {
    val context = LocalContext.current
    val viewModel: UpdateNoteViewModel = koinViewModel(parameters = { parametersOf(noteId) })

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var isUpdated by remember { mutableStateOf(false) }

    val note by viewModel.note.collectAsState()

    var originalTitle by remember { mutableStateOf("") }
    var originalDescription by remember { mutableStateOf("") }

    LaunchedEffect(note) {
        note.let {
            title = it.title
            description = it.description
            date = it.date
            time = it.time

            originalTitle = it.title
            originalDescription = it.description
        }
    }

    // update date and time when title or description changes
    LaunchedEffect(title, description) {
        if (title != originalTitle || description != originalDescription) {
            date = getCurrentDate()
            time = getCurrentTime()
            isUpdated = !isUpdated
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Update Note",
                        fontFamily = FontFamily.Monospace
                    )
                },
                navigationIcon = {
                    IconButton(onClick = backToNoteDetails) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    ElevatedButton(
                        onClick = {
                            if (isUpdated) {
                                viewModel.updateNote(
                                    note = Note(
                                        id = noteId,
                                        title = title,
                                        description = description,
                                        date = date,
                                        time = time
                                    ),
                                    response = { success, message ->
                                        if (success) {
                                            backToNoteDetails()
                                            Toast.makeText(
                                                context,
                                                "Updated successfully!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Update failed. Error: $message",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                )
                            } else {
                                Toast.makeText(
                                    context,
                                    "No changes made!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    ) {
                        Text(
                            text = "UPDATE",
                            fontFamily = FontFamily.Monospace
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
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = "Date",
                            modifier = Modifier
                                .size(16.dp),
                            tint = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            text = date,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(start = 6.dp, end = 8.dp),
                            color = MaterialTheme.colorScheme.tertiary
                        )

                        Icon(
                            imageVector = Icons.Outlined.AccessTime,
                            contentDescription = "Time",
                            modifier = Modifier
                                .size(16.dp),
                            tint = MaterialTheme.colorScheme.tertiary
                        )

                        Text(
                            text = time,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 4.dp),
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    TitleTextField(
                        value = title,
                        onValueChanged = { title = it },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    DescriptionTextField(
                        value = description,
                        onValueChanged = { description = it },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}