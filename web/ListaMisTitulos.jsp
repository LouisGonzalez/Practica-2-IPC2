<%-- 
    Document   : ListaMisTitulos
    Created on : 2/10/2019, 10:26:25 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.revistas.Revista"%>
<%@page import="practica2.revistas.RevistaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            String user = (String) session.getAttribute("nombre");
        %>
    </head>
    <body>
        <%
            RevistaDAO dao = new RevistaDAO();
            Revista revista = new Revista();
            ArrayList<Revista> listar = dao.ListarMisTitulos(user);
        %>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <table>
                            <thead>
                                <tr>
                                    <th>Titulo Revista:</th>
                                    <th>No. Titulos subidos:</th>
                                    <th>No. Suscriptores:</th>
                                    <th>Descripcion:</th>
                                    <th>Cuota de suscripcion:</th>
                                    <th>No. likes:</th>
                                    <th>Costo mensual:</th>                                    
                                </tr>
                            </thead>
                            <tbody>
                                <% if(listar.size() > 0){
                                    for(Revista listar2: listar){
                                        revista = listar2;                                    
                                %>
                                <tr>
                                    <td><%=revista.getTitulo_revista()%></td>
                                    <td><%=revista.getTitulos_subidos()%></td>
                                    <td><%=revista.getNo_suscriptores()%></td>
                                    <td><%=revista.getDescripcion()%></td>
                                    <td><%=revista.getCuota_suscripcion()%></td>
                                    <td><%=revista.getNo_likes()%></td>
                                    <td><%=revista.getCosto_mensual()%></td>
                                    <td><a href="ControladorPDF?idpp=<%=revista.getId()%>" target="blank">Ver mis revistas</a></td>
                                    <td><a href="ControladorBloqueos?id=<%=revista.getId()%>" target="blank">Bloqueos</a></td>
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
