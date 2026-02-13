package com.jovanna.testapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jovanna.testapp.data.SimulatorRepository
import com.jovanna.testapp.model.Question
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExamExecutionViewModel : ViewModel() {

    // ESTADO DE LA UI (lo que la pantalla necesita saber)
    // -------------------------------------------------------
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _timeLeftSeconds = MutableStateFlow(0)
    val timeLeftSeconds: StateFlow<Int> = _timeLeftSeconds.asStateFlow()

    // Mapa de respuestas: [IndicePregunta -> IndiceOpcionSeleccionada]
    private val _selectedAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val selectedAnswers: StateFlow<Map<Int, Int>> = _selectedAnswers.asStateFlow()

    private var timerJob: Job? = null

    // ACCIONES (Lo que la pantalla puede pedir)
    // -------------------------------------------------------

    fun loadExam(examId: String) {
        // Cargar preguntas del repositorio
        val loadedQuestions = SimulatorRepository.getQuestionsForExam(examId)
        _questions.value = loadedQuestions

        // Configurar tiempo (Simulado: 30 minutos si hay preguntas)
        if (loadedQuestions.isNotEmpty()) {
            _timeLeftSeconds.value = 30 * 60
            startTimer()
        }
    }

    private fun startTimer() {
        // Evitamos tener dos temporizadores a la vez
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_timeLeftSeconds.value > 0) {
                delay(1000L)
                _timeLeftSeconds.value -= 1
            }
            // Aquí podríamos autocompletar el examen si llega a 0
        }
    }

    fun selectAnswer(questionIndex: Int, optionIndex: Int) {
        _selectedAnswers.update { currentMap ->
            currentMap.toMutableMap().apply {
                put(questionIndex, optionIndex)
            }
        }
    }

    fun nextQuestion() {
        if (_currentQuestionIndex.value < _questions.value.size - 1) {
            _currentQuestionIndex.value += 1
        }
    }

    fun previousQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value -= 1
        }
    }

    fun calculateScore(): Pair<Int, Int> {
        val total = _questions.value.size
        var correct = 0

        _questions.value.forEachIndexed { index, question ->
            val userAnswer = _selectedAnswers.value[index]
            if (userAnswer == question.correctOptionIndex) {
                correct++
            }
        }
        return Pair(correct, total)
    }

    // Limpiar el timer cuando el ViewModel muere
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}