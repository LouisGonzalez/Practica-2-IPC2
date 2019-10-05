package practica2.reportes;

import java.sql.*;
import java.util.ArrayList;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class SegundoReporte {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LLAMADAS = "SELECT * FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista WHERE r.editor = ? ";
    private Atributos atributo;
  
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Atributos> ListarSuscripciones(Date fechaInicial, Date fechaFinal, Object objeto, Object columnaCondicion) throws SQLException{
        ArrayList<Atributos> list = new ArrayList<>();
        obtenerConexion();
        String LLAMADA = "SELECT * FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista WHERE r."+columnaCondicion+" = ? AND s.fecha_suscripcion <= ?";
        String SEGUNDA_LLAMADA = "SELECT * FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista WHERE r."+columnaCondicion+" = ? AND s.fecha_suscripcion >= ?";
        String TERCERA_LLAMADA = "SELECT * FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista WHERE r."+columnaCondicion+" = ?";
        String CUARTA_LLAMADA = "SELECT * FROM Revista r JOIN Suscriptor s ON r.id = s.id_revista WHERE r."+columnaCondicion+" = ? AND s.fecha_suscripcion >= ? AND s.fecha_suscripcion <= ?";
        PreparedStatement declaracionSuscripcion = null;
        ResultSet result= null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionSuscripcion = cn.prepareStatement(LLAMADA);
            declaracionSuscripcion.setObject(1, objeto);
            declaracionSuscripcion.setDate(2, fechaFinal);
            result = declaracionSuscripcion.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionSuscripcion = cn.prepareStatement(SEGUNDA_LLAMADA);
            declaracionSuscripcion.setObject(1, objeto);
            declaracionSuscripcion.setDate(2, fechaInicial);
            result = declaracionSuscripcion.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial == null){
            declaracionSuscripcion = cn.prepareStatement(TERCERA_LLAMADA);
            declaracionSuscripcion.setObject(1, objeto);
            result = declaracionSuscripcion.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else {
            declaracionSuscripcion = cn.prepareStatement(CUARTA_LLAMADA);
            declaracionSuscripcion.setObject(1, objeto);
            declaracionSuscripcion.setDate(2, fechaInicial);
            declaracionSuscripcion.setDate(3, fechaFinal);
            result = declaracionSuscripcion.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        }
        login.Desconectar();
        return list;    
    }
    
    private Atributos seteo(ResultSet result) throws SQLException{
        atributo = new Atributos();
        atributo.setNombre_usuario(result.getString("nombre_usuario"));
        atributo.setTitulo_revista(result.getString("titulo_revista"));
        atributo.setFecha_creado(result.getDate("fecha_suscripcion"));
        atributo.setUltimo_pago(result.getDate("ultimo_pago"));
        return atributo;    
    }
   
    
    
}

