package practica2.categorias;

import java.sql.*;
import java.util.ArrayList;
import practica2.clases.Conexion;
import practica2.revistas.Revista;

/**
 *
 * @author luisGonzalez
 */
public class CategoriasDAO {

    private static Connection cn;
    private static Conexion login;
    private static final String LISTADO = "SELECT * FROM Categorias ORDER BY id";
    private static final String LISTADO_FILTRADO = "SELECT * FROM Categorias_revista WHERE categoria = ?";
    private static final String LISTADO_REVISTAS = "SELECT * FROM Revista WHERE id = ?";
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Categoria> ListarCategoriasAdmin() throws SQLException{
        ArrayList<Categoria> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionListado = cn.prepareStatement(LISTADO);
        ResultSet result = declaracionListado.executeQuery();
        while(result.next()){
            Categoria categoria = new Categoria();
            categoria.setId(result.getInt("id"));
            categoria.setDescripcion(result.getString("descripcion"));            
            list.add(categoria);
        }
        login.Desconectar();
        return list;
    }
    
    public ArrayList<Revista> ListarFiltros(String categoria) throws SQLException{
        ArrayList<Revista> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionListado = cn.prepareStatement(LISTADO_FILTRADO);
        declaracionListado.setString(1, categoria);
        ResultSet result = declaracionListado.executeQuery();
        while(result.next()){
            Revista revista = new Revista();
            revista.setId(result.getInt("id_revista"));
            PreparedStatement declaracionRev = cn.prepareStatement(LISTADO_REVISTAS);
            declaracionRev.setInt(1, revista.getId());
            ResultSet result2 = declaracionRev.executeQuery();
            while(result2.next()){                
                revista.setTitulo_revista(result2.getString("titulo_revista"));
                revista.setEditor(result2.getString("editor"));                
            }
            list.add(revista);
        }
        login.Desconectar();
        return list;
    }
    
    
    
    
}
