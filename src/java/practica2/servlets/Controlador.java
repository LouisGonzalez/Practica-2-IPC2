package practica2.servlets;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import javax.servlet.http.Part;
import practica2.clases.Llamadas;
import practica2.clases.Usuario;
import practica2.clases.UsuarioDAO;

/**
 *
 * @author luisGonzalez
 */
@MultipartConfig
public class Controlador extends HttpServlet {

    Usuario user = new Usuario();
    UsuarioDAO dao = new UsuarioDAO();
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");         
            out.println("<html>");              
            out.println("<head>");                  
            out.println("<title>Servlet Controlador</title>");            
            out.println("</head>");             
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
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
        try {
            String accion = request.getParameter("accion");
            HttpSession session = request.getSession();
            switch (accion) {
                case "Guardar":
                    Part part = request.getPart("fotoPerfil");
                    InputStream input = part.getInputStream();
                    user.setFoto(input);
                    dao.agregarFoto(user, user.getNombre_usuario());
                    request.getRequestDispatcher("CaracteristicasUsuario.jsp").forward(request, response);
                    break;
                case "Registrar":
                    user.setNombres(request.getParameter("nombres"));
                    user.setApellidos(request.getParameter("apellidos"));
                    user.setNombre_usuario(request.getParameter("username"));
                    user.setPassword(request.getParameter("pass"));
                    user.setTipo_usuario(request.getParameter("tipoUsuario"));
                    user.setEdad(Integer.parseInt(request.getParameter("edad")));
                    user.setNacimiento(Date.valueOf(request.getParameter("nacimiento")));
                    session.setAttribute("nombre", user.getNombre_usuario());
                    if(user.getNombres() == null || user.getApellidos() == null || user.getNombre_usuario() == null || user.getPassword() == null || user.getEdad() == 0 || user.getTipo_usuario() == null || user.getNacimiento() == null){
                        request.getRequestDispatcher("NuevaCuenta.jsp").forward(request, response);
                    } else {
                        dao.agregarUsuario(user, request, response); 
                    }
                    break;
                case "Entrar":  
                    session.setAttribute("nombre", request.getParameter("nombre"));                    
                    llamada.inicioSesion(request.getParameter("nombre"), request.getParameter("pass"), request, response);            
                    break;
                case "Aceptar":
                    user.setHobbies(request.getParameter("hobbie"));
                    user.setTemas_interes(request.getParameter("temas"));
                    user.setDescripcion(request.getParameter("descripcion"));
                    user.setLugar_estudio(request.getParameter("estudio"));
                    llamada.modificarDatoUsuario("hobbies", user.getHobbies(), (String)session.getAttribute("nombre"), "Usuarios", "nombre_usuario");
                    llamada.modificarDatoUsuario("temas_interes", user.getTemas_interes(), (String)session.getAttribute("nombre"), "Usuarios", "nombre_usuario");
                    llamada.modificarDatoUsuario("descripcion", user.getDescripcion(), (String)session.getAttribute("nombre"), "Usuarios", "nombre_usuario");
                    llamada.modificarDatoUsuario("lugar_estudio", user.getLugar_estudio(), (String)session.getAttribute("nombre"), "Usuarios", "nombre_usuario");
                    llamada.comprobacionTipoUsuario((String)session.getAttribute("nombre"), request, response);
                    break;
                default:
                    request.getRequestDispatcher("NuevaCuenta.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
