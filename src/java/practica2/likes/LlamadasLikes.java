package practica2.likes;

import java.sql.*;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class LlamadasLikes {
    
    private static Connection cn;
    private static Conexion login;
    private static final String NUEVO_LIKE = "INSERT INTO Likes (id, nombre_usuario, id_revista) VALUES (?, ?, ?)";
    private static final String BORRAR_LIKE = "DELETE FROM Likes WHERE nombre_usuario = ? AND id_revista = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public boolean siExiste(String sql, String nombre_usuario, int id_revista) throws SQLException{
        obtenerConexion();
        PreparedStatement busqueda = cn.prepareStatement(sql);
        busqueda.setString(1, nombre_usuario);
        busqueda.setInt(2, id_revista);
        ResultSet result = busqueda.executeQuery();
        login.Desconectar();
        return result.next();        
    }
    
    public void crearLike(String nombre_usuario, int id_revista) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionLike = cn.prepareStatement(NUEVO_LIKE);
        declaracionLike.setInt(1, 0);
        declaracionLike.setString(2, nombre_usuario);
        declaracionLike.setInt(3, id_revista);
        declaracionLike.executeUpdate();
        login.Desconectar();
    }
    
    public void eliminarLike(String nombre_usuario, int id_revista) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionLike = cn.prepareStatement(BORRAR_LIKE);
        declaracionLike.setString(1, nombre_usuario);
        declaracionLike.setInt(2, id_revista);
        declaracionLike.executeUpdate();
        login.Desconectar();
    }
}
