package practica2.servlets;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import practica2.bloqueos.LlamadasBloqueos;
import practica2.categorias.LlamadasCategorias;
import practica2.clases.Llamadas;
import practica2.general.LlamadasGenerales;
import practica2.revistas.LlamadasRevista;
import practica2.revistas.Revista;

/**
 *
 * @author luisGonzalez
 */
@MultipartConfig
public class ControladorPDF extends HttpServlet {

    private Revista revistas = new Revista();
    private LlamadasRevista llamadas = new LlamadasRevista(); 
    private Llamadas nuevaLlamada = new Llamadas();
    private LlamadasGenerales general = new LlamadasGenerales();
    private LlamadasBloqueos llamadaBloqueo = new LlamadasBloqueos();
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
        int idRevista = Integer.parseInt(request.getParameter("idpp"));
        HttpSession session = request.getSession();
        session.setAttribute("idMiRevista", idRevista);
        request.getRequestDispatcher("ListaMisRevistas.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");  
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
        try{
            String accion = request.getParameter("accionar");
            HttpSession session = request.getSession();
            switch(accion){
                case "Guardar":
                    revistas.setTitulo_revista(request.getParameter("titulo"));
                    revistas.setDescripcion(request.getParameter("descripcion"));
                    revistas.setEditor((String)session.getAttribute("nombre"));
                    revistas.setCuota_suscripcion(Integer.parseInt(request.getParameter("cuota")));
                    revistas.setFecha(Date.valueOf(request.getParameter("creacionRev")));
                    llamadas.crearRevista(revistas.getEditor(), revistas.getDescripcion(), revistas.getTitulo_revista(), revistas.getCuota_suscripcion(), revistas.getFecha());
                    session.setAttribute("tituloRev", request.getParameter("titulo"));                    
                    revistas.setId((int) general.mostrarDatos(revistas.getTitulo_revista(), "id", "Revista", "titulo_revista"));
                    llamadaBloqueo.crearBloqueo(revistas.getId());
                    llamadaCategoria.crearCategoria(revistas.getId(), request.getParameter("categoria"));
                    request.getRequestDispatcher("PublicacionRevistas.jsp").forward(request, response);
                    break;
                case "Guardar Revista":
                    Part part = request.getPart("revista");
                    InputStream input = part.getInputStream();
                    revistas.setPdf(input);
                    revistas.setFecha_creacion(Date.valueOf(request.getParameter("creacion")));
                    revistas.setEditor((String)session.getAttribute("nombre"));
                    revistas.setTitulo_revista((String)session.getAttribute("tituloRev"));
                    llamadas.crearTitulo(revistas.getEditor(), revistas.getFecha_creacion(), revistas.getPdf(), revistas.getTitulo_revista());
                    nuevaLlamada.comprobacionTipoUsuario(revistas.getEditor(), request, response);                                       
                    break;
                case "Siguiente":
                    if(request.getParameterValues("nuevo")!=null){
                        request.getRequestDispatcher("PublicacionTitulos.jsp").forward(request, response);
                    } else {
                        session.setAttribute("tituloRev", request.getParameter("eleccion"));
                        request.getRequestDispatcher("PublicacionRevistas.jsp").forward(request, response);                    
                    }
                    break;
                case "Descargar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    System.out.println(id);
                default:
                    break;
            }
        } catch(SQLException ex){
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
