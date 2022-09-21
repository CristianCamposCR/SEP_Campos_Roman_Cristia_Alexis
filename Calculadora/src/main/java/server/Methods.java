package server;

import java.util.List;

public class Methods {
    DaoCalculadora daoCalculadora = new DaoCalculadora();
    public double suma(double firstNumber, double secondNumber){
        double result;
        result = firstNumber + secondNumber;
        daoCalculadora.saveOperation(firstNumber,secondNumber,result,"Suma");
        return result;

    }

    public double resta(double firstNumber, double secondNumber){
        double result;
        result = firstNumber - secondNumber;
        daoCalculadora.saveOperation(firstNumber, secondNumber, result, "Resta");
        return result;
    }
    public double multiplicacion (double firstNumber, double secondNumber){
        double result;
        result = firstNumber * secondNumber;
        daoCalculadora.saveOperation(firstNumber, secondNumber, result, "Multiplicacion");
        return result;

    }
    public double division(double firstNumber, double secondNumber){
        double result;
        result = firstNumber/secondNumber;
        daoCalculadora.saveOperation(firstNumber, secondNumber, result, "Division");
        return result;
    }
    public double exponente(double firstNumber, double secondNumber){
        double result;
        result = Math.pow(firstNumber,secondNumber);
        daoCalculadora.saveOperation(firstNumber, secondNumber, result, "Exponente");
        return result;
    }

    public double raiz(double firstNumber, double secondNumber){
        double result;
        double ex = 1/secondNumber;
        result = Math.pow(firstNumber,ex);
        daoCalculadora.saveOperation(firstNumber, secondNumber, result, "Raiz");
        return result;
    }

    public String historial (double firsNumber){
        String text="";
        List<String> lista = daoCalculadora.listaOperaciones();
        for (String res:lista) {
            text += res + "\n";
        }
        return text;
    }



}
