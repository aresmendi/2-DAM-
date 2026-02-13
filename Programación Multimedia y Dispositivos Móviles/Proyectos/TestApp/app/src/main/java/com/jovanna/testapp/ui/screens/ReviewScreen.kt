package com.jovanna.testapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jovanna.testapp.R
import com.jovanna.testapp.model.Question
import com.jovanna.testapp.ui.viewmodel.ReviewViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(
    attemptId: String, // <--- Recibimos el ID
    onNavigateBack: () -> Unit,
    viewModel: ReviewViewModel = viewModel()
) {
    // Cargar datos al entrar
    LaunchedEffect(attemptId) {
        viewModel.loadAttempt(attemptId)
    }

    val attempt by viewModel.attempt.collectAsState()
    val questions by viewModel.questions.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_review)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->

        // Si no hay datos aún, mostramos carga (o vacío)
        if (attempt == null || questions.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            // Mostramos el contenido
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // TARJETA RESUMEN
                Surface(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = stringResource(R.string.review_summary_title),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            // "Has acertado 8 de 10 preguntas"
                            text = stringResource(R.string.review_summary_fmt, attempt!!.score, questions.size),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // LISTA DE PREGUNTAS
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(questions) { index, question ->
                        // Buscamos qué respondió el usuario en esta pregunta (index)
                        val userAnswerIndex = attempt!!.userAnswers[index]

                        ReviewQuestionCard(
                            question = question,
                            userAnswerIndex = userAnswerIndex
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewQuestionCard(question: Question, userAnswerIndex: Int?) {
    var expanded by remember { mutableStateOf(false) }

    // Lógica: ¿Es correcta?
    val isCorrect = userAnswerIndex == question.correctOptionIndex

    val statusColor = if (isCorrect) Color(0xFF4CAF50) else Color(0xFFF44336)
    val icon = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Close

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth().animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(16.dp)
        ) {
            // CABECERA
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = statusColor)
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = question.text,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                    maxLines = if (expanded) Int.MAX_VALUE else 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }

            // DETALLE (Solo si expandido)
            if (expanded) {
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
                Spacer(modifier = Modifier.height(12.dp))

                // Respuesta del usuario
                val userText = if (userAnswerIndex != null) question.options[userAnswerIndex] else stringResource(R.string.review_unanswered)

                ReviewOptionRow(
                    label = stringResource(R.string.review_your_answer),
                    text = userText,
                    color = statusColor,
                    isBold = true
                )

                // Si falló, mostramos la correcta
                if (!isCorrect) {
                    Spacer(modifier = Modifier.height(8.dp))
                    ReviewOptionRow(
                        label = stringResource(R.string.review_correct_answer),
                        text = question.options[question.correctOptionIndex],
                        color = Color(0xFF4CAF50),
                        isBold = true
                    )
                }

                // Explicación (Si existe en el modelo)
                if (!question.explanation.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "ℹ\uFE0F ${question.explanation}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ReviewOptionRow(label: String, text: String, color: Color, isBold: Boolean = false) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.width(140.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
            ),
            color = color
        )
    }
}