package com.jovanna.testapp.model

data class ExamAttempt(
    val id: String,
    val examId: String,
    val examTitle: String,
    val date: String,
    val score: Int,
    val totalQuestions: Int,
    // Mapa: Clave = Índice de Pregunta, Valor = Índice de Respuesta del usuario
    val userAnswers: Map<Int, Int> = emptyMap()
)