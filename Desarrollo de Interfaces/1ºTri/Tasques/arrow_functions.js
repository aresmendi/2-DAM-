//Ejercicio 1
let data = [
    { name: "Nacho", phone: "966112233", age: 40 },
    { name: "Ana", phone: "911223344", age: 35 },
    { name: "Mario", phone: "611998877", age: 15 },
    { name: "Laura", phone: "633663366", age: 17 }
];

let newPerson = (object) => {
    //Verificamos si el telefono existe
    if (data.some(person => person.phone === object.phone)) {
        console.log("El teléfono ya está registrado");
    }
    else {
        data.push(object);
        console.log("El contacto ha sido añadido");
    }
}

let deletePerson = (num) => {
    const personToDelete = data.find(person => person.phone  === num);
    data = data.filter(person => person.phone !== num);

    if (personToDelete) {
        //Primero encontramos a la persona antes de eliminarla
        data = data.filter(person => person.phone !== num);
        console.log("Contacto eliminado");
    } else {
        console.log("No se encontró el teléfono");
    }
}

newPerson({ name: "Juan", phone: "965661564", age: 60 });
newPerson({ name: "Rodolfo", phone: "910011001", age: 20 });
deletePerson("910011001");
console.log(data);
