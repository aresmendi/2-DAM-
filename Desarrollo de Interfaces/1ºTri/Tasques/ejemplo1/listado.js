const route='C:\\Program Files'
const fs = require('fs');
fs.readdirSync(route).forEach(file =>{console.log(file);});