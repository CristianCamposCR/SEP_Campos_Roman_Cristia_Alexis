package client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class RPCClient {

    public static void main(String[] args) throws MalformedURLException, XmlRpcException {
        Scanner escaner = new Scanner(System.in);
        escaner.useDelimiter("\n");
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1200"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        String nombre, apellido1, apellido2, estado, sexo, fechaNac,dia, mes, anio, curp;
        int opc;
        Object[] data =null;
        Object[] data2 =null;
        String response;

        do {
            System.out.println("********************************************************************Generador de CURP***********************************************");
            System.out.println("MENU");
            System.out.println("1.- Generar CURP");
            System.out.println("2.- Consultar CURP");
            System.out.println("3.-Salir");
            opc = escaner.nextInt();

            switch (opc){
                case 1:
                    System.out.println("Generar CURP");
                    System.out.println("Ingresa tu nombre o nombres");
                    nombre = escaner.next().toUpperCase();
                    System.out.println("Ingresa tu apellido paterno");
                    apellido1 = escaner.next().toUpperCase();
                    System.out.println("Ingresa tu apellido materno");
                    apellido2 = escaner.next().toUpperCase();
                    System.out.println("Ingresa tu sexo");
                    sexo = escaner.next().toUpperCase();
                    System.out.println("Escribe el codigo de tu estado de acuerdo a la siguiente lista");
                    System.out.print("AGUASCALIENTES AS || BAJA CALIFORNIA BC || BAJA CALIFORNIA SUR BS \n" +
                            "CAMPECHE CC || COAHUILA CL || COLIMA CM \n" +
                            "CHIAPAS CS  || CHIHUAHUA CH || DISTRITO FEDERAL DF \n" +
                            "DURANGO DG || GUANAJUATO GT || GUERRERO GR \n" +
                            "HIDALGO HG || JALISCO JC || MÉXICO MC \n" +
                            "MORELOS MS || NAYARIT NT || NUEVO LEÓN NL \n" +
                            "OAXACA OC || PUEBLA PL || QUERÉTARO QT \n" +
                            "QUINTANA ROO QR || SAN LUIS POTOSÍ SP || SINALOA SL \n" +
                            "SONORA SR || TLAXCALA TL || VERACRUZ VZ \n" +
                            "YUCATÁN YN || ZACATECAS ZS || NACIDO EN EL EXTRANJERO NE \n");
                    estado = escaner.next();
                    System.out.println("Ingresa tu fecha de nacimiento con digitos,  iniciando por: ");
                    System.out.println("Dia:");
                    dia = escaner.next();
                    System.out.println("Mes:");
                    mes = escaner.next();
                    System.out.println("Año");
                    anio = escaner.next();
                    fechaNac = dia + "/" + mes + "/" + anio;
                    System.out.println(fechaNac);
                    //carc030122hmsmmra2
                    data = new Object[]{nombre,apellido1,apellido2,fechaNac,sexo,estado};
                    response = (String) client.execute("Methods.generarCurp", data);
                    System.out.println("Tu CURP es: -> " + response);
                    break;
                case 2:
                    System.out.println("Ingresa la CURP de la persona que quieres consultar");
                    curp = escaner.next().toUpperCase();
                    data2 = new Object[]{curp};
                    String datos = (String) client.execute("Methods.consulta",data2);
                    System.out.println(datos);

                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    break;

            }
        }while (opc<3);

    }
}




