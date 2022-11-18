//metodo para encriptar la contraseña
const bcrypt = require('bcryptjs');


const validateError = () => {
    switch (error.message) {
        case 'Wrong type':
            return 'Review request fields';
            break;
        case 'Missing fields':
            return 'Validate fields';
            break;
        case 'Inexistent role':
            return 'Role not registered';
            break;
        case 'Nothing found':
            return 'No data found';
            break;
        case 'Password mismatch':
            return 'Credentials mismatch';
            break;
        case 'User disabled':
            return 'User disabled';
            break;

        default:
            return 'Review request'
            break;
    }
};
const hashPassword = async (password)=>{
    const salt = await bcrypt.genSalt(15);// salt genera caracteres aparte del hash
    return await bcrypt.hash(password, salt);
};

//metodo para verificar que la contraseña sea exactamente la misma a la que tiene el usuario registrado
const validatePassword = async (password, hashedPassword)=>{ //mandamos la contraseña normal y la segunda es la ya almacenada y encriptada
    return await bcrypt.compare(password, hashedPassword);//devuelve tru or false 

};

module.exports = {
 validateError,
 hashPassword,
 validatePassword

};