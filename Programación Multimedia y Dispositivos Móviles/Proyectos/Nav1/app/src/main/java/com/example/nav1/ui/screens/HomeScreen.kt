package com.example.nav1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nav1.ui.common.NameInputCard

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {

    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        NameInputCard(
            name = name,
            onNameChange = { name = it },
            onContinue = {
                if (name.isNotBlank()) onNavigate(name)
            }
        )
    }
}

