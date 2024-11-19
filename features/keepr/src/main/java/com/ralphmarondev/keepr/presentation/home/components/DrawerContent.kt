package com.ralphmarondev.keepr.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backup
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.ImportExport
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.keepr.R

@Composable
fun DrawerContent(
    closeDrawer: () -> Unit
) {
    val drawerItems0 = listOf(
        NavigationModel(
            icon = Icons.Outlined.Person,
            label = "My Profile",
            onClick = {}
        ),
        NavigationModel(
            icon = Icons.Outlined.Settings,
            label = "Settings",
            onClick = {}
        )
    )

    val drawerItems1 = listOf(
        NavigationModel(
            icon = Icons.Outlined.Category,
            label = "Categories",
            onClick = {}
        ),
        NavigationModel(
            icon = Icons.Outlined.StarOutline,
            label = "SubCategories",
            onClick = {}
        ),
        NavigationModel(
            icon = Icons.Outlined.ManageAccounts,
            label = "Accounts",
            onClick = {}
        )
    )

    val drawerItems2 = listOf(
        NavigationModel(
            icon = Icons.Outlined.Backup,
            label = "Backup",
            onClick = {}
        ),
        NavigationModel(
            icon = Icons.Outlined.ImportExport,
            label = "Export",
            onClick = {}
        )
    )

    ModalDrawerSheet(
        modifier = Modifier
            .statusBarsPadding()
            .systemBarsPadding()
            .padding(end = 48.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.keepr),
                    contentDescription = "App Icon",
                    modifier = Modifier
                        .size(100.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Keepr",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .padding(vertical = 4.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Version 1.0",
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                drawerItems0.forEachIndexed { _, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = item.label,
                                fontFamily = FontFamily.Monospace
                            )
                        },
                        selected = false,
                        onClick = item.onClick,
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp))
                drawerItems1.forEachIndexed { _, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = item.label,
                                fontFamily = FontFamily.Monospace
                            )
                        },
                        selected = false,
                        onClick = item.onClick,
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp))
                drawerItems2.forEachIndexed { _, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = item.label,
                                fontFamily = FontFamily.Monospace
                            )
                        },
                        selected = false,
                        onClick = item.onClick,
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp))
                Spacer(modifier = Modifier.weight(1f))

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Close Drawer",
                            fontFamily = FontFamily.Monospace
                        )
                    },
                    onClick = closeDrawer,
                    selected = false,
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "Close"
                        )
                    },
                    modifier = Modifier
                        .padding(8.dp),
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        unselectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        unselectedIconColor = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

private data class NavigationModel(
    val icon: ImageVector,
    val label: String,
    val onClick: () -> Unit
)