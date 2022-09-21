package server;

import utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoCalculadora {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;

    private final String CRATE_OPERATION = "INSERT INTO `calculadora`.`operations` (`type`, `first_number`, `second_number`, `result`) VALUES (?,?,?,?);";
    private final String LIST_OPERATIONS = "SELECT `type`,first_number,second_number,result from calculadora.operations order by create_ad desc;";

    public boolean saveOperation(double firstNumber, double secondNumber, double result, String type) {
        try {
            conn = new MySQLConnection().getConnection();
            String query = CRATE_OPERATION;
            pstm = conn.prepareStatement(query);
            //rs = pstm.executeQuery();
            pstm.setString(1, type);
            pstm.setDouble(2, firstNumber);
            pstm.setDouble(3, secondNumber);
            pstm.setDouble(4, result);
            System.out.println("se hizo");

            return pstm.executeUpdate() ==1;


        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public List<String> listaOperaciones() {
        List<String> lista = new LinkedList<>();
        String operaciones = "";
        try {
            conn = new MySQLConnection().getConnection();
            String query = LIST_OPERATIONS;
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                operaciones ="Tipo de operacion: "+ rs.getString("type") + ", Primer numero: " + rs.getDouble("first_number") + ", Segundo numero:  " + rs.getDouble("second_number") + " El resultado es: " + rs.getDouble("result");
                lista.add(operaciones);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        for (String miLista : lista) {
            System.out.println(miLista);
        }
        return lista;
    }

    public static void main(String[] args) {
        new DaoCalculadora().listaOperaciones();
    }



}

