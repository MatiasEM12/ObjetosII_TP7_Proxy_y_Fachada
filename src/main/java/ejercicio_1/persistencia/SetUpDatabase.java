package ejercicio_1.persistencia;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SetUpDatabase {



    public static Connection obtenerConexion(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }

}