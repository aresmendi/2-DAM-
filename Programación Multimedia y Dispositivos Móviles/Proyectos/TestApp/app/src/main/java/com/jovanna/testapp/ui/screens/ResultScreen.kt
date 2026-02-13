package com.jovanna.testapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jovanna.testapp.R
import com.jovanna.testapp.ui.theme.TestAppTheme

@Composable
fun ResultScreen(
    score: Int,
    totalQuestions: Int,
    onNavigateHome: () -> Unit,
    onReviewAnswers: () -> Unit
) {
    // Cálculos básicos
    val percentage = if (totalQuestions > 0) score.toFloat() / totalQuestions else 0f
    val isApproved = percentage >= 0.5f

    // Colores y Textos dinámicos según si aprobó o no
    val resultColor = if (isApproved) Color(0xFF4CAF50) else Color(0xFFF44336) // Verde o Rojo

    val resultText = if (isApproved) stringResource(R.string.result_status_approved)
    else stringResource(R.string.result_status_improve)

    val resultIcon = if (isApproved) Icons.Default.CheckCircle else Icons.Default.Refresh

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // TÍTULO
            Text(
                text = stringResource(R.string.title_results),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // GRÁFICO CIRCULAR (Simulado por ahora)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(200.dp)
            ) {
                // Círculo de fondo (Gris claro)
                CircularProgressIndicator(
                    progress = { 1f },
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    strokeWidth = 20.dp,
                )

                // Círculo de progreso (Verde o Rojo)
                CircularProgressIndicator(
                    progress = { percentage },
                    modifier = Modifier.fillMaxSize(),
                    color = resultColor,
                    strokeWidth = 20.dp,
                    strokeCap = StrokeCap.Round
                )

                // Texto central (Porcentaje y Leyenda)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        // Formato: "75%"
                        text = stringResource(R.string.result_percentage_fmt, (percentage * 100).toInt()),
                        style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (isApproved) stringResource(R.string.result_hits)
                        else stringResource(R.string.result_low_performance),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // PUNTUACIÓN Y MENSAJE
            Text(
                // Formato: "Puntuación Total: 15/20"
                text = stringResource(R.string.result_score_total, score, totalQuestions),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = resultIcon, contentDescription = null, tint = resultColor)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = resultText,
                    style = MaterialTheme.typography.headlineSmall,
                    color = resultColor,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Empuja los botones hacia abajo

            // BOTONES DE ACCIÓN

            // Botón Revisar
            OutlinedButton(
                onClick = onReviewAnswers,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(stringResource(R.string.btn_review_answers))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón Volver al Inicio
            Button(
                onClick = onNavigateHome,
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Home, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(stringResource(R.string.btn_back_home))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    TestAppTheme {
        ResultScreen(score = 15, totalQuestions = 20, onNavigateHome = {}, onReviewAnswers = {})
    }
}