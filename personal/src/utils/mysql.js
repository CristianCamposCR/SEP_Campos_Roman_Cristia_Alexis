const mysql = require('mysql');

require('dotenv').config();

const client = mysql.createPool({
    connectionLimit: 5,
    host: process.env.DB_HOST,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_DATABASE,
    port: process.env.DB_PORT,
});//recibe un obj
//crea una piscina de conexiones y solo permite maximo 5 conexiones al mismo tiempo

const query = (sql, params) => {//1.-Stament 2.- valor
    //query tambien retorna voy pero la forzamos a que nos retorne algo
    return new Promise((resolve, reject) => {
        client.getConnection((err, conn) => {//esta funcion es sincrona y por eso se mete en un promise y se termina hasta que se ejecute un reject o un resolve que son los dos metodos principales de una promesa
            //y esta funcion no retorna nada es como un void
            //get connection recibe una funcion void que no retorna nada por eso se engloba en una promesa ya que sus metodos si retornan algo
            if (err) reject(err);
            conn.query(sql, params, (err, rows) => {
                if (err) reject(err);
                conn.release();
                resolve(rows);
            })
        });
    });

}//aqui recimibos la sentencia sql y los parametros que vamos a remplazar en esas sentencia


//asi se hace una exportacion de un modulo
module.exports = {
    query
};