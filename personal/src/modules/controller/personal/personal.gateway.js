//este archivo simplemente va a tener todos los metodos que ejecuten las sentencias sql va a ser como nuestro dao
//para nombrar archivos con dos nombres el punto es una convencion para saber como se encunetra ese archivo
//cds-utez.controller.js
//importamos
const { query } = require('../../../utils/mysql');

//despues hacemos nuestra consulta
const findAll = async () => {
    const sql = `SELECT pe.*, po.description FROM personal pe JOIN position po  ON po.id = pe.position_id`;
    return await query(sql, []);
};

const findById = async (id) => {
    if (Number.isNaN(id)) throw Error('Wrong type')
    if (!id) throw Error('Missing fields');//si el id es 0 devuelve un falso
    const sql = `SELECT pe.*, po.description FROM personal pe JOIN position po  ON po.id = pe.position_id
    WHERE pe.id = ?;`;
    return await query(sql, [id]);
};

const save = async (person) => {
    if (!person.name || !person.surname || !person.birthday
        || !person.salary || !person.position.id) throw Error("Missing field");
    //este if hace los valores obligatorios
    //que no sea nulo que no sea indefinido que no sea vacio que no 
    const sql = `INSERT INTO personal (name,surname,lastname,
        birthday,salary,position_id) VALUES (?,?,?,?,?,?);`;

    //destructuramos
    const { insertedId } = await query(sql, [person.name,
    person.surname, person.lastname || null, person.birthday,
    person.salary, person.position.id]);
    return {...person, id:insertedId};//los tres puntos sirven para de alguna manera iterar
    //los tres puntos toman esos atributos y los pasan al objeto nuevo y le pasamos uno nuevo que es el id 
    //con el valor con el que se registro en la base de datos

};
module.exports = {
    findAll,
    findById,
    save,
};