package practica2.bloqueos;

import java.sql.*;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class Bloqueo {
    
    private static Connection cn;
    private static Conexion login;
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    
    
}
