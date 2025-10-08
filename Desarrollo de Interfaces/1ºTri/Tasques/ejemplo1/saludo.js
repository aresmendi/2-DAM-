/**Ejercicio 1: módulos en node.
Crea un fichero llamado "saludo.js".
Echa un vistazo en la API de Node al módulo "os" (https://nodejs.org/api/), y
específicamente al método userInfo. Úselo para hacer un programa que salude al
usuario que ha accedido al sistema operativo. Por ejemplo, si el usuario es "bea", debe
decir "Hola bea". Ejecuta el programa en la terminal para comprobar su correcto
funcionamiento.
AYUDA: el método userInfo devuelve un objeto con varias propiedades del usuario que
ha accedido. Para obtener el nombre de usuario, debemos acceder a la propiedad de
username. */
const os = require('os');
let nombre = os.userInfo().username;
console.log("Hola " + nombre); 