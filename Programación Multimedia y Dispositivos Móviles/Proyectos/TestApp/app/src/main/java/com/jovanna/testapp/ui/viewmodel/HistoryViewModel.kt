package com.jovanna.testapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jovanna.testapp.data.SimulatorRepository
import com.jovanna.testapp.model.ExamAttempt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HistoryViewModel : ViewModel() {

    // Estado: Lista de intentos
    private val _history = MutableStateFlow<List<ExamAttempt>>(emptyList())
    val history: StateFlow<List<ExamAttempt>> = _history.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        // Pedimos los datos al repositorio (simulando backend)
        _history.value = SimulatorRepository.getHistory()
    }
}