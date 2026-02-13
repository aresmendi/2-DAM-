package com.example.nav1.navigation
// ↑ Este archivo pertenece al paquete 'navigation' de la app Nav1.
//   Aquí agrupamos todo lo relacionado con la navegación (rutas, NavHost, etc.)

// 'sealed class' = clase sellada.
// Es una clase que solo puede tener un conjunto LIMITADO de subtipos,
// todos definidos dentro de este mismo archivo.
// Esto es muy útil para representar "un conjunto de opciones cerradas":
// en este caso, las diferentes pantallas/rutas de la app.
sealed class Routes(val route: String) {

    // ----------------------------
    // RUTA: Home
    // ----------------------------
    // 'object Home' es un objeto singleton que representa la ruta "home".
    // Extiende (hereda) de Routes y le pasa el String "home" al constructor.
    //
    // Esta será la PANTALLA INICIAL:
    // - Es donde el usuario introduce su nombre.
    // - No muestra la bottom bar.
    object Home : Routes("home")


    // ----------------------------
    // RUTA: Welcome
    // ----------------------------
    // 'object Welcome' representa la pantalla de bienvenida (después de poner el nombre).
    // La ruta se define como "welcome/{user}".
    //
    // OJO: "{user}" indica que la ruta tiene un PARÁMETRO llamado 'user'
    // que se sustituirá por el nombre real, por ejemplo:
    //   "welcome/Ana"
    //   "welcome/Pedro"
    object Welcome : Routes("welcome/{user}") {

        // Función de ayuda para crear la ruta completa con el nombre:
        //
        // Si pasamos user = "Ana"
        // devuelve "welcome/Ana"
        //
        // Así evitamos concatenar Strings a mano en el resto del código
        // y centralizamos el formato aquí.
        fun createRoute(user: String) = "welcome/$user"
    }


    // ----------------------------
    // RUTA: Perfil
    // ----------------------------
    // Pantalla de perfil, sin parámetros.
    // Su ruta es simplemente "perfil".
    // Podemos navegar con navController.navigate(Routes.Perfil.route)
    object Perfil : Routes("perfil")


    // ----------------------------
    // RUTA: Contactos
    // ----------------------------
    // Pantalla de contactos (lista con sticky header y LazyColumn).
    // Tampoco tiene parámetros, ruta "contactos".
    object Contactos : Routes("contactos")
}

