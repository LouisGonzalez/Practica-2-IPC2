package practica2.comentarios;

import java.sql.*;
import java.util.ArrayList;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class ComentariosDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO = "SELECT * FROM Comentarios WHERE id_revista = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Comentarios> ListarComentarios(int id_revista) throws SQLException{
        ArrayList<Comentarios> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionComentario = cn.prepareStatement(LISTADO);
        declaracionComentario.setInt(1, id_revista);
        ResultSet result = declaracionComentario.executeQuery();
        while(result.next()){
            Comentarios comentario = new Comentarios();
            comentario.setId(result.getInt("id"));
            comentario.setNombre_usuario(result.getString("nombre_usuario"));
            comentario.setDescripcion(result.getString("descripcion"));
            list.add(comentario);            
        }
        login.Desconectar();
        return list;
    }
    
}
