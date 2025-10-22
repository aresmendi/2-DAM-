const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('electronAPI', {
    goRegister: () => ipcRenderer.send('go-register'),
    setUserName: (callback) => ipcRenderer.on('set-user-name', (event,user) => callback(user))
});
