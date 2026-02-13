package com.jovanna.testapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jovanna.testapp.data.SimulatorRepository
import com.jovanna.testapp.model.Exam
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SimulatorListViewModel : ViewModel() {

    private val _exams = MutableStateFlow<List<Exam>>(emptyList())
    val exams: StateFlow<List<Exam>> = _exams.asStateFlow()

    init {
        loadExams()
    }

    private fun loadExams() {
        // Pedimos todos los exámenes al repositorio
        _exams.value = SimulatorRepository.getExams()
    }
}