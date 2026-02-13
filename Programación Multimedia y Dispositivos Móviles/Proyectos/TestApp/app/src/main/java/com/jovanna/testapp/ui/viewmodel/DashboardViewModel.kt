package com.jovanna.testapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jovanna.testapp.data.SimulatorRepository
import com.jovanna.testapp.model.Exam
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel : ViewModel() {

    // _exams es privado y mutable (solo el ViewModel lo puede cambiar)
    private val _exams = MutableStateFlow<List<Exam>>(emptyList())

    // exams es público e inmutable (la Vista solo puede leerlo)
    val exams: StateFlow<List<Exam>> = _exams.asStateFlow()

    init {
        // Al iniciarse el ViewModel, cargamos los datos
        loadExams()
    }

    private fun loadExams() {
        // Pedimos los datos al Repositorio
        // En el futuro, aquí manejaremos hilos en segundo plano (Coroutines) para llamar a Spring Boot
        _exams.value = SimulatorRepository.getExams()
    }
}