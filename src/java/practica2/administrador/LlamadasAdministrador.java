package practica2.administrador;

import java.sql.*;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class LlamadasAdministrador {
    
    private static Connection cn;
    private static Conexion login;
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public boolean siExiste(String sql, String nombre_usuario) throws SQLException{
        obtenerConexion();
        PreparedStatement busqueda = cn.prepareStatement(sql);
        busqueda.setString(1, nombre_usuario);
        ResultSet result = busqueda.executeQuery();
        login.Desconectar();
        return result.next();
    }
}
