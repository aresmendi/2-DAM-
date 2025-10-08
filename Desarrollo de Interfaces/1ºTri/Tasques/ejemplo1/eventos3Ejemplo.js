/**Ejercicio 3.
Crea una clase Coche que tenga como propiedades la marca y la matrícula. Crea tres
coches distintos con diferentes marcas y matrículas.
Añade los siguientes eventos:
• info → muestra la marca y la matrícula.
• arrancar → muestra el coche con matrícula xx ha arrancado.
• pitar → muestra el coche con matrícula xx ha pitado.
Prueba a usar esos eventos con diferentes coches. */
const events = require("events");
let EmisorEventos = events.EventEmitter;
let ee = new EmisorEventos();

class Coche extends events.EventEmitter {
  //Cada objeto del tipo Coche podrá emitir eventos y escucharlos
  constructor(marca, matricula) {
    super(); //EventEmitter no requiere parámetros en su constructor
    this.marca = marca;
    this.matricula = matricula;
  }
  info() {
    console.log(this.marca, this.matricula);
    this.emit("info");
  }
  arrancar() {
    console.log("El coche con matrícula " + this.matricula + " ha arrancado");
    this.emit("arrancar");
  }
  pitar() {
    console.log("El coche con matrícula " + this.matricula + " ha pitado");
    this.emit("pi");
  }
}
let saxo = new Coche("Saxo", "V5149FU");
let opel = new Coche("Opel", "3481GPU");

saxo.info("info", () => {});
opel.info("info", () => {});
saxo.arrancar("arrancar", () => {});
opel.arrancar("arrancar", () => {});
saxo.pitar("pi", () => {});
opel.pitar("pi", () => {});
