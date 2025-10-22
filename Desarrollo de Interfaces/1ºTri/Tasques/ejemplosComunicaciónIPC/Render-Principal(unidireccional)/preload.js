const { contextBridge, ipcRenderer } = require('electron');

//Exponemos la función
contextBridge.exposeInMainWorld('electronAPI', {
    setTitle: (title) => ipcRenderer.send('set-title', title),
});