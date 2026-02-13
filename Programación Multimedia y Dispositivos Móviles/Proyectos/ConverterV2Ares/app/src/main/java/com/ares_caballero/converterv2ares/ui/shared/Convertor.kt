package com.ares_caballero.converterv2ares.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ares_caballero.converterv2ares.R


@OptIn(ExperimentalMaterial3Api::class)
@Suppress("SpellCheckingInspection")
@Composable
fun Convertor() {
    var text by rememberSaveable { mutableStateOf("") }
    var number by rememberSaveable { mutableDoubleStateOf(0.0) }
    var binary by rememberSaveable { mutableStateOf("") }
    var hexa by rememberSaveable { mutableStateOf("") }
    var change by rememberSaveable { mutableStateOf(false) }

    number = text.toDoubleOrNull() ?: 0.0

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .padding(
                start = dimensionResource(id = R.dimen.firstColumnHorPadding),
                top = dimensionResource(id = R.dimen.firstColumnVerPadding)
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // TextField en su propia fila
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Introduzca número") },
            modifier = Modifier.fillMaxWidth()
        )

        // Switch y botón convertir juntos
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = change,
                    onCheckedChange = { change = it }
                )
                Text(
                    text = if (change) "Binary" else "Hexadecimal",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Button(
                onClick = {
                    if (change) {
                        binary = number.toInt().toString(2)
                    } else {
                        hexa = number.toInt().toString(16).uppercase()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.purple_700),
                    contentColor = colorResource(R.color.white)
                )
            ) {
                Text(text = "Convertir")
            }
        }

        // Resultado
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (change) {
                    stringResource(id = R.string.binary, binary)
                } else {
                    stringResource(id = R.string.hexa, hexa)
                },
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 25.sp
            )
        }

        // Botón reset
        Button(
            onClick = { text = ""; number = 0.0; binary = ""; hexa = "" },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.purple_700),
                contentColor = colorResource(R.color.white)
            ),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.resetText),
                fontSize = 15.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ConvertorPreview() {
    Convertor()
}