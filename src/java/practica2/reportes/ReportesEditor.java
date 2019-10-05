package practica2.reportes;

import java.sql.*;
import java.util.ArrayList;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class ReportesEditor {
    
    private static Connection cn;
    private static Conexion login;
    private static final String LLAMADA = "SELECT * FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE r.editor = ? AND c.fecha_comentario <= ?";
    private static final String SEGUNDA_LLAMADA = "SELECT * FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE r.editor = ? AND c.fecha_comentario >= ?";
    private static final String TERCERA_LLAMADA = "SELECT * FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE r.editor = ?";
    private static final String CUARTA_LLAMADA = "SELECT * FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE r.editor = ? AND c.fecha_comentario >= ? AND c.fecha_comentario <= ?";
    private static final String LLAMADA_FILTRO = "SELECT * FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE r.titulo_revista = ? AND c.fecha_comentario <= ?";
    private static final String SEGUNDA_LLAMADA_FILTRO = "SELECT * FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE r.titulo_revista = ? AND c.fecha_comentario >= ?";
    private static final String TERCERA_LLAMADA_FILTRO = "SELECT * FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE r.titulo_revista = ?";
    private static final String CUARTA_LLAMADA_FILTRO = "SELECT * FROM Revista r JOIN Comentarios c ON r.id = c.id_revista WHERE r.titulo_revista = ? AND c.fecha_comentario >= ? AND c.fecha_comentario <= ?";
    
    
    private Atributos atributo;
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }   
    
    public ArrayList<Atributos> ListarComentarios(Date fechaInicial, Date fechaFinal, String nombre_usuario) throws SQLException{
        ArrayList<Atributos> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionComentario = null;
        ResultSet result = null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionComentario = cn.prepareStatement(LLAMADA);
            declaracionComentario.setString(1, nombre_usuario);
            declaracionComentario.setDate(2, fechaFinal);
            result = declaracionComentario.executeQuery();
            while(result.next()){
                atributo = new Atributos();
                atributo.setTitulo_revista(result.getString("titulo_revista"));
                atributo.setNombre_usuario(result.getString("nombre_usuario"));
                atributo.setFecha_creado(result.getDate("fecha_comentario"));
                atributo.setComentario(result.getString("c.descripcion"));
                list.add(atributo);         
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionComentario = cn.prepareStatement(SEGUNDA_LLAMADA);
            declaracionComentario.setString(1, nombre_usuario);
            declaracionComentario.setDate(2, fechaInicial);
            result = declaracionComentario.executeQuery();
            while(result.next()){
                atributo = new Atributos();
                atributo.setTitulo_revista(result.getString("titulo_revista"));
                atributo.setNombre_usuario(result.getString("nombre_usuario"));
                atributo.setFecha_creado(result.getDate("fecha_comentario"));
                atributo.setComentario(result.getString("c.descripcion"));
                list.add(atributo);         
            }
        } else if(fechaFinal == null && fechaInicial == null){
            declaracionComentario = cn.prepareStatement(TERCERA_LLAMADA);
            declaracionComentario.setString(1, nombre_usuario);
            result = declaracionComentario.executeQuery();
            while(result.next()){
                atributo = new Atributos();
                atributo.setTitulo_revista(result.getString("titulo_revista"));
                atributo.setNombre_usuario(result.getString("nombre_usuario"));
                atributo.setFecha_creado(result.getDate("fecha_comentario"));
                atributo.setComentario(result.getString("c.descripcion"));
                list.add(atributo);         
            }
        } else {
            declaracionComentario = cn.prepareStatement(CUARTA_LLAMADA);
            declaracionComentario.setString(1, nombre_usuario);
            declaracionComentario.setDate(2, fechaInicial);
            declaracionComentario.setDate(3, fechaFinal);
            result = declaracionComentario.executeQuery();
            while(result.next()){
                atributo = new Atributos();
                atributo.setTitulo_revista(result.getString("titulo_revista"));
                atributo.setNombre_usuario(result.getString("nombre_usuario"));
                atributo.setFecha_creado(result.getDate("fecha_comentario"));
                atributo.setComentario(result.getString("c.descripcion"));
                list.add(atributo);         
            }
        }
        login.Desconectar();
        return list;
    }
    
    public ArrayList<Atributos> ListarComentariosFiltrados(Date fechaInicial, Date fechaFinal, String revista) throws SQLException{
        ArrayList<Atributos> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionComentario = null;
        ResultSet result = null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionComentario = cn.prepareStatement(LLAMADA_FILTRO);
            declaracionComentario.setString(1, revista);
            declaracionComentario.setDate(2, fechaFinal);
            result = declaracionComentario.executeQuery();
            while(result.next()){
                atributo = new Atributos();
                atributo.setTitulo_revista(result.getString("titulo_revista"));
                atributo.setNombre_usuario(result.getString("nombre_usuario"));
                atributo.setFecha_creado(result.getDate("fecha_comentario"));
                atributo.setComentario(result.getString("c.descripcion"));
                list.add(atributo);         
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionComentario = cn.prepareStatement(SEGUNDA_LLAMADA_FILTRO);
            declaracionComentario.setString(1, revista);
            declaracionComentario.setDate(2, fechaInicial);
            result = declaracionComentario.executeQuery();
            while(result.next()){
                atributo = new Atributos();
                atributo.setTitulo_revista(result.getString("titulo_revista"));
                atributo.setNombre_usuario(result.getString("nombre_usuario"));
                atributo.setFecha_creado(result.getDate("fecha_comentario"));
                atributo.setComentario(result.getString("c.descripcion"));
                list.add(atributo);         
            }
        } else if(fechaFinal == null && fechaInicial == null){
            declaracionComentario = cn.prepareStatement(TERCERA_LLAMADA_FILTRO);
            declaracionComentario.setString(1, revista);
            result = declaracionComentario.executeQuery();
            while(result.next()){
                atributo = new Atributos();
                atributo.setTitulo_revista(result.getString("titulo_revista"));
                atributo.setNombre_usuario(result.getString("nombre_usuario"));
                atributo.setFecha_creado(result.getDate("fecha_comentario"));
                atributo.setComentario(result.getString("c.descripcion"));
                list.add(atributo);         
            }
        } else {
            declaracionComentario = cn.prepareStatement(CUARTA_LLAMADA_FILTRO);
            declaracionComentario.setString(1, revista);
            declaracionComentario.setDate(2, fechaInicial);
            declaracionComentario.setDate(3, fechaFinal);
            result = declaracionComentario.executeQuery();
            while(result.next()){
                atributo = new Atributos();
                atributo.setTitulo_revista(result.getString("titulo_revista"));
                atributo.setNombre_usuario(result.getString("nombre_usuario"));
                atributo.setFecha_creado(result.getDate("fecha_comentario"));
                atributo.setComentario(result.getString("c.descripcion"));
                list.add(atributo); 
        
            }
        }
        login.Desconectar();
        return list;
    }
    
    
    
    
}
