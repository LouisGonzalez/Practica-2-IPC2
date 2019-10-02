package practica2.general;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.http.HttpServletResponse;
import practica2.clases.Conexion;
import practica2.clases.Llamadas;
import static practica2.clases.Llamadas.obtenerConexion;

/**
 *
 * @author luisGonzalez
 */
public class LlamadasGenerales {
 
    private static Connection cn;
    private static Conexion login;
    private Llamadas llamadas = new Llamadas();
    
    public static Connection obtenerConexion(){
        login= new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    //metodo para crear un listado de todos los titulos hasta la fecha
    public String[] listarColumna(String tabla, String columna, HttpServletResponse response, PrintWriter out) throws SQLException{        
        obtenerConexion();
        String consulta = "SELECT * FROM "+tabla+" ORDER BY "+columna;
        String[] titulo = null;
        PreparedStatement declaracionConsulta = cn.prepareStatement(consulta);
        ResultSet result = declaracionConsulta.executeQuery();
        //out.println("<select>");           
        while(result.next()){
        //    out.println("<option value="+result.getString("titulo_revista")+">"+result.getString("titulo_revista")+"</option>");
           titulo = new String[Integer.parseInt(result.getString("id")) + 1];
           titulo[Integer.parseInt(result.getString("id"))] = result.getString("titulo_revista");
        }
        //out.println("</select>");
        return titulo;    
       
    }    
    
    //metodo generico
    //metodo para modificar cualquiera de los campos de informacion de un usuario
    public void modificarDatoUsuario(String campo, Object valor, Object username, String tabla, String columna) throws SQLException{
        obtenerConexion();
        String usuario = "UPDATE "+tabla+" SET "+campo+" = ? WHERE "+columna+" = ?";
        PreparedStatement declaracionCambio = cn.prepareStatement(usuario);
        declaracionCambio.setObject(1, valor);
        declaracionCambio.setObject(2, username);
        declaracionCambio.executeUpdate();
        login.Desconectar();
    }
    
     public Object mostrarDatos(Object username, String valor, String tabla, String columna) throws SQLException, IOException{
        obtenerConexion(); 
        Object captura = "";
        String comprobacion = "SELECT * FROM "+tabla+" WHERE "+columna+" = ?";
        PreparedStatement declaracionComprobar = cn.prepareStatement(comprobacion);
        declaracionComprobar.setObject(1, username);
        ResultSet result = declaracionComprobar.executeQuery();
        while(result.next()){
            captura = result.getObject(valor);         
        }
        return captura;       
    }
     
 
}
