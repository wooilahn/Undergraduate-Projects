const express = require('express');
const app = express();

const main = require('./main.js');

app.use('/', main);

app.listen(8888, (err) => {
    console.log('SYSTEM : HTTP SERVER RUNNING ON 8888 PORT');
});