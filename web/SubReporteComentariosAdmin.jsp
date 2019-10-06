<%-- 
    Document   : SubReporteComentariosAdmin
    Created on : 5/10/2019, 07:17:41 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.reportes.admin.AtributosAdmin"%>
<%@page import="practica2.reportes.admin.ReporteRevistasComentadas"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="estiloPerfil.css" rel="stylesheet">
        <title>JSP Page</title>
        <%
            String user = (String) session.getAttribute("nombre");
            Date fechaInicial = (Date) session.getAttribute("fechaInicial");
            Date fechaFinal = (Date) session.getAttribute("fechaFinal");
            String titulo = (String) session.getAttribute("titulo_revista");
        %>
    </head>
    <body>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <%
                            ReporteRevistasComentadas reporte = new ReporteRevistasComentadas();
                            AtributosAdmin atributo = new AtributosAdmin();
                            ArrayList<AtributosAdmin> listar = reporte.listarComentarios(fechaInicial, fechaFinal, titulo);
                        %>
                        <table>
                            <thead>
                                <tr>
                                    <th>Nombre de usuario:</th>
                                    <th>Comentario:</th>
                                    <th>Fecha de comentario:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(listar.size() > 0){
                                    for(AtributosAdmin listar2: listar){
                                        atributo = listar2;
                                %>
                                <tr>
                                    <td><%=atributo.getNombre_usuario()%></td>
                                    <td><%=atributo.getComentario()%></td>
                                    <td><%=atributo.getFecha_comentario()%></td>
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
