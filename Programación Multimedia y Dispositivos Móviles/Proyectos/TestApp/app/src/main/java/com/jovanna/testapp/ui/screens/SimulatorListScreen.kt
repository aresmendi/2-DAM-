package com.jovanna.testapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jovanna.testapp.R
import com.jovanna.testapp.model.Exam
import com.jovanna.testapp.model.ExamType
import com.jovanna.testapp.ui.theme.TestAppTheme
import com.jovanna.testapp.ui.viewmodel.SimulatorListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimulatorListScreen(
    initialTab: Int = 0,
    onNavigateBack: () -> Unit,
    onStartExam: (String) -> Unit,
    // Inyectamos el ViewModel
    viewModel: SimulatorListViewModel = viewModel()
) {
    // Observamos los datos
    val allExams by viewModel.exams.collectAsState()

    var selectedTab by remember { mutableIntStateOf(initialTab) }

    // Usamos los strings del XML
    val tabTitles = listOf(
        stringResource(R.string.tab_assigned),
        stringResource(R.string.tab_my_reviews)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.title_simulators), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {

            // PESTAÑAS
            TabRow(selectedTabIndex = selectedTab) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            // FILTRADO DE LISTA
            // Si tab es 0 (Asignados) -> Filtramos por ASSIGNED
            // Si tab es 1 (Mis Repasos) -> Filtramos por CUSTOM
            val filteredExams = if (selectedTab == 0) {
                allExams.filter { it.type == ExamType.ASSIGNED }
            } else {
                allExams.filter { it.type == ExamType.CUSTOM }
            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredExams) { exam ->
                    SimulatorCard(exam = exam, onStart = { onStartExam(exam.title) })
                }
            }
        }
    }
}

@Composable
fun SimulatorCard(exam: Exam, onStart: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = exam.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = exam.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.HelpOutline,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    // FORMATO DINÁMICO: "20 Preg."
                    Text(
                        text = "${exam.questionCount} Preg.",
                        style = MaterialTheme.typography.bodySmall
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    // FORMATO DINÁMICO: "45 min."
                    Text(
                        text = "${exam.durationMinutes} min.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Button(
                    onClick = onStart,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    modifier = Modifier.height(36.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, null, Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(stringResource(R.string.btn_start_exam), fontSize = 12.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SimulatorListPreview() {
    TestAppTheme {
        SimulatorListScreen(onNavigateBack = {}, onStartExam = {})
    }
}