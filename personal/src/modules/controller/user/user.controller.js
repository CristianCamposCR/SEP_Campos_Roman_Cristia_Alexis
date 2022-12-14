//importamos Response y router
const {Response, Router} = require('express');
const {save} = require('./user.gateway');

const saveAndFlush = async (req, res = Response) =>{ // Flush es para hacer que se guarde indediatamente es como un commit
    try {
        const {email,password,role,personal} = req.body;//aqui obtenemos los datos
        const user = await save({email,password,role,personal});
        res.status(200).json(user);

    } catch (error) {
        console.log(error);
        const message = validateError(error);
        res.status(400).json({message});
    }
};

const userRouter = Router();
userRouter.post(`/`,[],saveAndFlush);//mapeamos saveAnflush

module.exports = {
    userRouter
};
