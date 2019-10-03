<%-- 
    Document   : ListaRevistas
    Created on : 24/09/2019, 09:44:25 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.revistas.Revista"%>
<%@page import="practica2.revistas.RevistaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            String user = (String)session.getAttribute("nombre");
            int id = (int) session.getAttribute("idMiRevista");
        %>    
    </head>
    <body>
        <%
            RevistaDAO dao = new RevistaDAO();
            Revista revista = new Revista();
            ArrayList<Revista> listar = dao.ListarMisRevistas(id);
        %>    
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <table>
                            <thead>
                                <tr>
                                    <th>Fecha Creacion</th>
                                    <th>Revista</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(listar.size() > 0){
                                    for(Revista listar2 : listar){
                                        revista = listar2;                                 
                                %>
                                <tr>
                                    <td><%=revista.getFecha_creacion()%></td>
                                    <td>
                                        <a href="MostrarPDF?id=<%=revista.getId()%>" target="blank"><img src="pdf (1).jpeg" title="pdf" width="50" height="50"/></a>                                          
                                    </td>
                                </tr>    
                                    <%} 
                                }%>
                            </tbody>
                        </table>
                    </div>    
                </div>
            </div>
        </div>
    </body>
</html>
