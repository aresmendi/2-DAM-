const setButton = document.getElementById('btn');
const titleInput = document.getElementById('title');

//Enviamos el mensaje
setButton.addEventListener('click', () => {
    const title = titleInput.value;
    window.electronAPI.setTitle(title); //Lo envía al proceso principal
});
