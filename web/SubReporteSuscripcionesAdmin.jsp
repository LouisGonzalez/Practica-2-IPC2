<%-- 
    Document   : SubReporteSuscripcionesAdmin
    Created on : 5/10/2019, 07:17:23 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.reportes.admin.AtributosAdmin"%>
<%@page import="practica2.reportes.admin.ReporteRevistasPopulares"%>
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
                            ReporteRevistasPopulares reporte = new ReporteRevistasPopulares();
                            AtributosAdmin atributo = new AtributosAdmin();
                            ArrayList<AtributosAdmin> listar = reporte.listarSuscripciones(fechaInicial, fechaFinal, titulo);
                        %>
                        <table>
                            <thead>
                                <tr>
                                    <th>Nombre de usuario:</th>
                                    <th>Fecha de suscripcion:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(listar.size() > 0){
                                    for(AtributosAdmin listar2: listar){
                                        atributo = listar2;
                                %>
                                <tr>
                                    <td><%=atributo.getNombre_usuario()%></td>
                                    <td><%=atributo.getFecha_suscripcion()%></td>
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
