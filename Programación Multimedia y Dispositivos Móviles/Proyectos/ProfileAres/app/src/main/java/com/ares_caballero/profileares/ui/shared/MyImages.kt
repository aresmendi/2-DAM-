package com.ares_caballero.profileares.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun MyImages(image: String, description: String) {
    var meGusta by remember { mutableStateOf(true) }
    Column {
        AsyncImage(
            model = image,
            contentDescription = "Paisaje"
        )
        Text(description)
        Button(onClick = { meGusta = !meGusta }) {
            Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = "Me gusta"
            )
            Text(
                if (meGusta) "Me gusta" else "No me gusta",
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}