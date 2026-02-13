package com.jovanna.testapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jovanna.testapp.R
import com.jovanna.testapp.model.Exam
import com.jovanna.testapp.ui.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    onStartSpecificExam: (String) -> Unit,
    onGenerateReview: () -> Unit,
    // Inyectamos el ViewModel aquí. Si no se pasa nada, crea uno nuevo.
    viewModel: DashboardViewModel = viewModel()
) {
    // Observamos el estado de la lista de exámenes
    // 'collectAsState' hace que la UI se redibuje si la lista cambia
    val examsList by viewModel.exams.collectAsState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // SALUDO
            item {
                Column {
                    Text(
                        text = stringResource(R.string.dashboard_greeting, "Jovanna"),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = stringResource(R.string.dashboard_subtitle),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            // ESTADÍSTICAS
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    StatCard(
                        title = stringResource(R.string.stat_last_score),
                        value = "8.5",
                        icon = Icons.AutoMirrored.Filled.List,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = stringResource(R.string.stat_exams),
                        value = "12",
                        icon = Icons.Default.DateRange,
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // BOTÓN GENERAR REPASO
            item {
                Button(
                    onClick = onGenerateReview,
                    modifier = Modifier.fillMaxWidth().height(80.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = stringResource(R.string.cd_generate_icon),
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                text = stringResource(R.string.btn_generate_review),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(R.string.btn_generate_review_sub),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }

            // TÍTULO LISTA
            item {
                Text(
                    text = stringResource(R.string.title_continue_exams),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // LISTA DE EXÁMENES (Desde el ViewModel)
            items(examsList) { exam ->
                ExamQuickAccessCard(
                    exam = exam,
                    onClick = { onStartSpecificExam(exam.title) }
                )
            }
        }
    }


@Composable
fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))
            Text(text = title, style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun ExamQuickAccessCard(exam: Exam, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = exam.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    // "20 preguntas - 30 min" formateado dinámicamente
                    text = stringResource(R.string.exam_details_format, exam.questionCount, exam.durationMinutes),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = stringResource(R.string.cd_start_exam),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

