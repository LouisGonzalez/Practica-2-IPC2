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

/**
 *
 * @author luisGonzalez
 */
public class ControladorAdministrador extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorAdministrador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorAdministrador at " + request.getContextPath() + "</h1>");
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
        String user = request.getParameter("user");
        HttpSession session = request.getSession();
        try {
            
            switch(accion){
                case "Agregar":        
                    llamadaGeneral.modificarDatoUsuario("tipo_usuario", "Administrador", user, "Usuarios", "nombre_usuario");
                    System.out.println("has agregado un nuevo administrador");
                    request.getRequestDispatcher("PerfilAdministrador.jsp").forward(request, response);
                    break;
                case "Eliminar":
                    llamadaGeneral.modificarDatoUsuario("tipo_usuario", "usuario", user, "Usuarios", "nombre_usuario");
                    System.out.println("se ha eliminado al administrador con exito");
                    request.getRequestDispatcher("PerfilAdministrador.jsp").forward(request, response);
                    break;
                case "Personalizar cuota mensual":
                    session.setAttribute("titulo", user);
                    request.getRequestDispatcher("PersonalizacionCuota.jsp").forward(request, response);
                    break;
                case "Guardar Cambios":
                    llamadaGeneral.modificarDatoUsuario("costo_mensual", request.getParameter("cuotaLocal"), user, "Revista", "titulo_revista");
                    request.getRequestDispatcher("ListadoRevistasAdmin.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
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
