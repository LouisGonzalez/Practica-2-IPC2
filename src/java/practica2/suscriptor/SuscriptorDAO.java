package practica2.suscriptor;
import java.sql.*;
import java.util.ArrayList;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class SuscriptorDAO {
    
    
    private static final String LISTADO_SUSCRIPCION = "SELECT * FROM Suscriptor WHERE nombre_usuario = ?";
    private static final String LISTADO_REVISTAS = "SELECT * FROM Revista WHERE id = ?";
    private static final String LISTADO_TITULOS = "SELECT * FROM Titulos_revistas WHERE id_revista = ?";
    private static Connection cn;
    private static Conexion login;
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Suscriptor> ListarSuscripciones(String user) throws SQLException{
        ArrayList<Suscriptor> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionSuscriptor = cn.prepareStatement(LISTADO_SUSCRIPCION);
        declaracionSuscriptor.setString(1, user);
        ResultSet result = declaracionSuscriptor.executeQuery();
        while(result.next()){
            Suscriptor suscriptor = new Suscriptor();
            suscriptor.setId(result.getInt("id"));
            suscriptor.setId_revista(result.getInt("id_revista"));
            suscriptor.setFecha_suscripcion(result.getDate("fecha_suscripcion"));
            suscriptor.setUltimo_pago(result.getDate("ultimo_pago"));
            PreparedStatement declaracionRev = cn.prepareStatement(LISTADO_REVISTAS);
            declaracionRev.setInt(1, suscriptor.getId_revista());
            ResultSet result2 = declaracionRev.executeQuery();
            while(result2.next()){
                suscriptor.setTitulo_revista(result2.getString("titulo_revista"));
            }
            list.add(suscriptor);
        }
        login.Desconectar();
        return list;
    }
    
    public ArrayList<Suscriptor> ListarTitulos(int id_revista) throws SQLException{
        ArrayList<Suscriptor> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionTitulo = cn.prepareStatement(LISTADO_TITULOS);
        declaracionTitulo.setInt(1, id_revista);
        ResultSet result = declaracionTitulo.executeQuery();
        while(result.next()){
            Suscriptor suscriptor = new Suscriptor();
            
            suscriptor.setFecha_creacion(result.getDate("fecha_creacion"));
            suscriptor.setMiRevista(result.getBytes("pdf_revista"));
            suscriptor.setId(result.getInt("id"));
            list.add(suscriptor);
        }
        login.Desconectar();
        return list;
    }
}
