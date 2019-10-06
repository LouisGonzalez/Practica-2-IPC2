<%-- 
    Document   : SubReporteSuscripcionesEditor
    Created on : 6/10/2019, 03:06:08 AM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.reportes.Atributos"%>
<%@page import="practica2.reportes.SegundoReporte"%>
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
                            SegundoReporte reporte = new SegundoReporte();
                            Atributos atributo = new Atributos();
                            ArrayList<Atributos> listar = reporte.ListarSuscripciones(fechaInicial, fechaFinal, titulo);
                        %>
                        <table>
                            <thead>
                                <tr>
                                    <th>Nombre de Usuario:</th>
                                    <th>Fecha de suscripcion:</th>
                                    <th>Ultimo pago:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(listar.size() > 0){
                                    for(Atributos listar2: listar){
                                        atributo = listar2;
                                %>
                                <tr>
                                    <td><%=atributo.getNombre_usuario()%></td>
                                    <td><%=atributo.getFecha_creado()%></td>
                                    <td><%=atributo.getUltimo_pago()%></td>
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
