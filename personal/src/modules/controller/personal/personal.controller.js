//solo nos va a servir para redireccionar metodos
//investigar sesiones 
//importamos
const { Response, Router, response } = require('express');
const validateError = require('../../../utils/functions');
const { findAll } = require('./personal.gateway');
const { findById } = require('./personal.gateway');
const { save } = require('./personal.gateway');

const getAll = async (req, res = Response) => {
    try {
        const personal = await findAll();
        res.status(200).json(personal);
    } catch (error) {
        console.log(error);
        const message = validateError();
        res.status(400).json({ message });
    }
};

const getById = async (req, res = Response) => {
    try {
        //asi es como obtenemos el id
        const { id } = req.params;
        const person = await findById(id);
        response.status(200).json(person);
    } catch (error) {//por lo general el catch siempre es el mismo
        console.log(error);
        const message = validateError();
        res.status(400).json({ message });
    }
};

const insert = async (req, res = Response) => {
    try {
        const { name, surname, lastname, birthday, salary, position} = req.body;
        const person = await save({
            name, surname, lastname, birthday, salary, position,
        });
        res.status(200).json(person);
    } catch (error) {
        console.log(error);
        const message = validateError(error);
        res.status(400).json({ message });
    }
};

const personalRouter = Router();
//son los routers
//aqui ya estan mapeados
personalRouter.get(`/`, [],getAll);
personalRouter.get(`/:id`,[],getById);
personalRouter.post(`/`,[],insert);

module.exports = {
    personalRouter
};