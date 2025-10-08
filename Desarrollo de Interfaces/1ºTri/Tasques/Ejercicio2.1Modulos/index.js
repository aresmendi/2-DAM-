const moment = require("moment");
/**a)
Guardar la fecha y hora actual en una variable.*/
let now = moment();
/**b)
Definir una fecha anterior a la actual. Puede especificar el formato de fecha en una cadena de texto, seguido del patrón de fecha */
let before = moment("01/11/1996", "DD/MM/YYYY");
/**c)
Definir una fecha posterior a la actual. */
let after = moment("14/07/2026", "DD/MM/YYYY");
/*d) 
Años transcurridos desde "before" hasta ahora*/
let diffPast = moment.duration(now.diff(before));
console.log(
  "Han pasado:",
  diffPast.years(),
  "años y",
  diffPast.months(),
  "meses"
);
/**.years() → devuelve los años enteros de la duración
 * .months() → devuelve los meses restantes después de los años completos.
 */
/*e) 
Años y meses que faltan de "now" hasta "after"*/
let diffFuture = moment.duration(after.diff(now));
console.log(
  "Faltan:",
  diffFuture.years(),
  "años y",
  diffFuture.months(),
  "meses"
);
/**f)
 * Ahora muestra en la consola si la fecha antigua es, efectivamente, anterior a la actual.
 */
before.isBefore(now)
  ? console.log("Si que es más antigua")
  : console.log("No es más antigua");
/**g)
Finalmente, cree una fecha que sea exactamente dentro de un mes. */
let inAMonth = now.add(1, "month");
console.log("Dentro de un mes será: ", inAMonth.format("DD/MM/YYYY"));
