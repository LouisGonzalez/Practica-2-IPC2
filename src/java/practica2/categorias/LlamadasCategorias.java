package practica2.categorias;

import java.sql.*;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class LlamadasCategorias {
    
    private static Connection cn;
    private static Conexion login;
    private static final String CREACION_CATEGORIA = "INSERT INTO Categorias (id, descripcion) VALUES (?, ?)";
    private static final String CREACION_CATEGORIA_REVISTA = "INSERT INTO Categorias_revista (id, id_revista, categoria) VALUES (?, ?, ?)";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearCategoriaAdmin(String descripcion) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionCategoria = cn.prepareStatement(CREACION_CATEGORIA);
        declaracionCategoria.setInt(1, 0);
        declaracionCategoria.setString(2, descripcion);
        declaracionCategoria.executeUpdate();
        login.Desconectar();
    }
    
    public void crearCategoria(int id_revista, String descripcion) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionCategoria = cn.prepareStatement(CREACION_CATEGORIA_REVISTA);
        declaracionCategoria.setInt(1, 0);
        declaracionCategoria.setInt(2, id_revista);
        declaracionCategoria.setString(3, descripcion);
        declaracionCategoria.executeUpdate();
        login.Desconectar();
    }
}
