package com.ralphmarondev.notes.presentation.newnote

import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ralphmarondev.notes.data.local.NoteDao
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.presentation.components.DescriptionTextField
import com.ralphmarondev.notes.presentation.components.TitleTextField
import com.ralphmarondev.notes.utils.getCurrentDate
import com.ralphmarondev.notes.utils.getCurrentTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(
    backToAllNotes: () -> Unit,
    noteDao: NoteDao
) {
    val viewModel: NewNoteViewModel = viewModel(
        factory = NewNoteViewModelFactory(noteDao)
    )
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "New Note",
                        fontFamily = FontFamily.Monospace
                    )
                },
                navigationIcon = {
                    IconButton(onClick = backToAllNotes) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    ElevatedButton(
                        onClick = {
                            Log.d(
                                "NOTES",
                                "Title: $title, Description: $description, " +
                                        "Date: ${getCurrentDate()}, " +
                                        "Time: ${getCurrentTime()}"
                            )
                            val note = Note(
                                title = title,
                                description = description,
                                date = getCurrentDate(),
                                time = getCurrentTime()
                            )
                            viewModel.createNote(
                                note = note,
                                response = { isSuccess, msg ->
                                    if (isSuccess) {
                                        Toast.makeText(
                                            context,
                                            "Saved successfully!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        backToAllNotes()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Failed. $msg",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            )
                        }
                    ) {
                        Text(
                            text = "SAVE",
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
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = "Date",
                            modifier = Modifier
                                .size(16.dp)
                        )
                        Text(
                            text = getCurrentDate(),
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(start = 6.dp, end = 8.dp)
                        )

                        Icon(
                            imageVector = Icons.Outlined.AccessTime,
                            contentDescription = "Time",
                            modifier = Modifier
                                .size(16.dp)
                        )

                        Text(
                            text = getCurrentTime(),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 4.dp)
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