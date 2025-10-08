let newPerson = (data, object) => { //Le pasamos el Array 
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

let deletePerson = (data, num) => {
    return new Promise((resolve, reject) => {
        //Primero encontramos a la persona antes de eliminarla
        const personToDelete = data.find(person => person.phone === num);
        if (personToDelete) {
            // Encontramos el índice y eliminamos el elemento
            const index = data.findIndex(person => person.phone === num);
            data.splice(index, 1);
            resolve(personToDelete);
        } else {
            reject("Error: No se encontraron coincidencias");
        }
    });
}

module.exports = {
    newPerson: newPerson,
    deletePerson: deletePerson
};