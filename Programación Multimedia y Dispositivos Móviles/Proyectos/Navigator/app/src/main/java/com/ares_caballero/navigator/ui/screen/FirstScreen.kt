package com.ares_caballero.navigator.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ares_caballero.navigator.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("First Screen")}
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "App para navegar")
            var nameState by rememberSaveable { mutableStateOf("") }
            TextField(
                value = nameState,
                onValueChange = { nameState = it },
                placeholder = {Text(text = "Introduce tu nombre")}
            )
            Button(
                onClick = {
                    navController.navigate(route = Routes.SecondScreen.createRoute(nameState))
                    nameState = ""
                },
                enabled = nameState.isNotEmpty()
            ) {
                Text(text = "Navega a la siguiente pantalla")
            }
        }
    }
}