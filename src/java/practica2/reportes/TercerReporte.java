package practica2.reportes;

import java.sql.*;
import java.util.ArrayList;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class TercerReporte {
    
    private static Connection cn;
    private static Conexion login;
    private Atributos atributo;
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Atributos> ListarLikes(Date fechaInicial, Date fechaFinal, Object objeto, Object columnaCondicion) throws SQLException{
        ArrayList<Atributos> list = new ArrayList<>();
        obtenerConexion();
        String LLAMADA = "SELECT * FROM Revista r JOIN Likes l ON r.id = l.id_revista WHERE r."+columnaCondicion+" = ? AND l.fecha_like <= ?";
        String SEGUNDA_LLAMADA = "SELECT * FROM Revista r JOIN Likes l ON r.id = l.id_revista WHERE r."+columnaCondicion+" = ? AND l.fecha_like >= ?";
        String TERCERA_LLAMADA = "SELECT * FROM Revista r JOIN Likes l ON r.id = l.id_revista WHERE r."+columnaCondicion+" = ?";
        String CUARTA_LLAMADA = "SELECT * FROM Revista r JOIN Likes l ON r.id = l.id_revista WHERE r."+columnaCondicion+" = ? AND l.fecha_like >= ? AND l.fecha_like <= ?";
        PreparedStatement declaracionLike = null;
        ResultSet result = null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionLike = cn.prepareStatement(LLAMADA);
            declaracionLike.setObject(1, objeto);
            declaracionLike.setDate(2, fechaFinal);
            result = declaracionLike.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionLike = cn.prepareStatement(SEGUNDA_LLAMADA);
            declaracionLike.setObject(1, objeto);
            declaracionLike.setDate(2, fechaInicial);
            result = declaracionLike.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else if(fechaInicial == null && fechaFinal == null){
            declaracionLike = cn.prepareStatement(TERCERA_LLAMADA);
            declaracionLike.setObject(1, objeto);
            result = declaracionLike.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else {
            declaracionLike = cn.prepareStatement(CUARTA_LLAMADA);
            declaracionLike.setObject(1, objeto);
            declaracionLike.setDate(2, fechaInicial);
            declaracionLike.setDate(3, fechaFinal);
            result = declaracionLike.executeQuery();
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
        atributo.setFecha_creado(result.getDate("fecha_like"));
        return atributo;
    }
}
