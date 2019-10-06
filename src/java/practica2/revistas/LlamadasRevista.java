package practica2.revistas;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import practica2.clases.Conexion;
import practica2.clases.Llamadas;
import practica2.clases.UsuarioDAO;
import practica2.general.LlamadasGenerales;

/**
 *
 * @author luisGonzalez
 */
public class LlamadasRevista {
    
    private static Connection cn;
    private static Conexion login;
    private Llamadas llamadas = new Llamadas();
    private LlamadasGenerales llamadaGeneral = new LlamadasGenerales();
    private static final String SELECT_TITULOS = "SELECT * FROM Titulos_revistas WHERE id= ?";
    
    public static Connection obtenerConexion(){
        login= new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearRevista(String editor, String descripcion, String titulo_revista, int cuota_suscripcion, Date fecha_creacion) throws SQLException, IOException {
        obtenerConexion();
        int cuota = (int) llamadaGeneral.mostrarDatos(1, "Cuota", "Cuota_global", "id");
        String nuevaRevista = "INSERT INTO Revista VALUES ('"+0+"','"+editor+"','"+1+"','"+0+"','"+descripcion+"','"+titulo_revista+"','"+cuota_suscripcion+"','"+0+"','"+cuota+"','"+fecha_creacion+"')";
        PreparedStatement declaracionRevista = cn.prepareStatement(nuevaRevista);
        declaracionRevista.executeUpdate();
        login.Desconectar();
    }
    
    public void crearTitulo(String editor, Date fecha_creacion, InputStream pdf_revista, String titulo) throws SQLException, IOException{
        obtenerConexion();
        int id_revista = Integer.parseInt(llamadas.mostrarDatos(titulo, "id", "Revista", "titulo_revista").toString());
        System.out.println(id_revista);
        int no_titulo = Integer.parseInt(llamadas.mostrarDatos(titulo, "titulos_subidos", "Revista", "titulo_revista").toString());
        int nuevo_valor = no_titulo + 1;
        String nuevoTitulo = "INSERT INTO Titulos_revistas (id, editor, id_revista, no_titulo, likes, fecha_creacion, pdf_revista) VALUES(?, ?, ?, ?, ?, ?, ?)";        
        PreparedStatement declaracionTitulo = cn.prepareStatement(nuevoTitulo);
        declaracionTitulo.setInt(1, 0);
        declaracionTitulo.setString(2, editor);
        declaracionTitulo.setInt(3, id_revista);
        declaracionTitulo.setInt(4, no_titulo);
        declaracionTitulo.setInt(5, 0);
        declaracionTitulo.setDate(6, fecha_creacion);
        declaracionTitulo.setBlob(7, pdf_revista);
        declaracionTitulo.executeUpdate();
        login.Desconectar();
        llamadas.modificarDatoUsuario("titulos_subidos", Integer.toString(nuevo_valor), titulo, "Revista", "titulo_revista");
        //modifica el valor de usuario de "usuario" a "editor"
        llamadas.modificarDatoUsuario("tipo_usuario", "editor", editor, "Usuarios", "nombre_usuario");      
    }
    
    public void mostrarRevista(String editor, int id, HttpServletResponse response) {
        obtenerConexion();
        byte[] b = null;
        try{
        PreparedStatement declaracionArchivo = cn.prepareStatement(SELECT_TITULOS);
        declaracionArchivo.setInt(1, id);
        ResultSet result = declaracionArchivo.executeQuery();
        while(result.next()){
            b = result.getBytes(7);
        }
        InputStream input = new ByteArrayInputStream(b);
        int tamanoInput = input.available();
        byte[] datos = new byte[tamanoInput];
        input.read(datos, 0, tamanoInput);
        response.getOutputStream().write(datos);
        input.close();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);                                 
        }
        
        
    }
    
    
    

    
    
    
    
}
