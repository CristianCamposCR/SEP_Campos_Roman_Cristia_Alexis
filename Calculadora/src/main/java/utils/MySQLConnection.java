package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MySQLConnection {
    public Connection getConnection() {
        final String DBNAME = "calculadora",
                USERNAME = "root",
                PASSWORD = "Abejercito22?",
                TIMEZONE = "America/Mexico_City",
                USESSL = "false",
                PUBLICKEY = "true";
        String dataSource = String.format("jdbc:mysql://localhost:3306/%s?user=%s" +
                        "&password=%s&serverTimezone=%s&useSSL=%s&allowPublicKeyRetrieval=%s",
                DBNAME, USERNAME, PASSWORD, TIMEZONE, USESSL, PUBLICKEY);
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection(dataSource);
        } catch (SQLException e) {
            System.out.println(this.getClass().getCanonicalName() +
                    "->" + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {

        Connection conn = new MySQLConnection().getConnection();
        //aqui se manda a ejecutar la cenexion y nuetro metodo "getConnection()"

        if(conn != null) {
            try {
                System.out.println("Conexion realizada :)"); // se avisa que la conexion fue exitosa
                conn.close();
                //aqui se cierra la conexion eso es importanta para evitar ataques y cosas similares
            }catch (SQLException e){
                System.out.println(e);
            }
        }else{
            System.out.println("Conexion fallida :(");
        }
    }
    //aqui finaliza el metodo para probar la conexion
}
