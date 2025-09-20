//Ejercicio 2
let data = [
    { name: "Nacho", phone: "966112233", age: 40 },
    { name: "Ana", phone: "911223344", age: 35 },
    { name: "Mario", phone: "611998877", age: 15 },
    { name: "Laura", phone: "633663366", age: 17 }
];

let newPerson = (object) => {
    return new Promise((resolve, reject) => {
        //Verificamos si el telefono existe
        if (data.some(person => person.phone === object.phone)) {
            reject("Error: El teléfono ya existe");
        } else {
            data.push(object);
            console.log("El contacto ha sido añadido");
            resolve(object);
        }
    });
}

let deletePerson = (num) => {
    return new Promise((resolve, reject) => {
        //Primero encontramos a la persona antes de eliminarla
        const personToDelete = data.find(person => person.phone === num);
        if (personToDelete) {
            data = data.filter(person => person.phone !== num);
            resolve(personToDelete);
        } else {
            reject("Error: No se encontraron coincidencias");
        }
    });

}

newPerson({ name: "Juan", phone: "965661564", age: 60 }).then(resultado => console.log(resultado)).catch(error => console.log(error));
newPerson({ name: "Rodolfo", phone: "910011001", age: 20 }).then(resultado => console.log(resultado)).catch(error => console.log(error));
newPerson({ name: "Juan", phone: "965661564", age: 60 }).then(resultado => console.log(resultado)).catch(error => console.log(error));
deletePerson("910011001").then(resultado => console.log(resultado)).catch(error => console.log(error));
deletePerson("910011001").then(resultado => console.log(resultado)).catch(error => console.log(error));
console.log("Estado final: ",data);
