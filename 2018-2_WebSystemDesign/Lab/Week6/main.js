const express = require('express');
const { MongoClient } = require('mongodb');
const bodyParser = require('body-parser');
const main = express.Router();

var db;
var myCollection;

MongoClient.connect("mongodb://localhost:27017",
    { useNewUrlParser:true }, (err, client) => {
        if (!err) {
            console.log("MongoDB Connected.")
        }

        db = client.db("ContactList-201420907"); //Select DB
        myCollection = db.collection('ContactList-201420907'); //Select
    });

main.use(bodyParser.urlencoded({
    extended: false
}));

main.get('/', (req, res) => {

    myCollection.find({}, async (err, result) => {
        const contactArray = await result.toArray();
        var list = "";

        for (var i = 0; i < contactArray.length; i++) {
            list = list + '<p> Name : ' + contactArray[i].name + ',  Contact : ' + contactArray[i].contact + '</p>';
        }

        var html =
            `
            <!DOCTYPE html>
            <html>
            <head>
            <style>
                label {
                    font-size : 30px;
                }
            
                input {
                    font-size : 30px;
                    size : 30px;
                }

                #post {
                    background-color : white;
                }
                
                p {
                    font-size : 20px;
                }
            </style>
            </head>
            <body>
                <form action="/contact/create" method="POST">
                    <label> Name :
                        <input type="text" name="name">
                    </label>
                    <label> Contact Number :
                        <input type="text" name="contact">
                    </label>
                        <input id="post" type="submit" value="Add Contact">
                 </form>` +
            list +
            `
            </body>
            <html>`;

        res.send(html);
    })
});

main.post('/contact/create', (req, res) => {

    myCollection.insertOne({name:req.body.name, contact:req.body.contact}, (err, result) => {
        console.log("inserted one document (id : " + req.body.name + ", contact : " + req.body.contact + ")"  );
    });

    res.redirect('/');
});

module.exports = main;