package server;

import utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoCurp {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;//Cacha resgistros de la base de datos

    private final String SAVE_PERSON = "INSERT INTO `curp`.`persona` (`nombre`, `apellido_paterno`, `apellido_materno`, ` sexo`, `estado`, `fecha_nac`, `curp`) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private final String GET_PERSON = "SELECT `nombre`, `apellido_paterno`, `apellido_materno`, ` sexo`, `estado`, `fecha_nac` FROM `curp`.`persona` where curp = ?;";

    public boolean savePerson(String nombre, String apellido1, String apellido2, String fechaNac, String sexo, String estado, String curp){
        try {
            conn = new MySQLConnection().getConnection();
            String query = SAVE_PERSON;
            pstm = conn.prepareStatement(query);
            pstm.setString(1,nombre);
            pstm.setString(2,apellido1);
            pstm.setString(3,apellido2);
            pstm.setString(4,sexo);
            pstm.setString(5,estado);
            pstm.setString(6,fechaNac);
            pstm.setString(7,curp);

            return pstm.executeUpdate() ==1;

        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

    public String listaPersona(String buscar) {
        String datos = "";
        try {
            conn = new MySQLConnection().getConnection();
            String query = GET_PERSON;
            pstm = conn.prepareStatement(query);
            pstm.setString(1,buscar);
            rs = pstm.executeQuery();
            if (rs.next()){
                datos ="Nombre: "+ rs.getString("nombre");
            }else {
                datos = "no entro";
            }
            datos ="Nombre: "+ rs.getString("nombre") + "\nApellido paterno: " + rs.getString("apellido_paterno")
                    + "\nApellido materno: " + rs.getString("apellido_materno")
                    + "\nSexo: " + rs.getString(" sexo")
                    + "\nEstado: " + rs.getString("estado")
                    + "\nFecha de nacimiento: " + rs.getString("fecha_nac");
        } catch (SQLException e) {
            System.out.println("no jalo "+e);
        }
        System.out.println(datos);
        return datos;
    }

    public static void main(String[] args) {
        DaoCurp daoCurp = new DaoCurp();
        daoCurp.listaPersona("CARC030122HMSMMRA0");
    }
}
