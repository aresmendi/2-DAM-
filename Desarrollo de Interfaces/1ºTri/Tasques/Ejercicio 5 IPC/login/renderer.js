const btn = document.getElementById('btn');

btn.addEventListener('click', () => {
    window.electronAPI.goRegister();
});

window.electronAPI.setUserName((user) => {
    document.getElementById('usuario').value = user;
    alert("Usuario creado correctamente");
});
