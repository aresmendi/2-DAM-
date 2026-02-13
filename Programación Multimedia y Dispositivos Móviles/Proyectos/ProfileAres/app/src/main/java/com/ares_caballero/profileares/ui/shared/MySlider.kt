package com.ares_caballero.profileares.ui.shared

import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Suppress("SpellCheckingInspection")
@Composable
fun MySlider() {
    var sliderValue by remember { mutableFloatStateOf(50f) }
    Text("Nivel de brillo: ${sliderValue.toInt()}%")
    Slider(
        value = sliderValue,
        onValueChange = { sliderValue = it },
        valueRange = 0f..100f
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MySliderPreview() {
    MySlider()
}
