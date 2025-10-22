const { app, BrowserWindow, Menu, ipcMain} = require("electron");
const path = require("node:path");

function createWindow () {
  const win = new BrowserWindow({
    webPreferences: {
      preload: path.join(__dirname, "preload.js"),
    },
  });
  const menu = Menu.buildFromTemplate([
    {
      label:app.name,
      submenu: [
        {
          click: () => win.webContents.send('update-counter', 1),
          label: 'Increment',
        },
        {
          click:() => win.webContents.send('update-counter',-1),
          label: 'Decrement',
        },
      ],
    },
  ]);
  Menu.setApplicationMenu(menu);
  win.loadFile("index.html");
  // Open the DevTools.
  win.webContents.openDevTools();
};

app.whenReady().then(() => {
  ipcMain.on('counter-value', (_event,value) => {
    console.log(value); //Así printamos el valor en la consola NOde
  })
  createWindow();
})
// Cerrar app cuando todas las ventanas se cierren (excepto en macOS)
app.on("window-all-closed", () => {
  if (process.platform !== "darwin") {
    app.quit();
  }
});
