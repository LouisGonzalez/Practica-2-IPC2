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
import practica2.categorias.LlamadasCategorias;

/**
 *
 * @author luisGonzalez
 */
public class ControladorCategoria extends HttpServlet {

    private LlamadasCategorias llamadaCategoria = new LlamadasCategorias();
    
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
        HttpSession session = request.getSession();
        boolean verificador = true;
        session.setAttribute("verificador", verificador);
        request.getRequestDispatcher("ListaRevistas.jsp").forward(request, response);
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
        String categoria = request.getParameter("categoria");
        HttpSession session = request.getSession();
        boolean verificador = true;
        try {            
            switch(accion){
                case "Crear":        
                llamadaCategoria.crearCategoriaAdmin(categoria);  
                request.getRequestDispatcher("NuevaCategoria.jsp").forward(request, response);
                break;
                case "Buscar":
                if(request.getParameterValues("busqueda")!=null){
                    verificador = true;
                    session.setAttribute("verificador", verificador);
                    session.setAttribute("eleccion", request.getParameter("eleccion"));
                    request.getRequestDispatcher("ListaRevistas.jsp").forward(request, response);
                } else {
                    verificador = false;                    
                    session.setAttribute("verificador", verificador);
                    session.setAttribute("eleccion", request.getParameter("eleccion"));
                    request.getRequestDispatcher("ListaRevistas.jsp").forward(request, response);
                }            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCategoria.class.getName()).log(Level.SEVERE, null, ex);
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
