package com.ares_caballero.profileares.ui.shared

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@Suppress("SpellCheckingInspection")
@Composable
fun MyCheckBox(){
    var marketing by remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = marketing,
            onCheckedChange = {value -> marketing = value}
        )
        Text("Recibir correos promocionales")
    }
}