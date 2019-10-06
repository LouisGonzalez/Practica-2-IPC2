<%-- 
    Document   : SubReporteGananciasEditor
    Created on : 6/10/2019, 04:04:23 AM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.reportes.Atributos"%>
<%@page import="practica2.reportes.CuartoReporte"%>
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
                            CuartoReporte reporte = new CuartoReporte();
                            Atributos atributo = new Atributos();
                            ArrayList<Atributos> listar = reporte.ListarGanancias(fechaInicial, fechaFinal, titulo);
                        %>
                        <table>
                            <thead>
                                <tr>
                                    <th>Nombre de usuario:</th>
                                    <th>Fecha de pago:</th>
                                    <th>Total:</th>
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
                                    <td><%=atributo.getTotal()%></td>
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