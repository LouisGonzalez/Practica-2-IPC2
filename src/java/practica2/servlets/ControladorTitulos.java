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
import practica2.clases.Llamadas;
import practica2.revistas.Revista;

/**
 *
 * @author luisGonzalez
 */
public class ControladorTitulos extends HttpServlet {

    Llamadas llamada = new Llamadas();
    
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
        HttpSession session = request.getSession();
        Revista revista = new Revista();
        session.setAttribute("revista", revista);        
        String titulo = request.getParameter("titulo");
        revista.setDescripcion(llamada.mostrarDatos(titulo, "descripcion", "Revista", "titulo_revista").toString());
        revista.setCuota_suscripcion(Integer.parseInt(llamada.mostrarDatos(titulo, "cuota_suscripcion", "Revista", "titulo_revista").toString()));
        revista.setTitulo_revista(titulo);
        revista.setEditor(llamada.mostrarDatos(titulo, "editor", "Revista", "titulo_revista").toString());
        revista.setTitulos_subidos(Integer.parseInt(llamada.mostrarDatos(titulo, "titulos_subidos", "Revista", "titulo_revista").toString()));
        revista.setNo_suscriptores(Integer.parseInt(llamada.mostrarDatos(titulo, "no_suscriptores", "Revista", "titulo_revista").toString()));
        request.getRequestDispatcher("DescripcionRevista.jsp").forward(request, response);
        
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
        response.setContentType("text/html");
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorTitulos.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorTitulos.class.getName()).log(Level.SEVERE, null, ex);
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
