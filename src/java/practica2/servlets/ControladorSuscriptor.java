package practica2.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica2.clases.Llamadas;
import practica2.general.LlamadasGenerales;
import practica2.pagos.LlamadasPagos;
import practica2.revistas.Revista;
import practica2.suscriptor.LlamadasSuscriptor;
import practica2.suscriptor.Suscriptor;

/**
 *
 * @author luisGonzalez
 */
@MultipartConfig
public class ControladorSuscriptor extends HttpServlet {

    private Llamadas llamada = new Llamadas();
    private LlamadasGenerales llamadaGeneral = new LlamadasGenerales();
    private LlamadasSuscriptor llamadaSuscriptor = new LlamadasSuscriptor();
    private LlamadasPagos llamadaPago = new LlamadasPagos();
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
            int id = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            session.setAttribute("id", id);
            session.setAttribute("id_revista", llamadaGeneral.mostrarDatos(id, "id_revista", "Suscriptor", "id"));
            
        try {
            llamadaSuscriptor.revisarFecha(id, llamadaGeneral, request, response);
         } catch (SQLException | ParseException ex) {
            Logger.getLogger(ControladorSuscriptor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSuscriptor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        int cant_suscriptores;
        Revista revista = (Revista) request.getSession().getAttribute("revista");
        PrintWriter out = response.getWriter();
        String user = request.getParameter("nombre");
        String usuario = (String) request.getSession().getAttribute("nombre");
        try {                
            switch(accion){
                case "Suscribirme":
                    Suscriptor suscriptor = new Suscriptor();        
                    suscriptor.setNombre_usuario(user);
                    suscriptor.setId_revista(Integer.parseInt(llamada.mostrarDatos(revista.getTitulo_revista(), "id", "Revista", "titulo_revista").toString()));
                    suscriptor.setFecha_suscripcion(Date.valueOf(request.getParameter("fecha_suscripcion")));                                        
                    String verificador = (String) llamadaGeneral.mostrarDatos(suscriptor.getId_revista(), "bloqueo_suscripcion", "Bloqueos", "id_revista");                    
                    if(verificador.equals("desactivado")){
                        llamadaSuscriptor.crearSuscripcion(suscriptor.getFecha_suscripcion(), suscriptor.getId_revista(), suscriptor.getNombre_usuario());
                        cant_suscriptores = revista.getNo_suscriptores() + 1;
                        llamadaGeneral.modificarDatoUsuario("no_suscriptores", cant_suscriptores, revista.getTitulo_revista(), "Revista", "titulo_revista"); 
                        //efectua el primer pago    
                        llamadaPago.crearPago(user, suscriptor.getId_revista(), revista.getCuota_suscripcion(), suscriptor.getFecha_suscripcion());
                        llamada.comprobacionTipoUsuario(user, request, response);
                    } else {
                        out.println("<script>");
                        out.println("alert('Al parecer esta opcion esta bloqueada por el editor, porfavor intenta mas tarde');");
                        out.println("window.location.href = 'DescripcionRevista.jsp'");
                        out.println("</script>");                 
                    }                    
                    break; 
                case "Deseo pagar":
                    request.getRequestDispatcher("Pago.jsp").forward(request, response);
                    break;
                case "En otro momento":                    
                    llamada.comprobacionTipoUsuario(user, request, response);
                    break;
                case "Pagar":
                    Date ultimaFecha = (Date) request.getSession().getAttribute("ultimaFecha");
                    Date fechaPago = (Date) request.getSession().getAttribute("fechaPago");
                    Date fechaHoy = (Date) request.getSession().getAttribute("fechaActual");
                    Suscriptor suscrip = new Suscriptor();
                    suscrip.setUltimo_pago(Date.valueOf(request.getParameter("fechaPago")));
                    if(suscrip.getUltimo_pago().before(fechaPago)){
                        out.println("<script>");
                        out.println("alert('La fecha que ingresas es antes de que tu ultimo pago caducara');");
                        out.println("window.location.href = 'Pago.jsp'");
                        out.println("</script>");
                    } else if(suscrip.getUltimo_pago().after(fechaHoy)){
                        out.println("<script>");
                        out.println("alert('La fecha que ingresas es de un dia mayor al de hoy');");
                        out.println("window.location.href = 'Pago.jsp'");                        
                        out.println("</script>");                        
                    } else {
                        int id = (int) request.getSession().getAttribute("id");
                        int id_revista = (int) request.getSession().getAttribute("id_revista");
                        llamadaSuscriptor.totalDiasSinPagar(usuario, id, llamadaGeneral, suscrip.getUltimo_pago(), fechaPago, id_revista);
                        llamadaGeneral.modificarDatoUsuario("ultimo_pago", suscrip.getUltimo_pago(), id, "Suscriptor", "id");
                        llamada.comprobacionTipoUsuario(usuario, request, response);                           
                    }                     
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSuscriptor.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
