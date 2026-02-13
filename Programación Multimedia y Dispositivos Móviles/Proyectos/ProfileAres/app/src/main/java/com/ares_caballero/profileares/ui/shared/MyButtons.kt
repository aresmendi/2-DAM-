package com.ares_caballero.profileares.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Suppress("SpellCheckingInspection")
@Composable
fun MyButtons(){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {/* Guardar los cambios*/}) {
            Icon(Icons.Default.Add, contentDescription = "Guardar")
            Spacer(modifier = Modifier.width(6.dp))
            Text("Guardar")
        }
        ElevatedButton(onClick = {/* Cancelar */}) {
            Icon(Icons.Default.Close, contentDescription = "Cancelar")
            Spacer(modifier = Modifier.width(6.dp))
            Text("Cancelar")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyButtonsPreview(){
    MyButtons()
}