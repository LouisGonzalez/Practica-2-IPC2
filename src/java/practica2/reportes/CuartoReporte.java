package practica2.reportes;

import java.sql.*;
import java.util.ArrayList;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class CuartoReporte {
    
    private static Connection cn;
    private static Conexion login;
    private Atributos atributo;
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<Atributos> ListarGanancias(Date fechaInicial, Date fechaFinal, String titulo) throws SQLException{
        ArrayList<Atributos> list = new ArrayList<>();
        obtenerConexion();
        String LLAMADA = "SELECT * FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE r.titulo_revista = ? AND fecha_pago <= ?";
        String SEGUNDA_LLAMADA = "SELECT * FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE r.titulo_revista = ? AND fecha_pago >= ?";
        String TERCERA_LLAMADA = "SELECT * FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE r.titulo_revista = ?";
        String CUARTA_LLAMADA = "SELECT * FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE r.titulo_revista = ? AND fecha_pago >= ? AND fecha_pago <= ?";
        PreparedStatement declaracionGanancias = null;
        ResultSet result = null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionGanancias = cn.prepareStatement(LLAMADA);
            declaracionGanancias.setObject(1, titulo);
            declaracionGanancias.setDate(2, fechaFinal);
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionGanancias = cn.prepareStatement(SEGUNDA_LLAMADA);
            declaracionGanancias.setObject(1, titulo);
            declaracionGanancias.setDate(2, fechaInicial);
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else if(fechaInicial == null && fechaFinal == null){
            declaracionGanancias = cn.prepareStatement(TERCERA_LLAMADA);
            declaracionGanancias.setObject(1, titulo);
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else {
            declaracionGanancias = cn.prepareStatement(CUARTA_LLAMADA);
            declaracionGanancias.setObject(1, titulo);
            declaracionGanancias.setDate(2, fechaInicial);
            declaracionGanancias.setDate(3, fechaFinal);
            result = declaracionGanancias.executeQuery();
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
        atributo.setFecha_creado(result.getDate("fecha_pago"));
        atributo.setTotal(result.getFloat("total_acumulado"));
        return atributo;
    }
    
    public ArrayList<Atributos> listar(Date fechaInicial, Date fechaFinal, Object objeto, Object objetoColumna) throws SQLException{
        ArrayList<Atributos> list = new ArrayList<>();
        obtenerConexion();
        String LLAMADA = "SELECT id_revista, titulo_revista, editor, SUM(total_acumulado) AS total_acumulado FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE r."+objetoColumna+" = ? AND fecha_pago <= ? GROUP BY id_revista";
        String SEGUNDA_LLAMADA = "SELECT id_revista, titulo_revista, editor, SUM(total_acumulado) AS total_acumulado FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE r."+objetoColumna+" = ? AND fecha_pago >= ? GROUP BY id_revista";
        String TERCERA_LLAMADA = "SELECT id_revista, titulo_revista, editor, SUM(total_acumulado) AS total_acumulado FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE r."+objetoColumna+" = ? GROUP BY id_revista";
        String CUARTA_LLAMADA = "SELECT id_revista, titulo_revista, editor, SUM(total_acumulado) AS total_acumulado FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE r."+objetoColumna+" = ? AND fecha_pago >= ? AND fecha_pago <= ? GROUP BY id_revista";
        PreparedStatement declaracionGanancias = null;
        ResultSet result = null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionGanancias = cn.prepareStatement(LLAMADA);
            declaracionGanancias.setObject(1, objeto);
            declaracionGanancias.setDate(2, fechaFinal);
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                segundoSeteo(result);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionGanancias = cn.prepareStatement(SEGUNDA_LLAMADA);
            declaracionGanancias.setObject(1, objeto);
            declaracionGanancias.setDate(2, fechaInicial);
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                segundoSeteo(result);
                list.add(atributo);
            }
        } else if(fechaInicial == null && fechaFinal == null){
            declaracionGanancias = cn.prepareStatement(TERCERA_LLAMADA);
            declaracionGanancias.setObject(1, objeto);
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                segundoSeteo(result);
                list.add(atributo);
            }
        } else {
            declaracionGanancias = cn.prepareStatement(CUARTA_LLAMADA);
            declaracionGanancias.setObject(1, objeto);
            declaracionGanancias.setDate(2, fechaInicial);
            declaracionGanancias.setDate(3, fechaFinal);
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                segundoSeteo(result);
                list.add(atributo);
            }
        }
        login.Desconectar();
        return list;
    }
    
    private Atributos segundoSeteo(ResultSet result) throws SQLException{
        atributo = new Atributos();
        atributo.setTitulo_revista(result.getString("titulo_revista"));
        atributo.setEditor(result.getString("editor"));
        atributo.setTotal(result.getFloat("total_acumulado"));
        return atributo;
    }
}
