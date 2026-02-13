package com.jovanna.testapp.ui.screens
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jovanna.testapp.R
import com.jovanna.testapp.model.ExamAttempt
import com.jovanna.testapp.ui.theme.TestAppTheme
import com.jovanna.testapp.ui.viewmodel.HistoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onNavigateToReview: (String) -> Unit, // Pasamos el ID del examen
    // Inyectamos el ViewModel
    viewModel: HistoryViewModel = viewModel()
) {
    // Observamos los datos del ViewModel
    val historyList by viewModel.history.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.title_history),
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Usamos la lista real del ViewModel
            items(historyList) { historyItem ->
                HistoryItemCard(item = historyItem, onClick = {onNavigateToReview(historyItem.id)})
            }
        }
    }
}

@Composable
fun HistoryItemCard(item: ExamAttempt, onClick: () -> Unit) {
    // Lógica de aprobado (>= 5)
    val isApproved = item.score >= 5

    // Recursos dinámicos según aprobado/suspenso
    val statusColor = if (isApproved) Color(0xFF4CAF50) else Color(0xFFF44336)
    val statusIcon = if (isApproved) Icons.Default.CheckCircle else Icons.Default.Cancel
    val statusText = if (isApproved) stringResource(R.string.status_approved)
    else stringResource(R.string.status_failed)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Columna de Datos
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.examTitle, // Usamos el título del objeto ExamAttempt
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    // Formato: "24/05/2024 - 15:30"
                    text = stringResource(R.string.date_format, item.date, "10:00"), // Hora simulada, podría estar en el modelo
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    // Formato: "Puntuación: 8/10"
                    text = stringResource(R.string.score_format, item.score, item.totalQuestions),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            // Columna de Estado e Icono
            Column(horizontalAlignment = Alignment.End) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = statusIcon,
                        contentDescription = stringResource(R.string.cd_history_status),
                        tint = statusColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = statusText,
                        style = MaterialTheme.typography.labelSmall,
                        color = statusColor
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = stringResource(R.string.cd_view_detail),
                    tint = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    TestAppTheme {
        HistoryScreen({ })
    }
}