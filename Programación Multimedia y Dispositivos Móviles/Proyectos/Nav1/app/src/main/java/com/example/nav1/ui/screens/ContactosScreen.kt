package com.example.nav1.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.nav1.model.Person

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactosScreen(
    contactosPorLetra: Map<Char, List<Person>>
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {

        contactosPorLetra.forEach { (letra, contactos) ->

            stickyHeader {
                Surface(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                    tonalElevation = 4.dp
                ) {
                    Text(
                        text = letra.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            items(contactos) { persona ->
                ContactItem(persona)
            }
        }
    }
}

@Composable
fun ContactItem(persona: Person) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "${persona.nombre} ${persona.apellidos}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text("Edad: ${persona.edad}")
            Text("Nacionalidad: ${persona.nacionalidad}")
        }
    }
}
