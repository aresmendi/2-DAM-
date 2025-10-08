const pers = require(__dirname+"/personas");

// Definimos el vector de personas como era originalmente
let data = [
    { name: "Nacho", phone: "966112233", age: 40 },
    { name: "Ana", phone: "911223344", age: 35 },
    { name: "Mario", phone: "611998877", age: 15 },
    { name: "Laura", phone: "633663366", age: 17 }
];

// Programa principal que utiliza el módulo para insertar o eliminar personas de prueba
pers.newPerson(data, { name: "Juan", phone: "965661564", age: 60 }).then(resultado => console.log(resultado)).catch(error => console.log(error));
pers.newPerson(data, { name: "Rodolfo", phone: "910011001", age: 20 }).then(resultado => console.log(resultado)).catch(error => console.log(error));
pers.newPerson(data, { name: "Juan", phone: "965661564", age: 60 }).then(resultado => console.log(resultado)).catch(error => console.log(error));
pers.deletePerson(data, "910011001").then(resultado => console.log(resultado)).catch(error => console.log(error));
pers.deletePerson(data, "910011001").then(resultado => console.log(resultado)).catch(error => console.log(error));

console.log("Estado final: ", data);