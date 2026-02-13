package com.ares_caballero.signinares.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ares_caballero.signinares.ui.shared.Formulario
import com.ares_caballero.signinares.ui.shared.MyBottomBar

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MyBottomBar() }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            item { Formulario() }

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}