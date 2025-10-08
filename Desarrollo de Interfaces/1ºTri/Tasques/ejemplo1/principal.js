//usamos require para usar el modulo
const utilities = require(__dirname +'/utilidades'); //Así aseguras que funcione desde donde sea, más o menos
const _ = require('lodash');
const events = require ('events');
console.log(_.difference([1,2,3],[1,2]));
//Usamos las funciones definidas en el otro módulo
console.log(utilities.Add(3,2)+ " , " + utilities.Substract(5,3));

let EmisorEventos = events.EventEmitter;
let ee = new EmisorEventos();
//primero escuchamos
ee.on('data', function(date){
    console.log(date);
});
//luego emitimos
let time = 0;
var interval = setInterval( ()=>{
    if (time > 5)clearInterval(interval);
    ee.emit('data',Date().toString());
    time++;
},500 );


//Asociación de eventos a objetos

class Person extends events.EventEmitter {
    //Cada objeto del tipo Person podrá emitir eventos y escucharlos 
    constructor(name){
        super();//EventEmitter no requiere parámetros en su constructor
        this.name = name;
    }
}

let manu = new Person('Manu');
let boris = new Person('Boris');
let people = [manu,boris];
people.forEach( littleperson => {
    littleperson.on('talk', message => { //A cada uno le asignamos un escuchador
        console.log(littleperson.name + ' ha dicho ' + message);
    });
});
//Emitimos la movida desde cada uno
manu.emit('talk', 'Espero que estudies node');
boris.emit('talk', 'Lo repito mucho');

people.forEach( person => {
    person.emit('talk', 'ForEacheando el rato');
});