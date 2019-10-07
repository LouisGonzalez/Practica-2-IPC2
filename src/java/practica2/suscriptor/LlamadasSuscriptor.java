package practica2.suscriptor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica2.clases.Conexion;
import practica2.general.LlamadasGenerales;
/**
 *
 * @author luisGonzalez
 */
public class LlamadasSuscriptor {
    
    private static Connection cn;
    private static Conexion login;
    private static final String NUEVO_SUSCRIPTOR = "INSERT INTO Suscriptor (id, nombre_usuario, id_revista, fecha_suscripcion, ultimo_pago) VALUES(?, ?, ?, ?, ?)";
    private static final String ESTADO = "ACTIVADA";
    private static final String ACTUALIZACION_PAGOS = "UPDATE Pagos_suscriptor SET total_acumulado = ? WHERE id_revista = ? AND nombre_usuario = ?";
    private static final String TOTAL = "SELECT * FROM Pagos_suscriptor WHERE nombre_usuario = ? AND id_revista = ?";
    private static final String NUEVO_PAGO = "INSERT INTO Pagos_suscriptor (id, nombre_usuario, id_revista, total_acumulado, fecha_pago) VALUES (?, ?, ?, ?, ?)";
    private final int PLAZO_DIAS = 30;
    
    
    
    public static Connection obtenerConexion(){
        login= new Conexion();
        cn = login.getConnection();
        return cn;
    }
        
    public void crearSuscripcion(Date fecha_creacion, int id_revista, String usuario) throws SQLException, IOException{
        obtenerConexion();
        PreparedStatement declaracionInsertar = cn.prepareStatement(NUEVO_SUSCRIPTOR);
        declaracionInsertar.setInt(1, 0);
        declaracionInsertar.setString(2, usuario);
        declaracionInsertar.setInt(3, id_revista);
        declaracionInsertar.setDate(4, fecha_creacion);
        declaracionInsertar.setDate(5, fecha_creacion);
        declaracionInsertar.executeUpdate();
    }
    
    public void revisarFecha(int id, LlamadasGenerales llamadaGeneral, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException, ParseException{
            HttpSession session = request.getSession();
            java.util.Date fechaActual = new java.util.Date();
            Date ultimaFecha = (Date) llamadaGeneral.mostrarDatos(id, "ultimo_pago", "Suscriptor", "id");
            int dias = (int) ((fechaActual.getTime()-ultimaFecha.getTime())/86400000);
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(ultimaFecha);
            calendario.add(Calendar.DAY_OF_YEAR, PLAZO_DIAS);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String cambio = format.format(calendario.getTime());
            java.util.Date fechaMaxima = (java.util.Date) format.parse(cambio);  
            Date fechaPago = new Date(fechaMaxima.getTime());
            Date fechaHoy = new Date(fechaActual.getTime());
            session.setAttribute("fechaPago", fechaPago);
            session.setAttribute("fechaActual", fechaHoy);
            session.setAttribute("ultimaFecha", ultimaFecha);
            if(dias<=30){
                request.getRequestDispatcher("ListaTitulosSuscritos.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("ConfirmacionPago.jsp").forward(request, response);
            }        
    }
    
    public void totalDiasSinPagar(String user, int id, LlamadasGenerales llamadaGeneral, Date fechaPagar, Date ultimoPago, int id_revista) throws SQLException, IOException{
        int dias = (int) ((fechaPagar.getTime() - ultimoPago.getTime())/86400000);
        int mesesTotales = dias / 30;
        float nuevoTotal;
        int cuota = (int) llamadaGeneral.mostrarDatos(id_revista, "cuota_suscripcion", "Revista", "id");
        float totalAcumulado = totalAcumulado(user, id_revista);   
        System.out.println(totalAcumulado);
        System.out.println(cuota);
        System.out.println(mesesTotales); 
        if(mesesTotales == 0){
           obtenerConexion(); 
           PreparedStatement declaracionNPago = cn.prepareStatement(NUEVO_PAGO);
           declaracionNPago.setInt(1, 0);
           declaracionNPago.setString(2, user);
           declaracionNPago.setInt(3, id_revista);
           declaracionNPago.setFloat(4, cuota);
           declaracionNPago.setDate(5, fechaPagar);
           declaracionNPago.executeUpdate();
           login.Desconectar();
        } else if(mesesTotales > 0){
            obtenerConexion();
            nuevoTotal = (mesesTotales * cuota);     
            PreparedStatement declaracionNPago = cn.prepareStatement(NUEVO_PAGO);
            declaracionNPago.setInt(1, 0);
            declaracionNPago.setString(2, user);
            declaracionNPago.setInt(3, id_revista);
            declaracionNPago.setFloat(4, nuevoTotal);
            declaracionNPago.setDate(5, fechaPagar);
            declaracionNPago.executeUpdate();            
            login.Desconectar();
        }
    }
    
    public float totalAcumulado(String user, int id_revista) throws SQLException{
        obtenerConexion();
        float totalAcumulado = 0;
        System.out.println(user);
        System.out.println(id_revista);
        PreparedStatement declaracionTotal = cn.prepareStatement(TOTAL);
        declaracionTotal.setString(1, user);
        declaracionTotal.setInt(2, id_revista);
        ResultSet result2 = declaracionTotal.executeQuery();
        while(result2.next()){
            totalAcumulado = result2.getFloat("total_acumulado");
            System.out.println(totalAcumulado);
        }
        login.Desconectar();
        return totalAcumulado;
    }
    
    
    
    
    
    
    
    
    
    
    
}
