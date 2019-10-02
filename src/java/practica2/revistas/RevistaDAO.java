package practica2.revistas;

import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class RevistaDAO {
    
    private static Connection cn;
    private static Conexion login;
    private static final String TITULO_SUBIDO = "SELECT * FROM Titulos_revistas WHERE editor = ?";
    private static final String REVISTA_SUBIDA = "SELECT * FROM Revista WHERE id = ?";
    private static final String TITULO_PUBLICADO = "SELECT * FROM Titulos_revistas ORDER BY id";
    private static final String LISTADO_TITULOS = "SELECT * FROM Revista ORDER BY id";
    
    public static Connection obtenerConexion(){
        login= new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Revista> ListarRevistas(String editor) throws SQLException, IOException{
        ArrayList<Revista> list = new ArrayList<Revista>();
        obtenerConexion();
        PreparedStatement declaracionTitulo = cn.prepareStatement(TITULO_SUBIDO);
        declaracionTitulo.setString(1, editor);
        ResultSet result2 = declaracionTitulo.executeQuery();
        while(result2.next()){
            Revista revista = new Revista();
            revista.setId(result2.getInt("id"));
            revista.setId_revista(result2.getInt("id_revista"));
            PreparedStatement declaracionRev = cn.prepareStatement(REVISTA_SUBIDA);
            declaracionRev.setInt(1, revista.getId_revista());
            ResultSet result3 = declaracionRev.executeQuery();
            while(result3.next()){
                revista.setTitulo_revista(result3.getString("titulo_revista"));
            }   
            revista.setArchivoPdf(result2.getBytes("pdf_revista"));
            list.add(revista);
        }      
        login.Desconectar();
        return list;
    }
    
    public ArrayList<Revista> ListarTitulos() throws SQLException {
        ArrayList<Revista> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionTitulo = cn.prepareStatement(LISTADO_TITULOS);
        ResultSet result = declaracionTitulo.executeQuery();
        while(result.next()){
            Revista revista = new Revista();
            revista.setId(result.getInt("id"));
            revista.setTitulo_revista(result.getString("titulo_revista"));
            revista.setEditor(result.getString("editor"));
            list.add(revista);
        }
        login.Desconectar();
        return list;
    }
    
    public ArrayList<Revista> ListarRevistasAdmin() throws SQLException{
        ArrayList<Revista> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionTitulo = cn.prepareStatement(LISTADO_TITULOS);
        ResultSet result = declaracionTitulo.executeQuery();
        while(result.next()){
            Revista revista = new Revista();
            revista.setId(result.getInt("id"));
            revista.setEditor(result.getString("editor"));
            revista.setTitulos_subidos(result.getInt("titulos_subidos"));
            revista.setNo_suscriptores(result.getInt("no_suscriptores"));
            revista.setDescripcion(result.getString("descripcion"));
            revista.setTitulo_revista(result.getString("titulo_revista"));
            revista.setCuota_suscripcion(result.getInt("cuota_suscripcion"));
            revista.setNo_likes(result.getInt("no_likes"));
            revista.setCosto_mensual(result.getInt("costo_mensual"));
            list.add(revista);
        }
        login.Desconectar();
        return list;
    }
     
}
