package com.jovanna.testapp.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login_screen")
    object Register : Routes("register_screen")
    object ForgotPassword : Routes("forgot_password_screen")

    object Dashboard : Routes("dashboard_screen")

    // Rutas con argumentos
    object SimulatorList : Routes("simulator_list_screen")
    object GenerateReview : Routes("generate_review_screen")
    object Profile : Routes("profile_screen")
    object History : Routes("history_screen")

    // Rutas que reciben parámetros (Ej: "exam_execution_screen/math-101")
    object ExamExecution : Routes("exam_execution_screen/{examId}") {
        fun createRoute(examId: String) = "exam_execution_screen/$examId"
    }

    object Review : Routes("review_screen/{attemptId}") {
        fun createRoute(attemptId: String) = "review_screen/$attemptId"
    }

    object Result : Routes("result_screen")
// Podríamos pasar score aquí, pero por simplicidad usamos el ViewModel compartido o parámetros simples.
}