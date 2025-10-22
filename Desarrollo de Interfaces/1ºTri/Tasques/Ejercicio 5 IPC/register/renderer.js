const form = document.getElementById('register');
const userNameInput = document.getElementById('user');

form.addEventListener('submit', async (event) => {
    event.preventDefault();
    const userName = userNameInput.value;
    window.electronAPI.setUserName(userName);
});