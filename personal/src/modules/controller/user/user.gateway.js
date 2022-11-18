const {query} = require('../../../utils/mysql');
const {hashPassword} = require('../../../utils/functions')

const save = async (user)=>{//recibo un obj user
    if (!user.email || !user.password || !user.role || !user.personal?.id) { // a esto s le conoce como navegacion segura y si llega algo indefinido no se lee y no salta ninguna excepcion
        throw Error('Missing fields');
    }
    const hashedPassword = await hashPassword(user.password);
    const sql = `INSERT INTO users (email, password, role, status, personla_id) VALUES (?,?,?,1,?);`;
    //destructuramos la query
    const {insertId} = await query(sql, [
        user.email,
        hashedPassword,//guardamos la contra encriptada
        user.role,
        user.personal.id,
    ]);
    delete user.password; //elimina la propiedad del obj user
    return {...user, id: insertId};

};


module.exports = {
    save,
};