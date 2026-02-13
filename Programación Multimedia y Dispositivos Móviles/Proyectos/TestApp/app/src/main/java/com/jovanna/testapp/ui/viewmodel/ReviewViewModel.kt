package com.jovanna.testapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jovanna.testapp.data.SimulatorRepository
import com.jovanna.testapp.model.ExamAttempt
import com.jovanna.testapp.model.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReviewViewModel : ViewModel() {

    // Estado del intento (puede ser nulo si no se encuentra)
    private val _attempt = MutableStateFlow<ExamAttempt?>(null)
    val attempt: StateFlow<ExamAttempt?> = _attempt.asStateFlow()

    // Estado de las preguntas asociadas a ese intento
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()

    fun loadAttempt(attemptId: String) {
        // Buscamos el intento
        val foundAttempt = SimulatorRepository.getAttemptById(attemptId)

        if (foundAttempt != null) {
            _attempt.value = foundAttempt
            // Si existe, cargamos las preguntas de ese examen
            _questions.value = SimulatorRepository.getQuestionsForExam(foundAttempt.examId)
        }
    }
}