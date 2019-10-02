package practica2.clases;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luisGonzalez
 */
public class UsuarioDAO {
    
    private ResultSet result; 
    private final Llamadas llamada = new Llamadas();
    private InputStream input;
    private OutputStream out;
    private BufferedInputStream buffer;
    private BufferedOutputStream bufferOut;
    
    public void listarImagen(String username, HttpServletResponse response){
        response.setContentType("image/*");
        try {
            out = response.getOutputStream();            
            Conexion login = new Conexion();
            Connection cn = login.getConnection();        
            String llamada = "SELECT * FROM Usuarios WHERE nombre_usuario = ?";
            PreparedStatement declaracionLlamada = cn.prepareStatement(llamada);
            declaracionLlamada.setString(1, username);
            result = declaracionLlamada.executeQuery();
            while(result.next()){
                input = result.getBinaryStream(7);    
            }
            buffer = new BufferedInputStream(input);
            bufferOut = new BufferedOutputStream(out);
            int i = 0;
            while((i = buffer.read())!=-1){
                bufferOut.write(i);
            }       
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);                                 
        }
        
    }
    
 
      
    public void agregarFoto(Usuario user, String username){
        try {
            llamada.insertarImagen(user.getFoto(), username);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarUsuario(Usuario user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            llamada.insertarUsuario(user.getNombres(), user.getApellidos(), user.getNombre_usuario(), user.getPassword(), user.getTipo_usuario(), user.getEdad(), user.getNacimiento());
            request.getRequestDispatcher("FotoPerfilUsuario.jsp").forward(request, response);
        } catch (SQLException ex) {
            request.getRequestDispatcher("NuevaCuenta.jsp").forward(request, response);
        }
    }
    
    
}
