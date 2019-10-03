package practica2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica2.comentarios.LlamadasComentarios;
import practica2.general.LlamadasGenerales;

/**
 *
 * @author luisGonzalez
 */
@MultipartConfig
public class ControladorComentarios extends HttpServlet {

    private LlamadasComentarios llamadaComentario = new LlamadasComentarios();
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
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();        
        try {        
            int id_revista = (int) llamadaGeneral.mostrarDatos(id, "id_revista", "Suscriptor", "id");
            session.setAttribute("id_revista", id_revista);
            request.getRequestDispatcher("Comentarios.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorComentarios.class.getName()).log(Level.SEVERE, null, ex);
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
        int id = (int) session.getAttribute("id_revista");
        PrintWriter out = response.getWriter();
        String verificador;
        try {
            String user = (String) session.getAttribute("nombre");
            switch(accion){
                case "Enviar": 
                verificador = (String) llamadaGeneral.mostrarDatos(id, "bloqueo_comentarios", "Bloqueos", "id_revista");                
                if(verificador.equals("desactivado")){
                    llamadaComentario.crearComentario(user, id, request.getParameter("comentario")); 
                    request.getRequestDispatcher("Comentarios.jsp").forward(request, response);                
                } else {
                    out.println("<script>");
                    out.println("alert('Al parecer esta opcion esta bloqueada por el editor, porfavor intenta mas tarde');");
                    out.println("window.location.href = 'Comentarios.jsp'");
                    out.println("</script>"); 
                }    
                    
                    
                    
                break;                
            }
            
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorComentarios.class.getName()).log(Level.SEVERE, null, ex);
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
