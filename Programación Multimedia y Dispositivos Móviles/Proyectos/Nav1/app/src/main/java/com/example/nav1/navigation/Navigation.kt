package com.example.nav1.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

// Importamos nuestras pantallas (Vistas)
import com.example.nav1.ui.screens.HomeScreen
import com.example.nav1.ui.screens.WelcomeScreen
import com.example.nav1.ui.screens.PerfilScreen
import com.example.nav1.ui.screens.ContactosScreen

// Importamos repositorio de contactos (Capa DATA)
import com.example.nav1.data.ContactRepository

// Importamos iconos
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

/**
 * Navigation()
 *
 * Esta función central controla:
 *  - La navegación entre pantallas
 *  - La barra superior (TopAppBar)
 *  - La barra inferior (BottomBar)
 *  - El padding del Scaffold
 *
 * Es el "Controlador" en un esquema MVC:
 *  VISTA  = Screens (HomeScreen, WelcomeScreen, etc.)
 *  MODELO = carpetas model/ y data/
 *  CONTROLADOR = este Navigation.kt
 *  Es muy básico porque es el siguiente tema pero sin
 *  presentaros esto sería muy muy basica e inutil la explicación de la
 *  Navegación
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {

    // Controlador de navegación
    val navController = rememberNavController()

    // Permite saber en qué pantalla estamos ahora mismo
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Guardamos la ruta actual (por ejemplo: "home", "welcome/Juan", "perfil", etc.)
    val currentRoute = backStackEntry?.destination?.route

    /**
     * SCaffold: estructura visual básica de la app
     *  - topBar     (Barra superior)
     *  - bottomBar  (Barra inferior)
     *  - content    (donde se dibujan las pantallas)
     *
     * Gracias al Scaffold las pantallas quedan siempre dentro del diseño.
     */
    Scaffold(

        // ---------------------------------------------------------
        // BARRA SUPERIOR (TOP BAR)
        // ---------------------------------------------------------
        topBar = {

            /**
             * Elegimos el contenido de la barra superior según
             * la pantalla en la que estamos (currentRoute).
             */

            when (currentRoute) {

                // Pantalla donde se introduce el nombre
                Routes.Home.route -> {
                    TopAppBar(
                        title = { Text("Introduce tu nombre") }
                        // NO hay botón atrás aquí es la pantalla de meter el nombre
                    )
                }

                // Pantalla de bienvenida
                Routes.Welcome.route -> {
                    TopAppBar(
                        title = { Text("Bienvenida") },

                        // Botón atrás que vuelve a la pantalla anterior
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                            }
                        }
                    )
                }

                // Pantalla de Perfil (accesible desde la BottomBar)
                Routes.Perfil.route -> {
                    TopAppBar(
                        title = { Text("Perfil") },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                            }
                        }
                    )
                }

                // Pantalla de contactos
                Routes.Contactos.route -> {
                    TopAppBar(
                        title = { Text("Contactos") },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                            }
                        }
                    )
                }
            }
        },

        // ---------------------------------------------------------
        // BARRA INFERIOR (BOTTOM BAR)
        // ---------------------------------------------------------
        bottomBar = {

            /**
             * IMPORTANTE:
             * La BottomBar SOLO aparece en:
             *  - Welcome
             *  - Perfil
             *  - Contactos
             *
             * NO aparece en HomeScreen porque es la pantalla inicial
             * donde se introduce el nombre.
             */
            if (currentRoute == Routes.Welcome.route ||
                currentRoute == Routes.Perfil.route ||
                currentRoute == Routes.Contactos.route
            ) {

                NavigationBar {

                    // ------------------
                    // BOTÓN HOME
                    // ------------------
                    NavigationBarItem(
                        selected = currentRoute?.startsWith("welcome") == true,
                        onClick = {
                            // Vamos a Welcome manteniendo el nombre del usuario
                            // ?: ""     ← Esto es el operador Elvis. Si el valor es null,
                            //             se usa "" para evitar errores.
                            val user = backStackEntry?.arguments?.getString("user") ?: ""
                            navController.navigate(Routes.Welcome.createRoute(user)) {
                                launchSingleTop = true   // Evita duplicar pantallas
                            }
                        },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home") }
                    )

                    // ------------------
                    // BOTÓN PERFIL
                    // ------------------
                    NavigationBarItem(
                        selected = currentRoute == Routes.Perfil.route,
                        onClick = {
                            navController.navigate(Routes.Perfil.route) {
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                        label = { Text("Perfil") }
                    )

                    // ------------------
                    // BOTÓN CONTACTOS
                    // ------------------
                    NavigationBarItem(
                        selected = currentRoute == Routes.Contactos.route,
                        onClick = {
                            navController.navigate(Routes.Contactos.route) {
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(Icons.Default.People, contentDescription = "Contactos") },
                        label = { Text("Contactos") }
                    )
                }
            }
        }

    ) { innerPadding ->

        /**
         * Área central donde se dibujan las pantallas.
         * El parámetro innerPadding permite que el contenido
         * NO quede debajo del TopBar y BottomBar.
         */
        //NavHost es el lugar donde Jetpack Compose mete
        //la pantalla correcta según la ruta actual.
        NavHost(
            navController = navController,
            startDestination = Routes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            /**
             * -------------------------------
             * RUTA: HOME
             * Pantalla inicial donde se pide el nombre
             * -------------------------------
             */
            composable(Routes.Home.route) {
                HomeScreen(
                    onNavigate = { name ->
                        // Navega a welcome pasando el nombre
                        navController.navigate(Routes.Welcome.createRoute(name))
                    }
                )
            }

            /**
             * -------------------------------
             * RUTA: WELCOME / BIENVENIDA
             * Recibe un nombre como parámetro
             * -------------------------------
             */
            composable(
                route = Routes.Welcome.route,
                arguments = listOf(navArgument("user") { type = NavType.StringType })
            ) {
                val user = it.arguments?.getString("user") ?: ""
                WelcomeScreen(name = user)
            }

            /**
             * -------------------------------
             * RUTA: PERFIL
             * -------------------------------
             */
            composable(Routes.Perfil.route) {
                PerfilScreen()
            }

            /**
             * -------------------------------
             * RUTA: CONTACTOS
             * Esta pantalla recibe DATOS desde fuera (state hoisting)
             * Los datos vienen del repositorio (carpeta DATA)
             * -------------------------------
             */
            composable(Routes.Contactos.route) {

                // CARGA DE DATOS: fuera de la pantalla (STATE HOISTING)
                val contactosPorLetra = remember { ContactRepository.getContacts() }

                // PASAMOS LOS DATOS A LA PANTALLA (es stateless)
                ContactosScreen(contactosPorLetra = contactosPorLetra)
            }
        }
    }
}
