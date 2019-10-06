package practica2.reportes.admin;

import java.sql.*;
import java.util.ArrayList;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class SubReporteGanancias {
    
    private static Connection cn;
    private static Conexion login;
    private AtributosAdmin atributo;
    private static final String LLAMADA = "SELECT * FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE titulo_revista = ? AND fecha_pago <= ?";
    private static final String SEGUNDA_LLAMADA = "SELECT * FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE titulo_revista = ? AND fecha_pago >= ?";
    private static final String TERCERA_LLAMADA = "SELECT * FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE titulo_revista = ?";
    private static final String CUARTA_LLAMADA = "SELECT * FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE titulo_revista = ? AND fecha_pago >= ? AND fecha_pago <= ?";
    
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<AtributosAdmin> listarPagos(Date fechaInicial, Date fechaFinal, String titulo) throws SQLException{
        ArrayList<AtributosAdmin> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionPago = null;
        ResultSet result = null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionPago = cn.prepareStatement(LLAMADA);
            declaracionPago.setString(1, titulo);
            declaracionPago.setDate(2, fechaFinal);
            result = declaracionPago.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionPago = cn.prepareStatement(SEGUNDA_LLAMADA);
            declaracionPago.setString(1, titulo);
            declaracionPago.setDate(2, fechaInicial);
            result = declaracionPago.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else if(fechaInicial == null && fechaFinal == null){
            declaracionPago = cn.prepareStatement(TERCERA_LLAMADA);
            declaracionPago.setString(1, titulo);
            result = declaracionPago.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        } else {
            declaracionPago = cn.prepareStatement(CUARTA_LLAMADA);
            declaracionPago.setString(1, titulo);
            declaracionPago.setDate(2, fechaInicial);
            declaracionPago.setDate(3, fechaFinal);
            result = declaracionPago.executeQuery();
            while(result.next()){
                seteo(result);
                list.add(atributo);
            }
        }
        login.Desconectar();
        return list;
    }
    
    private AtributosAdmin seteo(ResultSet result) throws SQLException{
        atributo = new AtributosAdmin();
        atributo.setNombre_usuario(result.getString("nombre_usuario"));
        atributo.setTotal(result.getFloat("total_acumulado"));
        atributo.setFecha_pago(result.getDate("fecha_pago"));
        return atributo;
    }
    
}
