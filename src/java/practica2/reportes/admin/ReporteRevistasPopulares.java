package practica2.reportes.admin;

import java.sql.*;
import java.util.ArrayList;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class ReporteRevistasPopulares {

    private static Connection cn;
    private static Conexion login;
    private AtributosAdmin atributo;
    private static final String LLAMADA = "SELECT titulo_revista, COUNT(*) FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista WHERE fecha_suscripcion <= ? GROUP BY titulo_revista ORDER BY COUNT(*) DESC";
    private static final String SEGUNDA_LLAMADA = "SELECT titulo_revista, editor, COUNT(*) FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista WHERE fecha_suscripcion >= ? GROUP BY titulo_revista ORDER BY COUNT(*) DESC";
    private static final String TERCERA_LLAMADA = "SELECT titulo_revista, editor, COUNT(*) FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista GROUP BY titulo_revista ORDER BY COUNT(*) DESC";
    private static final String CUARTA_LLAMADA = "SELECT titulo_revista, editor, COUNT(*) FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista WHERE fecha_suscripcion >= ? AND fecha_suscripcion <= ? GROUP BY titulo_revista ORDER BY COUNT(*) DESC";
    //JOINS para obtener suscripciones por revista
    private static final String LLAMADA_FILTRO = "SELECT * FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista WHERE titulo_revista = ? AND fecha_suscripcion <= ?";
    private static final String SEGUNDA_LLAMADA_FILTRO = "SELECT * FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista WHERE titulo_revista = ? AND fecha_suscripcion >= ?";
    private static final String TERCERA_LLAMADA_FILTRO = "SELECT * FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista WHERE titulo_revista = ?";
    private static final String CUARTA_LLAMADA_FILTRO = "SELECT * FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista WHERE titulo_revista = ? AND fecha_suscripcion >= ? AND fecha_suscripcion <= ?";
    
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    //metodo para conseguir el total de suscripciones de una revista
    public ArrayList<AtributosAdmin> listarRevistasPopulares(Date fechaInicial, Date fechaFinal) throws SQLException{
        ArrayList<AtributosAdmin> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionRev = null;
        ResultSet result = null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionRev = cn.prepareStatement(LLAMADA);
            declaracionRev.setDate(1, fechaFinal);
            result = declaracionRev.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionRev = cn.prepareStatement(SEGUNDA_LLAMADA);
            declaracionRev.setDate(1, fechaInicial);
            result = declaracionRev.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial == null){
            declaracionRev = cn.prepareStatement(TERCERA_LLAMADA);
            result = declaracionRev.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else {
            declaracionRev = cn.prepareStatement(CUARTA_LLAMADA);
            declaracionRev.setDate(1, fechaInicial);
            declaracionRev.setDate(2, fechaFinal);
            result = declaracionRev.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        }
        login.Desconectar();
        return list;
    }
    
    //seteo del primer metodo
    private AtributosAdmin seteo(ResultSet result) throws SQLException{
        atributo = new AtributosAdmin();
        atributo.setTitulo_revista(result.getString("titulo_revista"));
        atributo.setEditor(result.getString("editor"));
        atributo.setNo_suscriptores(result.getInt("COUNT(*)"));
        return atributo;
    }
    
    //metodo para conseguir las suscripciones por cada revista
    public ArrayList<AtributosAdmin> listarSuscripciones(Date fechaInicial, Date fechaFinal, String titulo) throws SQLException{
        ArrayList<AtributosAdmin> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionRev = null;
        ResultSet result = null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionRev = cn.prepareStatement(LLAMADA_FILTRO);
            declaracionRev.setString(1, titulo);
            declaracionRev.setDate(2, fechaFinal);
            result = declaracionRev.executeQuery();
            while(result.next()){
                segundoSeteo(result);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionRev = cn.prepareStatement(SEGUNDA_LLAMADA_FILTRO);
            declaracionRev.setString(1, titulo);
            declaracionRev.setDate(2, fechaInicial);
            result = declaracionRev.executeQuery();
            while(result.next()){
                segundoSeteo(result);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial == null){
            declaracionRev = cn.prepareStatement(TERCERA_LLAMADA_FILTRO);
            declaracionRev.setString(1, titulo);            
            result = declaracionRev.executeQuery();
            while(result.next()){
                segundoSeteo(result);
                list.add(atributo);
            }
        } else {
            declaracionRev = cn.prepareStatement(CUARTA_LLAMADA_FILTRO);
            declaracionRev.setString(1, titulo);
            declaracionRev.setDate(2, fechaInicial);
            declaracionRev.setDate(3, fechaFinal);
            result = declaracionRev.executeQuery();
            while(result.next()){
                segundoSeteo(result);
                list.add(atributo);
            }
        }
        login.Desconectar();
        return list;
    }
    
    private AtributosAdmin segundoSeteo(ResultSet result) throws SQLException{
        atributo = new AtributosAdmin();
        atributo.setNombre_usuario(result.getString("nombre_usuario"));
        atributo.setFecha_suscripcion(result.getDate("fecha_suscripcion"));
        return atributo;
    }
    
}
