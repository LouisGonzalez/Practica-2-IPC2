package practica2.reportes.admin;

import java.sql.*;
import java.util.ArrayList;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class ReporteRevistasComentadas {
    
    private static Connection cn;
    private static Conexion login;
    private AtributosAdmin atributo;
    private static final String LLAMADA = "SELECT titulo_revista, editor, COUNT(*) FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE fecha_comentario <= ? GROUP BY titulo_revista ORDER BY COUNT(*) DESC";
    private static final String SEGUNDA_LLAMADA = "SELECT titulo_revista, editor, COUNT(*) FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE fecha_comentario >= ? GROUP BY titulo_revista ORDER BY COUNT(*) DESC";
    private static final String TERCERA_LLAMADA = "SELECT titulo_revista, editor, COUNT(*) FROM Revista r JOIN Comentarios c ON r.id = c.id_revista GROUP BY titulo_revista ORDER BY COUNT(*) DESC";
    private static final String CUARTA_LLAMADA = "SELECT titulo_revista, editor, COUNT(*) FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE fecha_comentario >= ? AND fecha_comentario <= ? GROUP BY titulo_revista ORDER BY COUNT(*) DESC";
    //JOINS para obtener comentarios por revista
    private static final String LLAMADA_FILTRO = "SELECT * FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE titulo_revista = ? AND fecha_comentario <= ?";
    private static final String SEGUNDA_LLAMADA_FILTRO = "SELECT * FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE titulo_revista = ? AND fecha_comentario >= ?";
    private static final String TERCERA_LLAMADA_FILTRO = "SELECT * FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE titulo_revista = ?";
    private static final String CUARTA_LLAMADA_FILTRO = "SELECT * FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE titulo_revista = ? AND fecha_comentario >= ? AND fecha_comentario <= ?";
    
    
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    //metodo para conseguir el total de comentarios de una revista 
    public ArrayList<AtributosAdmin> listarRevistasComentadas(Date fechaInicial, Date fechaFinal) throws SQLException{
        ArrayList<AtributosAdmin> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionComentarios = null;
        ResultSet result = null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionComentarios = cn.prepareStatement(LLAMADA);
            declaracionComentarios.setDate(1, fechaFinal);
            result = declaracionComentarios.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionComentarios = cn.prepareStatement(SEGUNDA_LLAMADA);
            declaracionComentarios.setDate(1, fechaInicial);
            result = declaracionComentarios.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else if(fechaInicial == null && fechaFinal == null){
            declaracionComentarios = cn.prepareStatement(TERCERA_LLAMADA);
            result = declaracionComentarios.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else {
            declaracionComentarios = cn.prepareStatement(CUARTA_LLAMADA);
            declaracionComentarios.setDate(1, fechaInicial);
            declaracionComentarios.setDate(2, fechaFinal);
            result = declaracionComentarios.executeQuery();
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
        atributo.setNo_comentarios(result.getInt("COUNT(*)"));
        return atributo;
    }
    
    //metodo para conseguir los comentarios por revista
    public ArrayList<AtributosAdmin> listarComentarios(Date fechaInicial, Date fechaFinal, String titulo) throws SQLException{
        ArrayList<AtributosAdmin> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionComentarios = null;
        ResultSet result = null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionComentarios = cn.prepareStatement(LLAMADA_FILTRO);
            declaracionComentarios.setString(1, titulo);
            declaracionComentarios.setDate(2, fechaFinal);
            result = declaracionComentarios.executeQuery();
            while(result.next()){
                segundoSeteo(result);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionComentarios = cn.prepareStatement(SEGUNDA_LLAMADA_FILTRO);
            declaracionComentarios.setString(1, titulo);
            declaracionComentarios.setDate(2, fechaInicial);
            result = declaracionComentarios.executeQuery();
            while(result.next()){
                segundoSeteo(result);
                list.add(atributo);
            }
        } else if(fechaInicial == null && fechaFinal == null){
            declaracionComentarios = cn.prepareStatement(TERCERA_LLAMADA_FILTRO);
            declaracionComentarios.setString(1, titulo);
            result = declaracionComentarios.executeQuery();
            while(result.next()){
                segundoSeteo(result);
                list.add(atributo);
            }
        } else {
            declaracionComentarios = cn.prepareStatement(CUARTA_LLAMADA_FILTRO);
            declaracionComentarios.setString(1, titulo);
            declaracionComentarios.setDate(2, fechaInicial);
            declaracionComentarios.setDate(3, fechaFinal);
            result = declaracionComentarios.executeQuery();
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
        atributo.setComentario(result.getString("c.descripcion"));
        atributo.setFecha_comentario(result.getDate("fecha_comentario"));
        return atributo;
    }
}

