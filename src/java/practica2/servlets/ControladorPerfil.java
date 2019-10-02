package practica2.servlets;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import practica2.clases.Llamadas;
import practica2.clases.Usuario;
import practica2.general.LlamadasGenerales;
import practica2.revistas.Revista;

/**
 *
 * @author luisGonzalez
 */
@MultipartConfig
public class ControladorPerfil extends HttpServlet {
    
    Llamadas llamada = new Llamadas();
    Usuario user = new Usuario();
    Revista revista = new Revista();
    LlamadasGenerales llamadaGeneral = new LlamadasGenerales();
    
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
        //redirecciona al perfil de un usuario
        HttpSession session = request.getSession();        
        Usuario use = new Usuario();
        session.setAttribute("use", use);
        String usuario = request.getParameter("usuario");
        use.setNombres(llamada.mostrarDatos(usuario, "nombres", "Usuarios", "nombre_usuario").toString());
        use.setApellidos(llamada.mostrarDatos(usuario, "apellidos", "Usuarios", "nombre_usuario").toString());
        use.setTipo_usuario(llamada.mostrarDatos(usuario, "tipo_usuario", "Usuarios", "nombre_usuario").toString());
        use.setNacimiento((Date)llamada.mostrarDatos(usuario, "fecha_nacimiento", "Usuarios", "nombre_usuario"));
        use.setLugar_estudio(llamada.mostrarDatos(usuario, "lugar_estudio", "Usuarios", "nombre_usuario").toString());
        use.setHobbies(llamada.mostrarDatos(usuario, "hobbies", "Usuarios", "nombre_usuario").toString());
        use.setTemas_interes(llamada.mostrarDatos(usuario, "temas_interes", "Usuarios", "nombre_usuario").toString());
        use.setDescripcion(llamada.mostrarDatos(usuario, "descripcion", "Usuarios", "nombre_usuario").toString());
        use.setNombre_usuario(usuario);
        request.getRequestDispatcher("InfoPerfil.jsp").forward(request, response);
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
            Logger.getLogger(ControladorPerfil.class.getName()).log(Level.SEVERE, null, ex);
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
        String tip = (String)request.getSession().getAttribute("tipo");
        String user = (String)request.getSession().getAttribute("nombre");
        HttpSession session = request.getSession();
        try {          
            switch(accion){
                case "Aceptar":
                    session.setAttribute("tipo", request.getParameter("datos"));
                    request.getRequestDispatcher("ModificacionDatosUsuario.jsp").forward(request, response);
                    break;
                case "Guardar":      
                    llamada.modificarDatoUsuario(tip, request.getParameter("valor"), user, "Usuarios", "nombre_usuario");
                    llamada.comprobacionTipoUsuario(user, request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
                Logger.getLogger(ControladorPerfil.class.getName()).log(Level.SEVERE, null, ex);
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
