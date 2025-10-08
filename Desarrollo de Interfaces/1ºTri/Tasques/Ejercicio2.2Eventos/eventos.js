const events = require("events");
class Sensor extends events.EventEmitter {
  constructor(nombre, umbral) {
    super();
    this.nombre = nombre;
    this.umbral = umbral;

    this.intervalo = setInterval(() => {
      this.valor = Math.floor(Math.random() * 100) + 1;
      console.log(this.nombre + ": " + this.valor);
      this.comprobar();
    }, 1000);
  }
  comprobar() {
    if (this.valor > this.umbral) {
      console.log("Alerta:" + this.nombre + " superó el umbral");
      this.emit("alerta", { sensor: this.nombre, valor: this.valor });
    }
  }
}

let tempi = new Sensor("Temperatura", 80);
let humi = new Sensor("Humedad", 90);
let pres = new Sensor("Presión", 85);

let sensores = [tempi, humi, pres];

sensores.forEach((sensor) => {
  sensor.on("alerta", ({ sensor, valor }) => {
    console.log("El programa se ha detenido por " + sensor + " a nivel " + valor);
    process.exit(0); 
  });
});
