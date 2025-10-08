/**
 * Ejercicio 1.
Supongamos que deseamos crear un robot
a) Este robot tiene un control remoto que tiene la acción adelante. Crea una función
adelante en la que se muestre por consola: ‘El robot camina hacia el frente’. Añade
un listener a la función y crea un evento con emit para utilizarla.
b) Amplia el ejercicio anterior. Añade la acción saludar, esta función debe detener
el robot y saludar (mostrar mensajes por consola)
 */

const events = require("events");
let EmisorEventos = events.EventEmitter;
let ee = new EmisorEventos();

class Robot extends events.EventEmitter {
  //Cada objeto del tipo Person podrá emitir eventos y escucharlos
  constructor() {
    super(); //EventEmitter no requiere parámetros en su constructor
  }
  adelante() {
    console.log("El robot camina hacia el frente");
    this.emit("forward");
  }
  saludar() {
    console.log("El robot se ha detenido");
    console.log("El robot saluda");
    this.emit("hi");
  }
}

let rob = new Robot();

//Listeners
rob.on("forward", () => {}); //Dejamos una función vacía para conservar el listener
rob.on("hi", () => {});

rob.adelante();
rob.saludar();
