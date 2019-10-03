package practica2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica2.general.LlamadasGenerales;
import practica2.likes.LlamadasLikes;

/**
 *
 * @author luisGonzalez
 */
public class ControladorLike extends HttpServlet {

    LlamadasGenerales llamadaGeneral = new LlamadasGenerales();
    LlamadasLikes llamadaLike = new LlamadasLikes();
    private static final String BUSQUEDA = "SELECT * FROM Likes WHERE nombre_usuario = ? AND id_revista = ?";
    
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorLike</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorLike at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        HttpSession session = request.getSession(); 
        int id = Integer.parseInt(request.getParameter("id"));
        PrintWriter out = response.getWriter();
        boolean verificador;
        try {
            int id_revista = (int) llamadaGeneral.mostrarDatos(id, "id_revista", "Suscriptor", "id");
            String user = (String)session.getAttribute("nombre");
            int likesActuales = (int) llamadaGeneral.mostrarDatos(id_revista, "no_likes", "Revista", "id");
            int actualizacion;            
            switch(accion){
                case "Me gusta":
                    verificador = llamadaLike.siExiste(BUSQUEDA, user, id_revista); 
                    String comprobador = (String) llamadaGeneral.mostrarDatos(id_revista, "bloqueo_likes", "Bloqueos", "id_revista");                    
                    if(comprobador.equals("desactivado")){
                        if(verificador == false){                        
                            actualizacion = likesActuales + 1;
                            llamadaGeneral.modificarDatoUsuario("no_likes", actualizacion, id_revista, "Revista", "id");
                            llamadaLike.crearLike(user, id_revista);
                            request.getRequestDispatcher("ListaRevistasSuscritas.jsp").forward(request, response);
                        } else {
                            actualizacion = likesActuales - 1;
                            llamadaGeneral.modificarDatoUsuario("no_likes", actualizacion, id_revista, "Revista", "id");
                            llamadaLike.eliminarLike(user, id_revista);
                            request.getRequestDispatcher("ListaRevistasSuscritas.jsp").forward(request, response);
                        }
                    } else {
                        out.println("<script>");
                        out.println("alert('Al parecer esta opcion esta bloqueada por el editor, porfavor intenta mas tarde');");
                        out.println("window.location.href = 'ListaRevistasSuscritas.jsp'");
                        out.println("</script>");                 
                    }                        
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorLike.class.getName()).log(Level.SEVERE, null, ex);
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
