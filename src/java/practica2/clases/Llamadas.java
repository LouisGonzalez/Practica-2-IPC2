package practica2.clases;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luisGonzalez
 */
public class Llamadas {

    private static Connection cn;
    private static Conexion login;
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();        
        return cn;
    }    
    
    public void llamadaTablas(String tabla, Connection cn, int id) throws SQLException{
        obtenerConexion();
        String llamada = "SELECT * FROM ? WHERE id = ?";
        PreparedStatement declaracionLlamada = cn.prepareStatement(llamada);
        declaracionLlamada.setString(1, tabla);
        declaracionLlamada.setInt(2, id);
        declaracionLlamada.execute();   
        login.Desconectar();
    }
    
    //metodo para insertar un nuevo usuario dentro del sistema 
    public void insertarUsuario(String nombre, String apellido, String userName, String password, String tipoUsuario, int edad, Date nacimiento) throws SQLException{
        obtenerConexion();
        String nuevoUsuario = "INSERT INTO Usuarios VALUES ('"+0+"','"+nombre+"','"+apellido+"','"+userName+"','"+password+"','"+tipoUsuario+"','"+null+"','"+edad+"','"+nacimiento+"','"+null+"','"+null+"','"+null+"','"+null+"')";
        PreparedStatement declaracionUsuarios = cn.prepareStatement(nuevoUsuario);
        declaracionUsuarios.executeUpdate();   
        login.Desconectar();
    }
    
    //metodo para insertar la imagen de perfil a un usuario
    public void insertarImagen(InputStream foto, String username) throws SQLException{
        obtenerConexion();
        String imagen = "UPDATE Usuarios SET foto_perfil = ? WHERE nombre_usuario = ?";
        PreparedStatement declaracionImagen = cn.prepareStatement(imagen);
        declaracionImagen.setBlob(1, foto);
        declaracionImagen.setString(2, username);
        declaracionImagen.executeUpdate();
    }
    
    //metodo para iniciar sesion dentro del sistema
    public void inicioSesion(String username, String password, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        obtenerConexion();
        String captura = "";
        String sesion = "SELECT * FROM Usuarios WHERE nombre_usuario = ? AND password = ?";
        PreparedStatement declaracionSesion = cn.prepareStatement(sesion);
        declaracionSesion.setString(1, username);
        declaracionSesion.setString(2, password);
        ResultSet result = declaracionSesion.executeQuery();
        while(result.next()){
            captura = result.getString("tipo_usuario");
        }
        if(captura.equals("usuario")){
            System.out.println("has elegido usuario");
            request.getRequestDispatcher("PerfilUsuario.jsp").forward(request, response);
        } else if(captura.equals("editor")){
            System.out.println("has elegido editor");
            request.getRequestDispatcher("PerfilEditor.jsp").forward(request, response);
        } else if(captura.equals("Administrador")){
            System.out.println("has elegido a un administrador");
            request.getRequestDispatcher("PerfilAdministrador.jsp").forward(request, response);
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("<script>");
                request.getRequestDispatcher("registro.jsp").forward(request, response);            
                out.println("alert('Usuario o password incorrectos')");
                out.println("</script>");
            }
            
        }
    }
    
    //metodo generico
    //metodo para modificar cualquiera de los campos de informacion de un usuario
    public void modificarDatoUsuario(String campo, String valor, String username, String tabla, String columna) throws SQLException{
        obtenerConexion();
        String usuario = "UPDATE "+tabla+" SET "+campo+" = ? WHERE "+columna+" = ?";
        PreparedStatement declaracionCambio = cn.prepareStatement(usuario);
        declaracionCambio.setString(1, valor);
        declaracionCambio.setString(2, username);
        declaracionCambio.executeUpdate();
        login.Desconectar();
    }
    
    
    //verifica si la cuenta es de tipo usuario o de tipo editor
    public void comprobacionTipoUsuario(String username, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        obtenerConexion();
        String captura = "";
        String comprobacion = "SELECT * FROM Usuarios WHERE nombre_usuario = ?";
        PreparedStatement declaracionComprobar = cn.prepareStatement(comprobacion);
        declaracionComprobar.setString(1, username);
        ResultSet result = declaracionComprobar.executeQuery();
        while(result.next()){
            captura = result.getString("tipo_usuario");
        }
        if(captura.equals("editor")){
            request.getRequestDispatcher("PerfilEditor.jsp").forward(request, response);
        } else if(captura.equals("usuario")){
            request.getRequestDispatcher("PerfilUsuario.jsp").forward(request, response);
        }
    }
    
    //metodo generico
    //metodo encargado de mostrar un dato especifico del usuario
    public Object mostrarDatos(String username, String valor, String tabla, String columna) throws SQLException, IOException{
        obtenerConexion(); 
        Object captura = "";
        String comprobacion = "SELECT * FROM "+tabla+" WHERE "+columna+" = ?";
        PreparedStatement declaracionComprobar = cn.prepareStatement(comprobacion);
        declaracionComprobar.setString(1, username);
        ResultSet result = declaracionComprobar.executeQuery();
        while(result.next()){
            captura = result.getObject(valor);         
        }
        return captura;
       
   }
    
}
