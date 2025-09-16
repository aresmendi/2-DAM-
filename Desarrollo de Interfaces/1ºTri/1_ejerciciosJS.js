/*Ejercicio 1:
Declara tres variables:
-var nombreVar
-let nombreLet
-const nombreConst
Asigna valores y muéstralos por consola. Después 
intenta reasignar valores a las tres variables y 
observa qué ocurre mostrándolos por consola de 
nuevo. */

var nombreVar = "Sergio";
let nombreLet = "Andrés";
const nombreConst = "Ares";

console.log(nombreVar);
console.log(nombreLet);
console.log(nombreConst);
console.log("\n")
nombreVar = "Perico";
nombreLet = "Lucas";

// nombreConst = "Pedro"; Esta linea la comento por que 
// me da error y deja de ejecutar el resto del código

console.log(nombreVar);
console.log(nombreLet);
console.log(nombreConst);
console.log("\n")
/*Ejercicio 2: Declara una variable global con var mensaje = "Hola";. 
Dentro de un if (true), declara otra variable con let mensaje = "Adiós";
y muestra su valor. Después, imprime mensaje fuera del bloque. 
¿Son iguales los dos valores?
Ahora prueba a usar en los 2 casos let. ¿Cambia algo?
Finalmente prueba a usar en los 2 casos var. ¿Cambia algo?*/

//1ºPrueba
var mensaje = "Hola";

if(true){
    let mensaje = "Adiós";
    console.log(mensaje);
}
console.log(mensaje);
console.log("\n")

//2ºPrueba (En este caso se muestra dos veces Hola)
let mensaje1 = "Hola";

if(true){
    let mensaje1 = "Adiós";
    console.log(mensaje);
}
console.log(mensaje1);
console.log("\n")
//3ºPrueba (Aquí vuelve a cambiar, como en el primer ejemplo)
var mensaje2 = "Hola";

if(true){
    var mensaje2 = "Adiós";
    console.log(mensaje);
}
console.log(mensaje2);
console.log("\n")

/*Ejercicio 3:
Crea una variable nota y asígnale un número de 0 a 10. 
Programa para que salga por consola lo siguiente en función de la nota:
-Si la nota es mayor o igual a 9 → "Sobresaliente"
-Entre 7 y 8 → "Notable"
-Entre 5 y 6 → "Aprobado"
-Menos de 5 → "Suspenso"
Además, imprime "Felicidades" si la nota es mayor o igual a 9. */

var nota = 6.5;

if (nota >= 9) {
    console.log("Felicidades");
    console.log("Sobresaliente");
} else if (nota < 9 && nota >= 7 ){
    console.log("Notable");
} else if ( nota < 7 && nota >=5){
    console.log("Aprobado");
} else {
    console.log("Suspendido");
}
console.log("\n");

/*Ejercicio 4:
Crea un array vacío llamado letras. Inserta al principio del array 
las letras A, B y C. Luego, inserta al final las letras D y E. 
Finalmente, elimina el primer elemento y el último, e imprime el array 
final. */

var letras = new Array();
letras.unshift("A", "B", "C");
letras.push("D","E");
letras.shift();
letras.pop();
console.log(letras);
console.log("\n");

/*Ejercicio 5:
Dado el array numeros = [4, 21, 33, 12, 9, 54], obtén un nuevo array 
que contenga solo los números pares.*/

var numeros = [4, 21, 33, 12, 9, 54];
var pares = new Array();
for (const num of numeros) {
    if (num % 2 == 0){
        pares.push(num);
    }
}
console.log(pares);
console.log("\n");

/*Ejercicio 6:
a)
Añade dos elementos al final:
{name: "Pedro", telephone: "611944444", age: 25},
{name: "Julia", phone: "633232323", age: 37}
b)
Comprueba con console.log (data) que se han añadido.
 */
let data = [
{name: "Nacho", telephone: "966112233", age: 40},
{name: "Ana", telephone: "911223344", age: 35},
{name: "Mario", phone: "611998877", age: 15},
{name: "Laura", telephone: "633663366", age: 17}
];
data.push({name: "Pedro", telephone: "611944444", age: 25},{name: "Julia", phone: "633232323", age: 37});
console.log(data);
console.log("\n");

/*c)
Ordena el vector por edad, comprueba el resultado.*/
data.sort(function(n1,n2){
    return n1.age - n2.age;
});

console.log(data);
console.log("\n");

/*d)
Ordena el vector por nombre, comprueba el resultado.*/
data.sort(function(a,b){
    return a.name.localeCompare(b.name);
});
console.log(data);
console.log("\n");

/*e)
Crea y muestra un nuevo vector que contenga solo los mayores de 30 años.*/
let mayores30 = new Array();

mayores30 = data.filter(function(x){
    return x.age > 30;
});

console.log(mayores30);
console.log("\n");

/*Ejercicio 7:
Crea una diagonal con 20 símbolos:*/
console.log("Diagonal con N=20");

for (let i = 0; i < 20; i++) {
    console.log(" ".repeat(i)+ "*");
}

console.log("\n");

/*Ejercicio 8:
Crea una diagonal inversa con 20 símbolos:*/

console.log("Diagonal inversa con N=20");

for (let i = 20; i >= 0; i--) {
    console.log(" ".repeat(i)+ "*");
}

console.log("\n");

/*Ejercicio 9:
Crea una triángulo inferior con 20 símbolos:*/

console.log("Triángulo inferior con N=20");

for (let i = 20; i >= 0; i--) {
    console.log("*".repeat(i));
}

console.log("\n");

/*Ejercicio 10:
Crea una triángulo inferior inverso con 20 símbolos:*/

console.log("Triángulo inferior inverso con N=20");

for (let i = 0; i < 20; i++) {
    console.log("*".repeat(i));
}

console.log("\n");