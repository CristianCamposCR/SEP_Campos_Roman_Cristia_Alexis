package client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
//Campos Roman Cristian Alexis
public class RPCClient {
    public static void main(String[] args) throws MalformedURLException, XmlRpcException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1200"));
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        Scanner escaner = new Scanner(System.in);
        escaner.useDelimiter("\n");

        int menu;

        do {
            System.out.println("Hola por favor ingresa el programa que deseas ejecutar");
            System.out.println("1.- Ejercicio 1 (calcular imc)");
            System.out.println("2.- Ejercicio 2 (leer cuatro variables)");
            System.out.println("3.- Ejercicio 3 (sumar los numeros entre dos cantidades)");
            System.out.println("4.- Ejercicio 4 (acomodar 5 numeros enteros)");
            System.out.println("5.- Salir");
            menu = escaner.nextInt();

            switch (menu){
                case 1:
                    String nombre;
                    double peso, altura;
                    System.out.println("Escribe el nombre: ");
                    nombre = escaner.next();
                    System.out.println("Escribe cunato pesas: ");
                    peso = escaner.nextDouble();
                    System.out.println("Escribe tu altura en metros: ");
                    altura = escaner.nextDouble();

                    Object[] data0 = {nombre, altura, peso};
                    Object response0 = client.execute("Methods.calcularImc", data0);
                    System.out.println("Result -> " + response0);
                    break;
                case 2:
                    double num1, num2, num3, num4, suma, prom = 0.0, producto = 0.0;
                    System.out.println("Ingresa el valor 1");
                    num1 = escaner.nextDouble();
                    System.out.println("Ingresa el valor 2");
                    num2 = escaner.nextDouble();
                    System.out.println("Ingresa el valor 3");
                    num3 = escaner.nextDouble();
                    System.out.println("Ingresa el valor 4");
                    num4 = escaner.nextDouble();
                    suma = (num1 + num2 + num3 + num4);
                    producto = num1*num2*num3*num4;
                    prom = (suma/4);


                    Object[] data = {producto, suma, prom};
                    String response = (String) client.execute("Methods.producto", data);
                    System.out.println("Result -> " + response);
                    break;
                case 3:
                    double nume1, nume2, minimo, maximo, suma2 = 0.0;
                    System.out.println("Ingresa el primer número");
                    nume1 = escaner.nextDouble();
                    System.out.println("Ingresa el segundo número");
                    nume2 = escaner.nextDouble();
                    minimo = Math.min(nume1, nume2);
                    maximo = Math.max(nume1, nume2);
                    System.out.print("Se sumaron: ");
                    for (double i = minimo+1; i <maximo; i++) {

                        System.out.print( i + " |");
                    }
                    for (double i = minimo+1; i < maximo; i++) {
                        suma2+=i;
                    }
                    System.out.println();

                    Object[] data2 = {suma2};
                    String response2 = (String) client.execute("Methods.suma", data2);
                    System.out.println("Result -> " + response2);
                    break;
                case 4:
                    int temp;
                    int pos1, pos2, pos3, pos4, pos5;
                    System.out.println("Dame el numero de la posicion 1");
                    pos1 = escaner.nextInt();
                    System.out.println("Dame el numero de la posicion 2");
                    pos2 = escaner.nextInt();
                    System.out.println("Dame el numero de la posicion 3");
                    pos3 = escaner.nextInt();
                    System.out.println("Dame el numero de la posicion 4");
                    pos4 = escaner.nextInt();
                    System.out.println("Dame el numero de la posicion 5");
                    pos5 = escaner.nextInt();
                    Object[] data3 = {pos1, pos2, pos3, pos4, pos5};
                    String response3 = (String) client.execute("Methods.arreglo", data3);
                    System.out.println("Result -> " + response3);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }while (menu<5);





    }
}
