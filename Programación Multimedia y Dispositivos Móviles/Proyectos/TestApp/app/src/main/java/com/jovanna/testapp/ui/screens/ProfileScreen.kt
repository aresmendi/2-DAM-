package com.jovanna.testapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jovanna.testapp.R
import com.jovanna.testapp.ui.theme.TestAppTheme

@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // FOTO DE PERFIL
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(60.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.title_profile),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // TARJETA DE DATOS
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    // Datos simulados (En el futuro vendrán de UserViewModel)
                    ProfileInfoRow(label = stringResource(R.string.label_name), value = "Jovanna Rojas")

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    ProfileInfoRow(label = stringResource(R.string.label_username), value = "jovanna.r")

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    ProfileInfoRow(label = stringResource(R.string.label_email), value = "jovanna.rojas@email.com")

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    ProfileInfoRow(label = stringResource(R.string.label_center), value = "IES Serpis")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // CAMBIAR CONTRASEÑA
            TextButton(onClick = { /* Lógica futura */ }) {
                Text(
                    text = stringResource(R.string.btn_change_password),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // BOTÓN CERRAR SESIÓN
            Button(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = stringResource(R.string.cd_logout)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.btn_logout), fontSize = 16.sp)
            }
        }
    }


@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.SemiBold, color = Color.Gray)
        Text(text = value, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    TestAppTheme {
        ProfileScreen{}
    }
}