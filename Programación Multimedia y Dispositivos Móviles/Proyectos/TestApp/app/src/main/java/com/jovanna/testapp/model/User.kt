package com.jovanna.testapp.model

data class User(
    val id: String,
    val username: String,
    val fullName: String,
    val email: String,
    val centerName: String,
    val token: String? = null

    /*
    PARA SOLUCIONAR EL DRAMA DE LA CONTRASEÑA
    El **token** es la pieza clave para la seguridad y la comunicación entre tu App Android y el Backend (Spring Boot).

Piénsalo como **una pulsera de un festival** o la **tarjeta llave de un hotel**.

### ¿Cómo funciona?

1.  **El Login:** Cuando Jovanna pone su usuario y contraseña en la App y le da a "Ingresar", la App envía esos datos al Backend.
2.  **La Respuesta:** Si la contraseña es correcta, el Backend (Spring Boot) **NO** guarda una "sesión" abierta en su memoria (porque las APIs modernas son "stateless" o sin estado). En su lugar, genera un **Token** (una cadena larga de letras y números, generalmente un **JWT** - JSON Web Token) y se lo devuelve a tu Android.
3.  **El Acceso:** A partir de ese momento, tu Android guarda ese token. Cada vez que tu App quiera pedir algo (ej: "dame mis exámenes"), enviará el Token en la cabecera de la petición.
4.  **Validación:** El servidor lee el token, ve que es válido y que pertenece a Jovanna, y te devuelve los datos.

### ¿Por qué se usa esto y no la contraseña?

1.  **Seguridad:** Tu App **NUNCA** debe guardar la contraseña del usuario en el móvil. Guardas el token. Si alguien roba el token, este caduca al cabo de un tiempo (ej: 24 horas). Si guardaras la contraseña, sería un riesgo gravísimo.
2.  **Eficiencia:** No tienes que enviar usuario/contraseña en cada petición, lo cual sería lento e inseguro.

### ¿Por qué está definido como `String? = null` (con interrogación)?

Está definido como *nullable* (puede ser nulo) por dos razones:

1.  **Antes del Login:** Cuando creas el objeto usuario para intentar loguearte, aún no tienes el token.
2.  **Otros Usuarios:** Imagina que en el futuro haces un ranking de "Mejores Estudiantes". Tu App recibirá una lista de objetos `User` (Pedro, María, Luis...). Tú necesitas saber sus nombres (`username`) y sus puntos, pero el Backend **nunca** te enviará los tokens de *otros* usuarios, solo el tuyo propio. Por tanto, para esos usuarios, el campo `token` vendrá vacío (`null`).

**Resumen:** Es la "llave digital" que Spring Boot le dará a tu App para que no tenga que estar introduciendo la contraseña a cada rato.*/
)