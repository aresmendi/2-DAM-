const btn = document.getElementById('btn');
const filePathElement = document.getElementById('filePath');

btn.addEventListener('click', async () => {
  const filePath = await window.electronAPI.openFile(); // Espera la respuesta
  filePathElement.innerText = filePath; // Muestra la ruta
});