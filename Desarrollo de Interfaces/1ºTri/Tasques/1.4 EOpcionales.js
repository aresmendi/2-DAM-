/*1. Funciones, callbacks y arrow functions
1. Escribe una función tradicional que calcule la suma de dos números. Luego conviértela
en función anónima y en función flecha.*/

//Tradicional
function add (num1, num2){
    return num1 + num2;
}
console.log (add (3, 2));
console.log("\n");

//Anónima 

let addAnonymus = function (num1, num2){
    return num1 + num2;
}
console.log(addAnonymus(3,2));
console.log("\n");

//Flecha

let arrowAdd = (num1, num2) => num1 + num2; // Este es el caso de que la función solo devuelva un valor
console.log(arrowAdd(2,2));
console.log("\n");

let arrowAdd2 = (num1, num2) => { //Esto es lo que haríamos si queremos que la función devuelva más de un valor
    return num1 + num2;
};
console.log(arrowAdd2(1,1));
console.log("\n");

/*2. Crea un array de objetos con productos (nombre, precio, stock) y usa filter con una
función flecha para obtener solo los que tengan stock mayor que 0.*/

let objetos = [
    {nombre:"Pepinos", precio: 1.5, stock:20},
    {nombre:"Butrifarras", precio: 4, stock:10},
    {nombre:"Jamón", precio: 34.5, stock:0},
    {nombre:"Periquitos", precio:20.3, stock:23}
];

let hayStock = objetos.filter(objeto => objeto.stock > 0);
console.log(hayStock);
console.log("\n");

/*3. Implementa un callback con setTimeout que imprima “¡Tiempo cumplido!” después de
3 segundos, pero antes debe imprimirse “Esperando...”.*/

setTimeout(function (){
    console.log("¡Tiempo cumplido!");
}, 50);
console.log("Esperando...");
console.log("\n");


/*2. Promesas
Ejemplo*/
let MenorDeDiezPromise = listing => {
    return new Promise ((resolve, reject) => {
        let result = listing.filter (objeto => objeto.precio >10);
        if (result.length>0)
            resolve (result);
        else
            reject("No hay resultados");
    });
};
//Consumir la Promesa para hacer que funcione!!!
MenorDeDiezPromise(objetos).
then(resultado => console.log(resultado)).
catch(error => console.log(error));
console.log("\n");


/*4. Crea una promesa que simule comprobar si un número es par.
- Si lo es → resolve("El número es par").
- Si no lo es → reject("El número es impar").
Consume la promesa con then y catch.*/

let ParoImparPromise = new Promise ((resolve, reject) => {
    let num = 2;
    //Si todo va bien, llamamos a resolve
    if (num % 2 == 0)
        resolve("El número es par");
    //Si algo falla, llamamos a reject
    else 
        reject("El número es impar");
});

ParoImparPromise.
then(resultado => console.log(resultado)).
catch(error => console.log(error));


/*5. Escribe una función que devuelva una promesa que simule consultar un servidor y
devuelva un array de usuarios después de 2 segundos (setTimeout).
- Si la lista está vacía, devuelve un error.
- Consume la promesa mostrando el resultado en consola.*/

function consulta(){
    return new Promise((resolve, reject) => {
        //Simulamos consulta al servidor con setTimeOut
        setTimeout(() => {
            //Simulamos el array de usuarios
            let usuarios = [
              
            ];
            if (usuarios.length > 0)
                resolve(usuarios);
            else
                reject("La lista está vacía");
        }, 2000);
    });
}

//Llamamos a la función
consulta().
then(resultado => console.log(resultado)).catch(error => console.log(error));

/*3. Clases y objetos*/
/*Ejemplo*/
class Person{
    constructor(firstname,lastname,birthday) {
        this.firstname=firstname;
        this.lastname=lastname;
        this.birthday= new Date(birthday);
    }
    getAge(){
        const today = new Date();
        let age = today.getFullYear() - this.birthday.getFullYear();
        return age;
    }
}
const p = new Person("Paul", "Almunia", "1966-08-07");
console.log(p.firstname + " " + p.lastname + " tiene " + p.getAge() + " años");

/*6. Define una clase Coche con propiedades marca, modelo, año y un método
getAntiguedad() que calcule cuántos años tiene el coche.
- Crea 2 instancias y muestra la antigüedad en consola.*/
class Coche{
    constructor(marca,modelo,year) {
        this.marca=marca;
        this.modelo=modelo;
        this.year= new Date(year);
    }
    getAntiguedad(){
        const today = new Date();
        let antiguedad = today.getFullYear() - this.year.getFullYear();
        return "La antiguedad del " + this.marca  +" es " + antiguedad + " años";
    }
}
const xaxo = new Coche("Citroen", "Saxo", "1997");
const corsa = new Coche("Opel", "Corsa", "2017");
console.log(xaxo.getAntiguedad());
console.log(corsa.getAntiguedad());


/*7. Crea una clase CuentaBancaria con métodos depositar y retirar.
-Simula operaciones y consulta su saldo actual. */
class CuentaBancaria{
    constructor(usuario,saldo) {
        this.usuario=usuario;
        this.saldo=saldo;
    }
    depositar(dinero){
        this.saldo+=dinero; //Modificamos el saldo
        return this.saldo; //Devolvemos el saldo
    }
    retirar(dinero){
        this.saldo-=dinero;
        return this.saldo;
    }
    consultaSaldo(){
        return "El saldo actual es " + this.saldo + "€";
    }
}
let cuenta = new CuentaBancaria("Ares", 1000);
cuenta.depositar(400);
console.log(cuenta.consultaSaldo());
cuenta.retirar(1000);
console.log(cuenta.consultaSaldo());

/* 4. Herencia
8. Crea una clase Animal con propiedades nombre y edad, y un método emitirSonido() que
imprima “sonido genérico”.
-Define una clase Perro que herede de Animal que sobreescriba el método para
imprimir “¡Guau!”.
-Crea un objeto Perro y prueba los métodos. */
class Animal{
    constructor(nombre, edad){
        this.nombre = nombre;
        this.edad = edad;
    }
    emitirSonido(){
        return "Sonido genérico";
    }
}
class Perro extends Animal{
    constructor(nombre, edad){
        super(nombre, edad);
    }
    emitirSonido(){
        return "Guau!";
    }
}
let pajaro = new Animal("Loco", 20);
let arrel = new Perro("Arrel", 6);

console.log(pajaro.emitirSonido());
console.log(arrel.emitirSonido());