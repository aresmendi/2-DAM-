package com.example.perfilinteractivoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppContent()
        }
    }
}

@Suppress("SpellCheckingInspection")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppContent() {
    // ---- Variables de estado ----
    var darkTheme by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableFloatStateOf(50f) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var selectedOption by remember { mutableStateOf("Hombre") }
    var marketing by remember { mutableStateOf(false) } // ✅ corregido

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil de Usuario") },
                actions = {
                    IconButton(onClick = { notificationsEnabled = !notificationsEnabled }) {
                        BadgedBox(
                            badge = {
                                if (notificationsEnabled) Badge { Text("3") }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notificaciones"
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = { /* Acción 1 */ }) {
                    Icon(Icons.Default.Home, contentDescription = "Inicio")
                }
                IconButton(onClick = { /* Acción 2 */ }) {
                    Icon(Icons.Default.Settings, contentDescription = "Ajustes")
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                // ---- Imagen de perfil ----
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Eva Palomar", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("Desarrollador Android", color = Color.Gray)

                Spacer(modifier = Modifier.height(20.dp))

                //Divider(thickness = 1.dp)
                HorizontalDivider(thickness = 1.dp)
                Spacer(modifier = Modifier.height(12.dp))

                // ---- Switch (modo oscuro) ----
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Modo oscuro")
                    Switch(checked = darkTheme, onCheckedChange = { darkTheme = it })
                }

                Spacer(modifier = Modifier.height(12.dp))

                // ---- Slider (nivel de brillo) ----
                Text("Nivel de brillo: ${sliderValue.toInt()}%")
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    valueRange = 0f..100f
                )

                HorizontalDivider(thickness = 1.dp, color = DividerDefaults.color)
                Spacer(modifier = Modifier.height(12.dp))

                // ---- RadioButtons (género) ----
                Text("Género:", fontWeight = FontWeight.SemiBold)
                Row {
                    listOf("Hombre", "Mujer", "Otro").forEach { option ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable { selectedOption = option }
                        ) {
                            RadioButton(
                                selected = (option == selectedOption),
                                onClick = { selectedOption = option }
                            )
                            Text(option)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // ---- CheckBox (marketing) ----
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = marketing,
                        onCheckedChange = { value -> marketing = value }
                    )
                    Text("Recibir correos promocionales")
                }

                Spacer(modifier = Modifier.height(20.dp))

                // ---- Botones ----
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = { /* Guardar cambios */ }) {
                        Icon(Icons.Default.Save, contentDescription = "Guardar")
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Guardar")
                    }
                    ElevatedButton(onClick = { /* Cancelar */ }) {
                        Icon(Icons.Default.Close, contentDescription = "Cancelar")
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Cancelar")
                    }
                }
            }
        }
    }
}
// ---- PREVIEW ----
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMyApp() {
    MyAppContent()
}
