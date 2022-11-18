//importacion de librearias y/o componentes
const express = require("express");//son las librerias que instalamos anteriormente
const cors = require("cors");
require("dotenv").config();
//estamos englobando todas nuestras rutas en un solo archivo
const {personalRouter, userRouter} = require('../../src/modules/controller/routes.js');
const { authRouter } = require("../modules/controller/auth/auth.controller.js");


//Inicializacion de server
const app = express();
app.set("port", process.env.PORT || 3000);

//Utilizacion de middlewares
app.use(
    cors    ({origins: "*"})
);//permite recibir peticiones desde cualquier aplicacion

//Limite del tamaño de datos en peticiones
app.use(express.json({limit:"50mb"}));//se limita el peso de las peticiones

//Definicion de todas mis rutas
app.get("/", (request,response) =>{//esta diagonal es la misma de la direccion y es nuestro primer endpoint, definimos por que metodo se va a hacer
    response.send("Bienvenido a la aplicacion AppRest Personal-UTEZ");
});//este metodo va a recibir dos parametros la ruta y un metodo funciona con los endpoints
//http://localhost:3000/
app.use(`/api/personal`,personalRouter);
//estas son las uris aqui las ponemos
app.use(`/api/user`, userRouter);

app.use(`/api/auth`, authRouter);

//Exportacion de nuestro modulo y/o variables
//variable global module
module.exports = {
    app
};//es equivalente a {app:app} si solo mandamos el nombre de la variable toma esa variable como una llave
//comando para ejecutar el proyecto npm run dev
//se va a scripts dev y corre el escript llamdo dev y ese corre otra cosa lo que le dice ahi


