var time = 0
//Iniciamos el intervalo y guardamos su ID
var intervalId = setInterval(
    () => {
        time += 1;
        console.log("Han pasado " + time + " segundos");
        //Detenemos el intervalo después de 3 segundos
        if (time >= 3) {
            clearInterval(intervalId);
            console.log(__dirname);
            console.log(__filename);
        }
    }, 1000); //Cada segundo

