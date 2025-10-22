const {app, BrowserWindow, ipcMain} = require('electron');
const path = require("node:path");

let loginWindow;

function handleGoRegister(){
    const win2 = new BrowserWindow({
        webPreferences: { preload: path.join(__dirname, 'register', 'preload.js'),   
         }
    })
    win2.loadFile('./register/index.html');
}

async function setUserName(event,user){
    loginWindow.webContents.send('set-user-name', user);
}

const createWindow = () => {
    const win = new BrowserWindow({
        webPreferences: {
            preload: path.join(__dirname, 'login', 'preload.js'),
        },
    });
    loginWindow = win;
    win.loadFile('./login/index.html');
};

app.whenReady().then(()=> {
    ipcMain.on('go-register', handleGoRegister);
    ipcMain.handle('set-user-name', setUserName);
    createWindow();
});

app.on('window-all-closed',()=>{
    if(process.platform !== 'darwin') app.quit();
});

try{
    require('electron-reloader')(module, {
        debug: true,
        watchRenderer:true,
    });
} catch (_) {}