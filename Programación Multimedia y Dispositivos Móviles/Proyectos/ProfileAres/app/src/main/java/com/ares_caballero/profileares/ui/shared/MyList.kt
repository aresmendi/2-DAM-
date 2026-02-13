package com.ares_caballero.profileares.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Suppress("SpellCheckingInspection")
@Composable
fun MyList() {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cosas que me gustan",
             fontSize = 20.sp,
             fontWeight = FontWeight.Bold)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Icon(
                    Icons.Default.Build,
                    contentDescription = "Programar"
                )
                Text("- Programar",
                     fontSize = 18.sp)
            }
            Row {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "La música"
                )
                Text("- La música",
                     fontSize = 18.sp)
            }
            Row {
                Icon(
                    Icons.Default.Create,
                    contentDescription = "Diseñar videojuegos"
                )
                Text("- Diseñar videojuegos",
                     fontSize = 18.sp)
            }
            Row {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Aprender"
                )
                Text("- Aprender cosas nuevas",
                     fontSize = 18.sp)
            }
            Row {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "Mis perras"
                )
                Text("- Mis perras",
                     fontSize = 18.sp)
            }
        }
    }
}

@Preview
@Composable
private fun MyListPreview() {
    MyList()
}