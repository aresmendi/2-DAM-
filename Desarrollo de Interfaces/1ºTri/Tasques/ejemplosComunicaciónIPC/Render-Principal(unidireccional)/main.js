const { app, BrowserWindow, ipcMain } = require("electron");
const path = require("node:path");

function handleSetTitle(event,title){
  const webContents = event.sender;
  const win = BrowserWindow.fromWebContents(webContents);
  win.setTitle(title); //Cambia el título
}

const createWindow = () => {
  const win = new BrowserWindow({
    webPreferences: {
      preload: path.join(__dirname, "preload.js"),
    },
  });
  win.loadFile("index.html");
};

app.whenReady().then(() => {
  ipcMain.on('set-title',handleSetTitle); //Escucha el canal 'set-title'
  createWindow();
});

// Cerrar app cuando todas las ventanas se cierren (excepto en macOS)
app.on("window-all-closed", () => {
  if (process.platform !== "darwin") {
    app.quit();
  }
});
