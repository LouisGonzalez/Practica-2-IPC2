package practica2.administrador;

import java.sql.*;
import java.util.ArrayList;
import practica2.clases.Conexion;
import practica2.clases.Usuario;

/**
 *
 * @author luisGonzalez
 */
public class AdministradorDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String USUARIOS = "SELECT * FROM Usuarios WHERE tipo_usuario = ?";
    private static final String BUSQUEDA = "SELECT * FROM Suscriptor WHERE nombre_usuario = ?";
    private LlamadasAdministrador llamadaAdmin = new LlamadasAdministrador();
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Usuario> ListarUsuarios() throws SQLException{
        ArrayList<Usuario> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionUsuario = cn.prepareStatement(USUARIOS);
        declaracionUsuario.setString(1, "usuario");
        ResultSet result = declaracionUsuario.executeQuery();
        boolean verificador;
        while(result.next()){
            Usuario user = new Usuario();
            user.setNombre_usuario(result.getString("nombre_usuario"));
            verificador = llamadaAdmin.siExiste(BUSQUEDA, user.getNombre_usuario());
            if(verificador == false) {
                list.add(user);
            }
        }
        login.Desconectar();
        return list;
    }
    
    public ArrayList<Usuario> ListarAdmin(String usuario) throws SQLException{
        ArrayList<Usuario> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionUsuario = cn.prepareStatement(USUARIOS);
        declaracionUsuario.setString(1, "Administrador");
        ResultSet result = declaracionUsuario.executeQuery();
        while(result.next()){
            Usuario user = new Usuario();
            user.setNombre_usuario(result.getString("nombre_usuario"));            
            if(usuario.equals("CUENTA PADRE")){
                if(!user.getNombre_usuario().equals(usuario)){
                    list.add(user);
                }                
            } else {
                if(!user.getNombre_usuario().equals("CUENTA PADRE") && !user.getNombre_usuario().equals(usuario)){
                    list.add(user);
                }
            }                  
        }
        login.Desconectar();
        return list;
    }
}
