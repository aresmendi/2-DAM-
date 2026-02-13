package com.jovanna.testapp.model

data class Exam(
    val id: String,
    val title: String,
    val description: String,
    val durationMinutes: Int,
    val questionCount: Int,
    val type: ExamType,
    val questions: List<Question> = emptyList()
)

enum class ExamType {
    ASSIGNED, // Exámenes de los profesores
    CUSTOM    // Repasos creados por el alumno
}