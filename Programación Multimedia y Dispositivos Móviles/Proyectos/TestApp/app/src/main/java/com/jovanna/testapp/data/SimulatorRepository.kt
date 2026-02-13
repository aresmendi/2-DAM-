package com.jovanna.testapp.data

import com.jovanna.testapp.model.*

/**
 * Este repositorio simula ser el Backend (Spring Boot).
 * Por ahora devuelve datos fijos, pero aquí es donde conectaremos Retrofit más adelante.
 */
object SimulatorRepository {

    // --- 1. DATOS DE EXÁMENES (Para el Dashboard y Listado) ---
    fun getExams(): List<Exam> {
        return listOf(
            Exam(
                id = "math-101",
                title = "Matemáticas I - Álgebra",
                description = "Ecuaciones de segundo grado y polinomios.",
                durationMinutes = 45,
                questionCount = 20,
                type = ExamType.ASSIGNED
            ),
            Exam(
                id = "art-202",
                title = "Historia del Arte",
                description = "Renacimiento y Barroco.",
                durationMinutes = 30,
                questionCount = 15,
                type = ExamType.ASSIGNED
            ),
            Exam(
                id = "review-001",
                title = "Repaso Personalizado 1",
                description = "Basado en tus errores de mates.",
                durationMinutes = 10,
                questionCount = 5,
                type = ExamType.CUSTOM
            )
        )
    }

    // --- 2. PREGUNTAS (Para la Ejecución del Examen) ---
    // Simulamos que pedimos las preguntas de un examen específico por su ID
    fun getQuestionsForExam(examId: String): List<Question> {
        // En una app real, aquí haríamos una llamada a la API: api.getQuestions(examId)
        return listOf(
            Question(
                id = "q1",
                text = "¿Cuál es el valor de 'x' en 2x + 5 = 15?",
                options = listOf("2", "5", "10", "7"),
                correctOptionIndex = 1, // 5
                explanation = "Restamos 5 a 15 (queda 10) y dividimos entre 2."
            ),
            Question(
                id = "q2",
                text = "¿Quién pintó 'La Gioconda'?",
                options = listOf("Miguel Ángel", "Rafael", "Da Vinci", "Donatello"),
                correctOptionIndex = 2,
                explanation = "Leonardo da Vinci la pintó a principios del siglo XVI."
            ),
            Question(
                id = "q3",
                text = "¿Capital de Francia?",
                options = listOf("Londres", "Berlín", "Madrid", "París"),
                correctOptionIndex = 3,
                explanation = "París es la capital histórica y política."
            ),
            Question(
                id = "q4",
                text = "¿Año de llegada a la Luna?",
                options = listOf("1969", "1970", "1965", "1950"),
                correctOptionIndex = 0,
                explanation = "El Apolo 11 alunizó en 1969."
            )
        )
    }

    // --- 3. HISTORIAL ----
    fun getHistory(): List<ExamAttempt> {
        return listOf(
            ExamAttempt(
                id = "att-1",
                examId = "math-101",
                examTitle = "Matemáticas I",
                date = "24/05/2024",
                score = 8,
                totalQuestions = 10
            ),
            ExamAttempt(
                id = "att-2",
                examId = "art-202",
                examTitle = "Historia del Arte",
                date = "22/05/2024",
                score = 3, //suspenso
                totalQuestions = 10
            )
        )
    }

    // --- 4. TEMAS DE ERROR (Para Generar Repaso) ---
    fun getErrorTopics(): List<ErrorTopic> {
        return listOf(
            ErrorTopic("t1", "Ecuaciones", 5, true),
            ErrorTopic("t2", "Renacimiento", 3, true),
            ErrorTopic("t3", "Cinemática", 4, false)
        )
    }

    // --- NUEVO: Buscar un intento por ID ---
    fun getAttemptById(attemptId: String): ExamAttempt? {
        return getHistory().find { it.id == attemptId }
    }
}