<%-- 
    Document   : SubReporteGananciasAdmin
    Created on : 6/10/2019, 01:18:02 AM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.reportes.admin.AtributosAdmin"%>
<%@page import="practica2.reportes.admin.SubReporteGanancias"%>
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
                            SubReporteGanancias reporte = new SubReporteGanancias();
                            AtributosAdmin atributo = new AtributosAdmin();
                            ArrayList<AtributosAdmin> listar = reporte.listarPagos(fechaInicial, fechaFinal, titulo);
                        %>
                        <table>
                            <thead>
                                <tr>
                                    <th>Nombre de usuario:</th>
                                    <th>Monto del pago:</th>
                                    <th>Fecha de pago:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(listar.size() > 0){
                                    for(AtributosAdmin listar2: listar){
                                        atributo = listar2;
                                %>
                                <tr>
                                    <td><%=atributo.getNombre_usuario()%></td>
                                    <td><%=atributo.getTotal()%></td>
                                    <td><%=atributo.getFecha_pago()%></td>
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
