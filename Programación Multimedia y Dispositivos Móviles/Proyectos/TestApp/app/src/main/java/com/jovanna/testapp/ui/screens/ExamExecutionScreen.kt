package com.jovanna.testapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jovanna.testapp.R
import com.jovanna.testapp.ui.viewmodel.ExamExecutionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamExecutionScreen(
    examId: String,
    onFinishExam: (Int, Int) -> Unit,
    onCancelExam: () -> Unit,
    viewModel: ExamExecutionViewModel = viewModel()
) {
    // Cargamos el examen al iniciar (solo una vez)
    LaunchedEffect(examId) {
        viewModel.loadExam(examId)
    }

    // Observamos el estado del ViewModel
    val questions by viewModel.questions.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val timeLeft by viewModel.timeLeftSeconds.collectAsState()
    val selectedAnswers by viewModel.selectedAnswers.collectAsState()

    // Si aún no hay preguntas cargadas, mostramos carga o vacío
    if (questions.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(text = stringResource(R.string.exam_in_progress), style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = stringResource(R.string.exam_question_counter, currentIndex + 1, questions.size),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                },
                actions = {
                    AssistChip(
                        onClick = {},
                        label = { Text(formatTime(timeLeft)) },
                        leadingIcon = {
                            Icon(Icons.Default.AccessTime, stringResource(R.string.cd_clock_icon), Modifier.size(16.dp))
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = if (timeLeft < 60) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.surface
                        )
                    )
                    IconButton(onClick = onCancelExam) {
                        Icon(Icons.Default.Close, stringResource(R.string.cd_close_exam))
                    }
                }
            )
        },
        bottomBar = {
            ExamBottomBar(
                canGoBack = currentIndex > 0,
                isLastQuestion = currentIndex == questions.size - 1,
                onPrevious = { viewModel.previousQuestion() },
                onNext = { viewModel.nextQuestion() },
                onFinish = {
                    val (score, total) = viewModel.calculateScore()
                    onFinishExam(score, total)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Barra de progreso
            LinearProgressIndicator(
                progress = { (currentIndex + 1) / questions.size.toFloat() },
                modifier = Modifier.fillMaxWidth().height(8.dp),
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Pregunta actual
            val currentQuestion = questions[currentIndex]

            Text(
                text = currentQuestion.text,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Opciones
            currentQuestion.options.forEachIndexed { index, optionText ->
                val isSelected = selectedAnswers[currentIndex] == index

                OptionCard(
                    text = optionText,
                    isSelected = isSelected,
                    onClick = { viewModel.selectAnswer(currentIndex, index) }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}


@Composable
fun OptionCard(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        border = if (isSelected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = isSelected, onClick = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun ExamBottomBar(
    canGoBack: Boolean,
    isLastQuestion: Boolean,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit
) {
    Surface(tonalElevation = 3.dp, shadowElevation = 8.dp) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = onPrevious,
                enabled = canGoBack,
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                Spacer(Modifier.width(4.dp))
                Text(stringResource(R.string.btn_previous))
            }

            Spacer(Modifier.width(16.dp))

            Button(
                onClick = if (isLastQuestion) onFinish else onNext,
                modifier = Modifier.weight(1f)
            ) {
                Text(if (isLastQuestion) stringResource(R.string.btn_finish) else stringResource(R.string.btn_next))
                Spacer(Modifier.width(4.dp))
                Icon(if (isLastQuestion) Icons.Default.CheckCircle else Icons.AutoMirrored.Filled.ArrowForward, null)
            }
        }
    }
}

fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return "%02d:%02d".format(minutes, remainingSeconds)
}