const { app, BrowserWindow, ipcMain, dialog } = require("electron");
const path = require("node:path");

async function handleFileOpen() {
  const {canceled,filePaths} = await dialog.showOpenDialog();
  if(!canceled){
    return filePaths[0]; //Devolverá la ruta del archivo
  }
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
  ipcMain.handle('dialog:openFile', handleFileOpen); //Usamos .handle
  createWindow();
})
// Cerrar app cuando todas las ventanas se cierren (excepto en macOS)
app.on("window-all-closed", () => {
  if (process.platform !== "darwin") {
    app.quit();
  }
});
