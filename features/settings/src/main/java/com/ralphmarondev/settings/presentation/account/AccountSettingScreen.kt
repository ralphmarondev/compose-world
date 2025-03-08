package com.ralphmarondev.settings.presentation.account

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.settings.R
import com.ralphmarondev.settings.presentation.account.components.NewNameDialog
import com.ralphmarondev.settings.presentation.account.components.NewPasswordDialog
import com.ralphmarondev.settings.presentation.account.components.NewUsernameDialog
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountSettingScreen(
    navigateBack: () -> Unit
) {
    /* TODO: get the current user details on launch
    * - allow user to change the current picture [get the picture on their internal storage]
    * - update the user details [fullName, username, password, description]
    * */
    val context = LocalContext.current

    val viewModel: AccountSettingsViewModel = koinViewModel()
    val fullName by viewModel.fullName.collectAsState()
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val showNewNameDialog by viewModel.showNewFullNameDialog.collectAsState()
    val showNewUsernameDialog by viewModel.showNewUsernameDialog.collectAsState()
    val showNewPasswordDialog by viewModel.showNewPasswordDialog.collectAsState()

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Account Settings",
                        fontFamily = FontFamily.Monospace
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = navigateBack
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = "Navigate Back"
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
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            painter = rememberAsyncImagePainter(R.drawable.cute_me),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        TextButton(
                            onClick = {
                                launcher.launch("image/*")
                            }
                        ) {
                            Text(
                                text = "Change photo",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.toggleShowNewFullNameDialog() }
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Full Name",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = fullName,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        HorizontalDivider()
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.toggleShowNewUsernameDialog() }
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Username",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = username,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        HorizontalDivider()
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.toggleShowNewPasswordDialog() }
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Password",
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = password.map { "*" }.joinToString(""),
                                fontFamily = FontFamily.Monospace,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }

        if (showNewNameDialog) {
            NewNameDialog(
                oldName = fullName,
                onDismiss = { viewModel.toggleShowNewFullNameDialog() },
                onSave = { newName ->
                    viewModel.saveNewFullName(
                        newName = newName,
                        result = { isSuccess, message ->
                            if (isSuccess) {
                                viewModel.toggleShowNewFullNameDialog()
                            }
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            )
        }

        if (showNewUsernameDialog) {
            NewUsernameDialog(
                oldUsername = username,
                onDismiss = { viewModel.toggleShowNewUsernameDialog() },
                onSave = { newUsername ->
                    viewModel.saveNewUsername(
                        newUsername = newUsername,
                        result = { isSuccess, message ->
                            if (isSuccess) {
                                viewModel.toggleShowNewUsernameDialog()
                            }
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            )
        }

        if (showNewPasswordDialog) {
            NewPasswordDialog(
                oldPassword = password,
                onDismiss = { viewModel.toggleShowNewPasswordDialog() },
                onSave = { newPassword ->
                    viewModel.saveNewPassword(
                        newPassword = newPassword,
                        result = { isSuccess, message ->
                            if (isSuccess) {
                                viewModel.toggleShowNewPasswordDialog()
                            }
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            )
        }
    }
}

private fun saveImageToAppFolder(context: Context, imageUri: Uri): String? {
    return try {
        val fileName = "IMG_${System.currentTimeMillis()}.png"
        val file = File(context.filesDir, fileName)

        val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri)
        FileOutputStream(file).use { outputStream ->
            inputStream?.copyTo(outputStream)
        }
        file.absolutePath
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}