/**Ejercicio 2.
Crea un evento llamado ‘hello’ que muestre por consola ‘hola‘ cada 5 segundos. */
const events = require("events");
let EmisorEventos = events.EventEmitter;
let ee = new EmisorEventos();
//Definimo la función hello
function hello() {
  console.log("Hola");
}

//Registramos el listener para el evento 'hello'
ee.on("hello", hello);

//Emitimos el evento 'hello' cada 5 segundos
setInterval(() => ee.emit("hello"), 5000);
