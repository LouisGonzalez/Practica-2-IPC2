package practica2.suscriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica2.clases.Conexion;
import practica2.clases.Llamadas;
import practica2.general.LlamadasGenerales;
/**
 *
 * @author luisGonzalez
 */
public class LlamadasSuscriptor {
    
    private static Connection cn;
    private static Conexion login;
    private Llamadas llamadas = new Llamadas();
    private static final String NUEVO_SUSCRIPTOR = "INSERT INTO Suscriptor (id, nombre_usuario, id_revista, fecha_suscripcion, estado_suscripcion, ultimo_pago) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String ESTADO = "ACTIVADA";
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
        declaracionInsertar.setString(5, ESTADO);
        declaracionInsertar.setDate(6, fecha_creacion);
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
    
    
    
    
    
    
    
    
    
    
    
}
