/*Ejercicio 1
Genera un número aleatorio entre 1 y 5, y muéstralo como “Retraso: X segundos”.
Luego programa con setTimeout un mensaje que diga "¡Lanzamiento!" después de
esos segundos.
*/
let rn = () => Math.floor(Math.random() * 5)+1 ;
let retraso = rn();
console.log("Retraso: " + retraso + " segundos");
setTimeout( ()=>console.log("Lanzamiento!"), retraso*1000);

/**Ejercicio 2:
Simula una “barra de carga” en consola que vaya creciendo carácter a carácter
cada medio segundo:
█
██
███
████
Cuando llegue a 10 bloques, se debe detener automáticamente y mostrar:
Carga completada */
let time = 0;
var intervalId = setInterval(
    () => {
        time += 1;
        console.log("█".repeat(time)); //repite() es la caña
        //Detenemos el intervalo cuando llegue a 10 bloques
        if (time >= 10) {
            clearInterval(intervalId);
            console.log("Carga Completada!")
        }
    }, 500); //Cada mediosegundo

