package com.ares_caballero.profileares.ui.shared

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Suppress("SpellCheckingInspection")
@Composable
fun MyBottomBar() {
    var messagesToSee by remember { mutableIntStateOf(0) }
    Column {
        BottomAppBar {
            IconButton(onClick = { /*Acción 1*/ }) {
                Icon(Icons.Default.Home, contentDescription = "Inicio")
            }
            IconButton(onClick = { /*Acción 2*/ }) {
                Icon(Icons.Default.Settings, contentDescription = "Ajustes")
            }
            Spacer(modifier = Modifier.weight(1f))
            BadgedBox(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                badge = {
                    Badge { Text(messagesToSee.toString()) }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Mensajes"
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            IconButton(onClick = { messagesToSee++ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "SumarMensajes"
                )
            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MyBottomBarPreview() {
    MyBottomBar()
}
