package com.ares_caballero.navigator.ui.navigation

import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ares_caballero.navigator.ui.screen.FirstScreen
import com.ares_caballero.navigator.ui.screen.SecondScreen

@Suppress("SpellCheckingInspection")
@Composable
fun Navigation() {
    //Constante para gestionar el estado que se debe propagar a todas las pantallas
    val navController = rememberNavController()

    //Elemento que conoce las diferentes pantallas y cual es la primera en lanzarse
    NavHost(
        navController = navController,
        startDestination = Routes.FirstScreen.route
    ) {
        //Definimos la primera pantalla
        composable(
            route = Routes.FirstScreen.route
        ){
            FirstScreen(navController)
        }
        //Definimos la segunda pantalla
        composable(
            route = Routes.SecondScreen.route,
            arguments = listOf(navArgument("name"){
                type = NavType.StringType
            })
        ){
            val argument = it.arguments?.getString("name")
            requireNotNull(argument)
            SecondScreen(navController,argument)
        }

    }
}