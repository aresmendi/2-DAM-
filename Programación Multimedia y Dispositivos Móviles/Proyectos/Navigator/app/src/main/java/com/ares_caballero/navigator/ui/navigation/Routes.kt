package com.ares_caballero.navigator.ui.navigation

sealed class Routes(val route: String)  {
    object FirstScreen : Routes("first_screen")
    object SecondScreen : Routes("second_screen/{name}"){
        fun createRoute(name: String) = "second_screen/$name"
    }

}