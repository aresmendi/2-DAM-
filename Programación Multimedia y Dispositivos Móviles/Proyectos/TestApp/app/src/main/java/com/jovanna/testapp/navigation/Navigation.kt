package com.jovanna.testapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jovanna.testapp.ui.screens.*

@Composable
fun Navigation() {
    val navController = rememberNavController()

    // Detectamos en qué pantalla estamos
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Definimos en qué pantallas SÍ queremos ver la barra
    val showBottomBar = currentRoute in listOf(
        Routes.Dashboard.route,
        Routes.SimulatorList.route,
        Routes.History.route,
        Routes.Profile.route
    )

    // El Scaffold Global envuelve toda la navegación
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                GlobalBottomBar(navController = navController, currentRoute = currentRoute)
            }
        }
    ) { innerPadding ->
        // El NavHost recibe el padding para que el contenido no quede tapado por la barra
        NavHost(
            navController = navController,
            startDestination = Routes.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            // --- RUTAS ---

            composable(Routes.Login.route) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Routes.Dashboard.route) {
                            popUpTo(Routes.Login.route) { inclusive = true }
                        }
                    },
                    onForgotPassword = { navController.navigate(Routes.ForgotPassword.route) },
                    onNavigateToRegister = { navController.navigate(Routes.Register.route) }
                )
            }

            composable(Routes.Register.route) {
                RegisterScreen(
                    onRegisterSuccess = { navController.navigate(Routes.Login.route) },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(Routes.ForgotPassword.route) {
                ForgotPasswordScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onSubmit = { }
                )
            }

            composable(Routes.Dashboard.route) {
                DashboardScreen(
                    onStartSpecificExam = { examId ->
                        navController.navigate(Routes.ExamExecution.createRoute(examId))
                    },
                    onGenerateReview = { navController.navigate(Routes.GenerateReview.route) },
                )
            }

            composable(Routes.SimulatorList.route) {
                SimulatorListScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onStartExam = { examId ->
                        navController.navigate(Routes.ExamExecution.createRoute(examId))
                    }
                )
            }

            composable(
                route = Routes.ExamExecution.route,
                arguments = listOf(navArgument("examId") { type = NavType.StringType })
            ) { backStackEntry ->
                val examId = backStackEntry.arguments?.getString("examId") ?: ""
                ExamExecutionScreen(
                    examId = examId,
                    onFinishExam = { _, _ -> navController.navigate(Routes.Result.route) },
                    onCancelExam = { navController.popBackStack() }
                )
            }

            composable(Routes.Result.route) {
                ResultScreen(
                    score = 0,
                    totalQuestions = 0,
                    onNavigateHome = {
                        navController.navigate(Routes.Dashboard.route) {
                            popUpTo(Routes.Dashboard.route) { inclusive = true }
                        }
                    },
                    onReviewAnswers = { navController.navigate(Routes.History.route) }
                )
            }

            composable(Routes.History.route) {
                HistoryScreen(
                    onNavigateToReview = { attemptId ->
                        navController.navigate(Routes.Review.createRoute(attemptId))
                    },
                )
            }

            composable(
                route = Routes.Review.route,
                arguments = listOf(navArgument("attemptId") { type = NavType.StringType })
            ) { backStackEntry ->
                val attemptId = backStackEntry.arguments?.getString("attemptId") ?: ""
                ReviewScreen(
                    attemptId = attemptId,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(Routes.Profile.route) {
                ProfileScreen(
                    onLogout = {
                        navController.navigate(Routes.Login.route) { popUpTo(0) }
                    },
                )
            }

            composable(Routes.GenerateReview.route) {
                GenerateReviewScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onReviewCreated = { navController.navigate(Routes.SimulatorList.route) }
                )
            }
        }
    }
}

// --- BARRA INFERIOR GLOBAL INTELIGENTE ---
@Composable
fun GlobalBottomBar(navController: NavHostController, currentRoute: String?) {
    NavigationBar {
        // INICIO
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, "Inicio") },
            label = { Text("Inicio") },
            selected = currentRoute == Routes.Dashboard.route,
            onClick = {
                navController.navigate(Routes.Dashboard.route) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        // EXÁMENES
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, "Exámenes") },
            label = { Text("Exámenes") },
            selected = currentRoute == Routes.SimulatorList.route,
            onClick = {
                navController.navigate(Routes.SimulatorList.route) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        // HISTORIAL
        NavigationBarItem(
            icon = { Icon(Icons.Default.DateRange, "Historial") },
            label = { Text("Historial") },
            selected = currentRoute == Routes.History.route,
            onClick = {
                navController.navigate(Routes.History.route) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        // PERFIL
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, "Perfil") },
            label = { Text("Perfil") },
            selected = currentRoute == Routes.Profile.route,
            onClick = {
                navController.navigate(Routes.Profile.route) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}