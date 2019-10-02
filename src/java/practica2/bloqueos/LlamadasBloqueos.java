package practica2.bloqueos;

import java.sql.*;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class LlamadasBloqueos {
    
    private static Connection cn;
    private static Conexion login;
    private static final String CREACION_BLOQUEO = "INSERT INTO Bloqueos (id, id_revista, bloqueo_likes, bloqueo_comentarios, bloqueo_suscripcion) VALUES (?, ?, ?, ?, ?)";
    private static final String BLOQUEO = "desactivado";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearBloqueo(int id_revista) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionBloqueo = cn.prepareStatement(CREACION_BLOQUEO);
        declaracionBloqueo.setInt(1, 0);
        declaracionBloqueo.setInt(2, id_revista);
        declaracionBloqueo.setString(3, BLOQUEO);
        declaracionBloqueo.setString(4, BLOQUEO);
        declaracionBloqueo.setString(5, BLOQUEO);
        declaracionBloqueo.executeUpdate();
        login.Desconectar();
    }
    
    
    
}
