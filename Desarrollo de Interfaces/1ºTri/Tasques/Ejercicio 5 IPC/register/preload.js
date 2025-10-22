const { contextBridge, ipcRenderer } = require('electron');

contextBridge.exposeInMainWorld('electronAPI', {
    setUserName: (user) => ipcRenderer.invoke('set-user-name', user),
});