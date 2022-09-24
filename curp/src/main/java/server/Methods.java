package server;

import java.util.List;
import java.util.Random;

public class Methods {
    DaoCurp daoCurp = new DaoCurp();
    public String generarCurp(String nombre, String apellido1, String apellido2, String fechaNac, String sexo, String estado){


        //aqui se generar los dos caracteres aleatorios del final
        String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        Random random2 = new Random();



        int randomInt = random.nextInt(abecedario.length());
        int randomInt2 = random2.nextInt(abecedario.length());
        String randomChar = String.valueOf(abecedario.charAt(randomInt));
        String randomChar2 = String.valueOf(abecedario.charAt(randomInt2));

        //aqui se genera toda la curp
        String curp= String.valueOf(apellido1.charAt(0)+""+ soloVocales(apellido1).charAt(0) +""+apellido2.charAt(0)+""+nombre.charAt(0)+""+fechaNac.substring(8,10)+fechaNac.substring(3,5)+fechaNac.substring(0,2)+sexo.charAt(0)+estado+soloConsonantes(apellido1).charAt(0)+soloConsonantes(apellido2).charAt(0)+soloConsonantes(nombre).charAt(0)+randomChar+randomChar2).toUpperCase();

        //mandamos a guardar en la base de datos
        daoCurp.savePerson(nombre,apellido1,apellido2,fechaNac,sexo,estado,curp);
        //retornamos la curp ya hecha
        return curp;
    }

    //metodo de consulta
    public String consulta (String curp){
        String buscar=curp;
        String text="";
        text += daoCurp.listaPersona(buscar);
        return text;
    }


    //metodo para solo retornar las consonantes de una cadena y asi poder escoger la segunda o la que sea necesaria
    public static String soloConsonantes(String palabra){//ya sirve
        char [] palabraChar = palabra.toUpperCase().toCharArray();
        String cons="";
        for (int i=1;i<palabraChar.length;i++){
            if ((palabraChar[i]!='A' && palabraChar[i]!='E' && palabraChar[i]!='I' && palabraChar[i]!='O' && palabraChar[i]!='U')){
                cons += palabraChar[i];
            }
        }
        return cons;
    }

    //metodo para solo retornar las VOCALES de una cadena y asi poder escoger la segunda o la que sea necesaria
    public static String soloVocales(String palabra){//ya sirve
        char [] palabraChar = palabra.toUpperCase().toCharArray();
        String vocal="";
        for (int i=1;i<palabraChar.length;i++){
            if (!(palabraChar[i]=='A' && palabraChar[i]=='E' && palabraChar[i]=='I' && palabraChar[i]=='O' && palabraChar[i]=='U')){
                vocal += palabraChar[i];
            }
        }
        return vocal;
    }
}
