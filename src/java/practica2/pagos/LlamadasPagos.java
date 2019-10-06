package practica2.pagos;

import java.sql.*;
import practica2.clases.Conexion;

/**
 *
 * @author luisGonzalez
 */
public class LlamadasPagos {
    
    private static Connection cn;
    private static Conexion login;
    private static final String NUEVO_PAGO = "INSERT INTO Pagos_suscriptor (id, nombre_usuario, id_revista, total_acumulado, fecha_pago) VALUES (?, ?, ?, ?, ?)";
    
    private static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public void crearPago(String nombre_usuario, int id_revista, float pago, Date fecha) throws SQLException{
        obtenerConexion();
        PreparedStatement declaracionPago = cn.prepareStatement(NUEVO_PAGO);
        declaracionPago.setInt(1, 0);
        declaracionPago.setString(2, nombre_usuario);
        declaracionPago.setInt(3, id_revista);
        declaracionPago.setFloat(4, pago);
        declaracionPago.setDate(5, fecha);
        declaracionPago.executeUpdate();
        login.Desconectar();
    }
    
}
