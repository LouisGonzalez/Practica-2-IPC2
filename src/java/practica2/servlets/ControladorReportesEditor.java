package practica2.servlets;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luisGonzalez
 */
public class ControladorReportesEditor extends HttpServlet {

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
        Date fechaInicial = null;
        Date fechaFinal = null;
        boolean verificador = true;
        session.setAttribute("fechaInicial", fechaInicial);
        session.setAttribute("fechaFinal", fechaFinal);
        session.setAttribute("verificar", verificador);
        request.getRequestDispatcher("ReporteComentariosEditor.jsp").forward(request, response);
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
        String revista = request.getParameter("revista");
        HttpSession session = request.getSession();
        boolean verificador = true;
        switch(accion){
            case "Buscar":                
                String primerFecha = request.getParameter("fechaInicial");
                String segundaFecha = request.getParameter("fechaFinal");
                Date fechaInicial = null;
                Date fechaFinal = null;
                if(primerFecha.equals("") && !segundaFecha.equals("")){
                    fechaInicial = null;
                    fechaFinal = Date.valueOf(segundaFecha);
                } else if(segundaFecha.equals("") && !primerFecha.equals("")){
                    fechaFinal = null;
                    fechaInicial = Date.valueOf(primerFecha);
                } else if(primerFecha.equals("") && segundaFecha.equals("")){
                    fechaInicial = null;
                    fechaFinal = null;
                } else {
                    fechaInicial = Date.valueOf(primerFecha);
                    fechaFinal = Date.valueOf(segundaFecha);                    
                }                
                if(request.getParameterValues("busqueda")!=null){
                    verificador = true;                    
                } else {
                    verificador = false;
                }
                session.setAttribute("listaRev", revista);
                session.setAttribute("verificar", verificador);
                session.setAttribute("fechaInicial", fechaInicial);
                session.setAttribute("fechaFinal", fechaFinal);
                request.getRequestDispatcher("ReporteComentariosEditor.jsp").forward(request, response);                             
                break;
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
