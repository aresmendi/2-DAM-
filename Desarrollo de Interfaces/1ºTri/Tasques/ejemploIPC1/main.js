const { app, BrowserWindow } = require("electron");
const path = require("node:path");

const createWindow = () => {
  const win = new BrowserWindow({
    webPreferences: {
      preload: path.join(__dirname, "preload.js"),
    },
  });
  win.loadFile("index.html");
};

// Crear ventana cuando la app esté lista
app.whenReady().then(() => {
  createWindow();

  // En macOS, recrear ventana si se activa sin ventanas abiertas
  app.on("activate", () => {
    if (BrowserWindow.getAllWindows().length === 0) {
      createWindow();
    }
  });
});

// Cerrar app cuando todas las ventanas se cierren (excepto en macOS)
app.on("window-all-closed", () => {
  if (process.platform !== "darwin") {
    app.quit();
  }
});
