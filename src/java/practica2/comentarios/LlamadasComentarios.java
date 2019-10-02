package practica2.comentarios;

import java.sql.*;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class LlamadasComentarios {
    
    private static Connection cn;
    private static Conexion login;
    private static final String CREACION_COMENTARIO = "INSERT INTO Comentarios (id, nombre_usuario, id_revista, descripcion) VALUES(?, ?, ?, ?)";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearComentario(String nombre_usuario, int id_revista, String descripcion) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionComentario = cn.prepareStatement(CREACION_COMENTARIO);
        declaracionComentario.setInt(1, 0);
        declaracionComentario.setString(2, nombre_usuario);
        declaracionComentario.setInt(3, id_revista);
        declaracionComentario.setString(4, descripcion);
        declaracionComentario.executeUpdate();        
    }
    
}
