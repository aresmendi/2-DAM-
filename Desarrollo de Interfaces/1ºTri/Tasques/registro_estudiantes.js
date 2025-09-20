class Estudiante {
    constructor(nombre,edad, notas = []) {
        this.nombre = nombre;
        this.edad = edad;
        this.notas = notas;
    }
    calculaMedias(){
        return new Promise((resolve,reject) => {
        let sumNotas = 0;
        let totalNotas = this.notas.length;
        for (const nota of this.notas) { //Acordarse del this.
            sumNotas += nota;
        }
        let media = sumNotas/totalNotas;
        console.log("Nota media de "+ this.nombre + ": " + media); //Acordarse del this.
        if (media >= 5){
            resolve("Aprobado");
        } else {
            reject("Suspendido");
        }
        });
    }
}

let a = new Estudiante("Juan", 20, [3,7,5]);
let b = new Estudiante("Perico", 21, [8,9,10]);
let c = new Estudiante("Andrés", 19, [1,2,4]);

a.calculaMedias().then(result => console.log(result)).catch(error => console.log(error));
b.calculaMedias().then(result => console.log(result)).catch(error => console.log(error));
c.calculaMedias().then(result => console.log(result)).catch(error => console.log(error));