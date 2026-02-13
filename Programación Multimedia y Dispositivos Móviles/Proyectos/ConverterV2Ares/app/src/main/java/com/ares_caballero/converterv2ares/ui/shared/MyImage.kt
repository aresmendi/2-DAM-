package com.ares_caballero.converterv2ares.ui.shared

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ares_caballero.converterv2ares.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Suppress("SpellCheckingInspection")
@Composable
fun MyImage(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Foto de Perfil",
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape)
    )
    Column(
        modifier = Modifier
            .padding(26.dp)
    ) {
        Text("Ares Caballero", fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyImagePreview() {
    MyImage(image = R.drawable.my_profile)
}