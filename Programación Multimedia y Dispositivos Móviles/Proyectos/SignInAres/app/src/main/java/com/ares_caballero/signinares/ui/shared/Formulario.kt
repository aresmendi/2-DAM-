package com.ares_caballero.signinares.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("SpellCheckingInspection")
@Composable
fun Formulario() {
    //Estados del formulario
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tlfString by remember { mutableStateOf("") }
    var birthDateString by remember { mutableStateOf("") }
    var selectedItems by remember { mutableStateOf(listOf<String>()) }

    // Estados de control
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val availableOptions = listOf("Kotlin", "Java", "Python", "JavaScript", "C++", "Assembly")
    var showDialog by rememberSaveable { mutableStateOf(false) }
    //Funciones
    fun clearForm() {
        nombre = ""
        apellidos = ""
        email = ""
        tlfString = ""
        birthDateString = ""
        selectedItems = listOf()
    }

    fun isFormValid(): Boolean {
        return nombre.isNotEmpty() &&
                apellidos.isNotEmpty() &&
                email.isNotEmpty() && email.contains("@") &&
                tlfString.isNotBlank() && tlfString.length == 9 &&
                birthDateString.isNotBlank() &&
                selectedItems.isNotEmpty()
    }
    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.padding(top = 8.dp)) {
            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }
        Row(modifier = Modifier.padding(top = 8.dp)) {
            TextField(
                value = apellidos,
                onValueChange = { apellidos = it },
                label = { Text("Apellidos") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }
        Row(modifier = Modifier.padding(top = 8.dp)) {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = email.isNotBlank() && !email.contains("@")
            )
        }
        Row(modifier = Modifier.padding(top = 8.dp)) {
            TextField(
                value = tlfString,
                onValueChange = { newValue ->
                    if (newValue.isEmpty() || newValue.toIntOrNull() != null) {
                        tlfString = newValue
                    }
                },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isError = tlfString.isNotBlank() && tlfString.length != 9
            )
        }
        Row(modifier = Modifier.padding(top = 8.dp)) {
            OutlinedTextField(
                value = birthDateString,
                onValueChange = { },
                label = { Text("Fecha de nacimiento") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Seleccionar fecha")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(modifier = Modifier.padding(top = 8.dp)) {
            Column {
                Text("Selecciona tus lenguajes:", fontWeight = FontWeight.Bold)
                availableOptions.forEach { option ->
                    FilterChip(
                        selected = selectedItems.contains(option),
                        onClick = {
                            selectedItems = if (selectedItems.contains(option)) {
                                selectedItems - option
                            } else {
                                selectedItems + option
                            }
                        },
                        label = { Text(option) }
                    )
                }
            }
        }
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { clearForm() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Borrar")
            }
            Button(
                onClick = {showDialog = true},
                enabled = isFormValid(),
                modifier = Modifier.weight(1f)
            ) {
                Text("Enviar")
            }
        }
    }


    // Diálogo del DatePicker
    // Solo se muestra cuando showDatePicker es true
    if (showDatePicker) {
        DatePickerDialog(
            // Función que se ejecuta cuando el usuario cierra el diálogo tocando fuera de él
            onDismissRequest = { showDatePicker = false },

            // Botón de confirmación (parte inferior derecha del diálogo)
            confirmButton = {
                TextButton(onClick = {
                    // selectedDateMillis contiene la fecha seleccionada en milisegundos (puede ser null)
                    datePickerState.selectedDateMillis?.let { millis ->
                        // Crea un formateador de fecha con el patrón día/mes/año
                        val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            // Convierte los milisegundos a un objeto Date y lo formatea como String
                            .format(Date(millis))
                        // Guarda la fecha formateada en la variable de estado
                        birthDateString = date
                    }
                    // Cierra el diálogo después de confirmar
                    showDatePicker = false
                }) {
                    // Texto que aparece en el botón de confirmar
                    Text("Aceptar")
                }
            },

            // Botón de cancelar (parte inferior izquierda del diálogo)
            dismissButton = {
                TextButton(onClick = {
                    // Cierra el diálogo sin guardar cambios
                    showDatePicker = false
                }) {
                    // Texto que aparece en el botón de cancelar
                    Text("Cancelar")
                }
            }
        ) {
            // Contenido del diálogo: el selector de fecha
            DatePicker(
                // Estado que mantiene la fecha seleccionada
                state = datePickerState,
                // Título que aparece en la parte superior del selector
                title = { Text("Seleccione su fecha de nacimiento") },
                // Permite alternar entre vista de calendario y vista de entrada de texto
                showModeToggle = true
            )
        }
    }

    // AlertDialog con resumen
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    clearForm() // Limpiar formulario después de confirmar
                }) {
                    Text("Aceptar")
                }
            },
            icon = {
                Icon(Icons.Default.PushPin, contentDescription = "Resumen")
            },
            title = {
                Text("Resumen del Registro")
            },
            text = {
                Column {
                    Text("Nombre: $nombre", fontWeight = FontWeight.Bold)
                    Text("Apellidos: $apellidos")
                    Text("Email: $email")
                    Text("Teléfono: $tlfString")
                    Text("Fecha de nacimiento: $birthDateString")
                    Text("Lenguajes seleccionados:")
                    selectedItems.forEach { item ->
                        Text("  • $item")
                    }
                }
            }
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FormularioPreview() {
    Formulario()
}