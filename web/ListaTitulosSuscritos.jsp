<%-- 
    Document   : ListaTitulosSuscritos
    Created on : 29/09/2019, 01:08:03 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.suscriptor.Suscriptor"%>
<%@page import="practica2.suscriptor.SuscriptorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            int id = (int) session.getAttribute("id_revista");
        %>
        id: <%=id%>
    </head>
    <body>
        <%
            SuscriptorDAO dao = new SuscriptorDAO();
            Suscriptor suscriptor = new Suscriptor();
            ArrayList<Suscriptor> listar = dao.ListarTitulos(id);
        %>
        <STYLE>A {text-decoration: none;}</STYLE>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <table>
                            <thead>
                                <tr>
                                    <th>Id: </th>
                                    <th>Fecha Creacion:</th>
                                    <th>Revista:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(listar.size() > 0){
                                    for(Suscriptor listar2: listar){
                                        suscriptor = listar2;
                                %>
                                <tr>
                                    <td><%=suscriptor.getId()%></td>
                                    <td><%=suscriptor.getFecha_creacion()%></td>
                                    <td><a href="MostrarPDF?id=<%=suscriptor.getId()%>" target="blank"><img src="pdf (1).jpeg" title="pdf" width="50" height="50"/></a></td>
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
