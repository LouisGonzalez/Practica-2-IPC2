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
            /*Date ultimaFecha = (Date) llamadaGeneral.mostrarDatos(id, "ultimo_pago", "Suscriptor", "id");
            int dias = (int) ((fechaActual.getTime()-ultimaFecha.getTime())/86400000);*/
            /*if(dias<=30){
                //request.getRequestDispatcher("ListaTitulosSuscritos.jsp").forward(request, response);
                request.getRequestDispatcher("ListaTitulosSuscritos.jsp").forward(request, response);
                } else {
                System.out.println("No has pagado tu suscripcion perro");
            }*/
            
            
            
            /*if(fechaActual.after(ultimaFecha)){
                System.out.println("fecha actual es mayor a la ultima fecha de pago");
            } else if(fechaActual.before(ultimaFecha)){
                System.out.println("fecha actual es menor a la ultima fecha de pago");
            }*/
            
            
            
            
            
            /*Calendar calendario = Calendar.getInstance();
            calendario.setTime(ultimaFecha);
            calendario.add(Calendar.DAY_OF_YEAR, PLAZO_DIAS);
            System.out.println(calendario.getTime());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String formatted = format.format(calendario.getTime());
            System.out.println(formatted);*/
            
            
            
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
        try {                
            switch(accion){
                case "Suscribirme":
                    Suscriptor suscriptor = new Suscriptor();        
                    suscriptor.setNombre_usuario(user);
                    suscriptor.setId_revista(Integer.parseInt(llamada.mostrarDatos(revista.getTitulo_revista(), "id", "Revista", "titulo_revista").toString()));
                    suscriptor.setFecha_suscripcion(Date.valueOf(request.getParameter("fecha_suscripcion")));
                    llamadaSuscriptor.crearSuscripcion(suscriptor.getFecha_suscripcion(), suscriptor.getId_revista(), suscriptor.getNombre_usuario());
                    cant_suscriptores = revista.getNo_suscriptores() + 1;
                    llamadaGeneral.modificarDatoUsuario("no_suscriptores", cant_suscriptores, revista.getTitulo_revista(), "Revista", "titulo_revista");
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
                        llamadaGeneral.modificarDatoUsuario("ultimo_pago", suscrip.getUltimo_pago(), id, "Suscriptor", "id");
                        llamada.comprobacionTipoUsuario(user, request, response);                                            
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
