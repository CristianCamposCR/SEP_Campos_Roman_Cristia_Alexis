package server;

import java.util.Arrays;
//Campos Roman Cristian Alexis
public class Methods {
    //ejercicio 1
    public Object calcularImc(String nombre, double altura, double peso){
        double imc = peso / (altura * altura);
        return "Hola "+ nombre + " tu IMC es "+imc;
    }

    //ejercicio 2
    public String producto(double producto, double suma, double promedio){
        return "Hola, el producto es: "+ producto + " , la suma es:  " + suma + " y el promedio es: " + promedio;
    }

    //ejercicio 3
    public String suma(double suma){
        return "El resultado de la suma es: " + suma;
    }

    //ejercicio 4
    public String arreglo(int pos1,int pos2, int pos3, int pos4, int pos5) {
        int temp, i, j;
        int[] array = new int[5];
        array[0] = pos1;
        array[1] = pos2;
        array[2] = pos3;
        array[3] = pos4;
        array[4] = pos5;
        Arrays.sort(array);
        /*
        for (i = 0; i < array.length - 1; i++) {//aqui ordeno
            for (j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
         */
        String aaa = "";
        for (i = 0; i < array.length; i++) {
            aaa += "El numero del índice " + i + " es " + array[i] + "\n";


        }
        return aaa;
    }
}
