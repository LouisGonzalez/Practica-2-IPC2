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
import practica2.bloqueos.LlamadasBloqueos;
import practica2.general.LlamadasGenerales;

/**
 *
 * @author luisGonzalez
 */
public class ControladorBloqueos extends HttpServlet {
    
    private LlamadasBloqueos llamadaBloqueo = new LlamadasBloqueos();
    private LlamadasGenerales llamadaGeneral = new LlamadasGenerales();
    
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
        int idRevista = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        session.setAttribute("idRevistas", idRevista);
        request.getRequestDispatcher("Bloqueos.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("idRevistas");
        String accion = request.getParameter("accion");
        boolean verificador;
        try {
            switch(accion){
                case "Bloquear Suscripciones":        
                    verificador = llamadaBloqueo.estaBloqueado(id, "bloqueo_suscripcion");
                    if(verificador == false){
                        llamadaGeneral.modificarDatoUsuario("bloqueo_suscripcion", "activado", id, "Bloqueos", "id_revista");
                        request.getRequestDispatcher("Bloqueos.jsp").forward(request, response);
                    } else if(verificador == true){
                        llamadaGeneral.modificarDatoUsuario("bloqueo_suscripcion", "desactivado", id, "Bloqueos", "id_revista");
                        request.getRequestDispatcher("Bloqueos.jsp").forward(request, response);
                    }
                    break;
                case "Bloquear Likes":
                    verificador = llamadaBloqueo.estaBloqueado(id, "bloqueo_likes");
                    if(verificador == false){
                        llamadaGeneral.modificarDatoUsuario("bloqueo_likes", "activado", id, "Bloqueos", "id_revista");
                        request.getRequestDispatcher("Bloqueos.jsp").forward(request, response);
                    } else if(verificador == true){
                        llamadaGeneral.modificarDatoUsuario("bloqueo_likes", "desactivado", id, "Bloqueos", "id_revista");
                        request.getRequestDispatcher("Bloqueos.jsp").forward(request, response);
                    }
                    break;
                case "Bloquear Comentarios":
                    verificador = llamadaBloqueo.estaBloqueado(id, "bloqueo_comentarios");
                    if(verificador == false){
                        llamadaGeneral.modificarDatoUsuario("bloqueo_comentarios", "activado", id, "Bloqueos", "id_revista");
                        request.getRequestDispatcher("Bloqueos.jsp").forward(request, response);
                    } else if(verificador == true){
                        llamadaGeneral.modificarDatoUsuario("bloqueo_comentarios", "desactivado", id, "Bloqueos", "id_revista");
                        request.getRequestDispatcher("Bloqueos.jsp").forward(request, response);
                    }                    
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBloqueos.class.getName()).log(Level.SEVERE, null, ex);
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
