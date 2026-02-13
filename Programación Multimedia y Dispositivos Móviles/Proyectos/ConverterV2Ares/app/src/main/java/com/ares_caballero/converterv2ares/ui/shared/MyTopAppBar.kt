package com.ares_caballero.converterv2ares.ui.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Suppress("SpellCheckingInspection")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    var notificationsEnabled by remember { mutableStateOf(true) }
    TopAppBar(
        title = { Text(text = "Ares Caballero Rey") },
        actions = {
            IconButton(onClick = {
                notificationsEnabled = !notificationsEnabled
            }) {
                BadgedBox(
                    badge = {
                        if (notificationsEnabled) Badge { Text("3") }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notificationes"
                    )
                }
            }
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyTopAppBarPreview() {
    MyTopAppBar()
}
