package client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
//Campos Roman Cristian Alexis
public class ClientRPC {
    static Scanner escaner = new Scanner(System.in);//static solo se puede usar con statics

    //permite el acceso a clases y metodos sin necesidad de instanciarla
    //y ayuda a mantener su valor durante toda la ejecucion del programa
    //ctrl + alt + l para dar formato
    public static void main(String[] args) throws MalformedURLException, XmlRpcException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1200"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        Object[] data = new Object[2];
        Object[] defecto = new Object[1]; //para usarlo en el metodo para traer el historial
        defecto[0] = 0.0;
        String option = "", firstNumber = "", secondNumber = "";
        Double response = 100D;
        do {
            System.out.println("1.-Suma");
            System.out.println("2.-Resta");
            System.out.println("3.-Multiplicacion");
            System.out.println("4.-Division");
            System.out.println("5.-Exponente");
            System.out.println("6.-Raíz");
            System.out.println("7.-Consultar historial");
            System.out.println("8.-salir");
            System.out.println("Seleccione una opción");
            option = escaner.next();
            if (isNumber(option)) {
                switch (Integer.parseInt(option)) {
                    case 1:
                        System.out.println("SUMA");
                        do {
                            System.out.println("Ingrese el primer numero");
                            firstNumber = escaner.next();
                            if (!isDouble(firstNumber))//sin llaves ejecuta solo la linea siguiente
                                System.out.println("Ingrese un numero válido");
                        } while (!isDouble(firstNumber));//se valida que el numero sea un double

                        //se pide el segundo numero
                        do {
                            System.out.println("Ingrese el segundo numero");
                            secondNumber = escaner.next();
                            if (!isDouble(secondNumber))//sin llaves ejecuta solo la linea siguiente
                                System.out.println("Ingrese un numero válido");
                        } while (!isDouble(secondNumber));//se valida que el numero sea un double


                        //ejecucion del metodo en el servidor

                        data = new Object[]{Double.parseDouble(firstNumber), Double.parseDouble(secondNumber)};//aqui creo si jala pues se manda
                        response = (Double) client.execute("Methods.suma", data);
                        System.out.println("Result -> " + response);
                        break;
                    case 2:
                        System.out.println("RESTA");
                        do {
                            System.out.println("Ingrese el primer numero");
                            firstNumber = escaner.next();
                            if (!isDouble(firstNumber))//sin llaves ejecuta solo la linea siguiente
                                System.out.println("Ingrese un numero válido");
                        } while (!isDouble(firstNumber));//se valida que el numero sea un double

                        do {
                            System.out.println("Ingrese el segundo numero");
                            secondNumber = escaner.next();
                            if (!isDouble(secondNumber))//sin llaves ejecuta solo la linea siguiente
                                System.out.println("Ingrese un numero válido");
                        } while (!isDouble(secondNumber));//se valida que el numero sea un double

                        //ejecucion del metodo en el servidor
                        data = new Object[]{Double.parseDouble(firstNumber), Double.parseDouble(secondNumber)};
                        response = (Double) client.execute("Methods.resta", data);
                        System.out.println("Result -> " + response);

                        break;
                    case 3:
                        System.out.println("MULTIPLICACION");
                        do {
                            System.out.println("Ingrese el primer numero");
                            firstNumber = escaner.next();
                            if (!isDouble(firstNumber))//sin llaves ejecuta solo la linea siguiente
                                System.out.println("Ingrese un numero válido");
                        } while (!isDouble(firstNumber));//se valida que el numero sea un double

                        do {
                            System.out.println("Ingrese el segundo numero");
                            secondNumber = escaner.next();
                            if (!isDouble(secondNumber))//sin llaves ejecuta solo la linea siguiente
                                System.out.println("Ingrese un numero válido");
                        } while (!isDouble(secondNumber));//se valida que el numero sea un double

                        //ejecucion del metodo en el servidor
                        data = new Object[]{Double.parseDouble(firstNumber), Double.parseDouble(secondNumber)};
                        response = (Double) client.execute("Methods.multiplicacion", data);
                        System.out.println("Result -> " + response);
                        break;
                    case 4:
                        System.out.println("DIVISION");
                        do {
                            System.out.println("Ingrese el primer numero");
                            firstNumber = escaner.next();
                            if (!isDouble(firstNumber))//sin llaves ejecuta solo la linea siguiente
                                System.out.println("Ingrese un numero válido");
                        } while (!isDouble(firstNumber));//se valida que el numero sea un double

                        do {
                            System.out.println("Ingrese el segundo numero");
                            secondNumber = escaner.next();
                            if (!isDouble(secondNumber) || Double.parseDouble(secondNumber)==0)//sin llaves ejecuta solo la linea siguiente
                                System.out.println("Ingrese un numero válido");
                        } while (!isDouble(secondNumber) || Double.parseDouble(secondNumber)==0);//se valida que el numero sea un double

                        //ejecucion del metodo en el servidor
                        data = new Object[]{Double.parseDouble(firstNumber), Double.parseDouble(secondNumber)};
                        response = (Double) client.execute("Methods.division", data);
                        System.out.println("Result -> " + response);
                        break;
                    case 5:
                        System.out.println("EXPONENTE");
                        do {
                            System.out.println("Ingrese el primer numero");
                            firstNumber = escaner.next();
                            if (!isDouble(firstNumber))//sin llaves ejecuta solo la linea siguiente
                                System.out.println("Ingrese un numero válido");
                        } while (!isDouble(firstNumber));//se valida que el numero sea un double

                        if (Double.parseDouble(firstNumber)==0){
                            do {
                                System.out.println("Ingrese el segundo numero");
                                secondNumber = escaner.next();
                                if (!isDouble(secondNumber) || Double.parseDouble(secondNumber)<0)//sin llaves ejecuta solo la linea siguiente
                                    System.out.println("Ingrese un numero válido");
                            } while (!isDouble(secondNumber) || Double.parseDouble(secondNumber)<0);//se valida que el numero sea un double

                        }else {
                            do {
                                System.out.println("Ingrese el segundo numero");
                                secondNumber = escaner.next();
                                if (!isDouble(secondNumber) )//sin llaves ejecuta solo la linea siguiente
                                    System.out.println("Ingrese un numero válido");
                            } while (!isDouble(secondNumber));//se valida que el numero sea un double

                        }

                        //ejecucion del metodo en el servidor
                        data = new Object[]{Double.parseDouble(firstNumber), Double.parseDouble(secondNumber)};
                        response = (Double) client.execute("Methods.exponente", data);
                        System.out.println("Result -> " + response);
                        break;
                    case 6:
                        System.out.println("RAIZ");
                        do {
                            System.out.println("Ingrese el primer numero (base)");
                            firstNumber = escaner.next();
                            if (!isDouble(firstNumber) || Double.parseDouble(firstNumber)<0)//sin llaves ejecuta solo la linea siguiente
                                System.out.println("Ingrese un numero válido");
                        } while (!isDouble(firstNumber) || Double.parseDouble(firstNumber)<0);//se valida que el numero sea un double

                        if (Double.parseDouble(firstNumber)==0){
                            do {
                                System.out.println("Ingrese el segundo numero (raiz)");
                                secondNumber = escaner.next();
                                if (!isDouble(secondNumber) || Double.parseDouble(secondNumber)<0)//sin llaves ejecuta solo la linea siguiente
                                    System.out.println("Ingrese un numero válido");
                            } while (!isDouble(secondNumber) || Double.parseDouble(secondNumber)<0);//se valida que el numero sea un double

                        }else {
                            do {
                                System.out.println("Ingrese el segundo numero");
                                secondNumber = escaner.next();
                                if (!isDouble(secondNumber) )//sin llaves ejecuta solo la linea siguiente
                                    System.out.println("Ingrese un numero válido");
                            } while (!isDouble(secondNumber));//se valida que el numero sea un double

                        }

                        //ejecucion del metodo en el servidor
                        data = new Object[]{Double.parseDouble(firstNumber), Double.parseDouble(secondNumber)};
                        response = (Double) client.execute("Methods.raiz", data);
                        System.out.println("Result -> " + response);
                        break;
                    case 7:
                        String h = (String) client.execute("Methods.historial", defecto);
                        System.out.println(h);
                        break;
                    case 8:
                        System.out.println("Saliendo ...");
                        break;
                    default:
                        System.out.println("Por defecto no existe esta opción");
                        break;

                }
            } else {
                System.out.println("La opcion es incorrecta. Intente nuevamente");
            }
        } while (!option.equals("8"));
    }

    public static boolean isNumber(String number) {//metodo para hacer validaciones de numeros
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String number) {//metodo para hacer validaciones de doubles
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {//nos permite controlar el flujo
            return false;
        }
    }

}
