<%-- 
    Document   : ReporteGananciasAdmin
    Created on : 5/10/2019, 01:34:21 AM
    Author     : luisGonzalez
--%>

<%@page import="practica2.reportes.Atributos"%>
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
            session.setAttribute("pagina", "SubReporteSuscripcionesAdmin.jsp");
        %>
    </head>
    <body>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <form action="ControladorReportesEditor" method="POST">
                            <label for="fechaInicial">Fecha Inicial:</label>
                            <input type="date" id="fechaInicial" name="fechaInicial"/> 
                            <label for="fechaFinal">Fecha Final:</label>
                            <input type="date" id="fechaFinal" name="fechaFinal"/><br><br>
                            <label><input type="checkbox" name="busqueda" value="busqueda">Deseo ver todas las revistas</label><br><br>                        
                            <input type="submit" name="accion" value="Buscar">
                        </form>  
                        <%
                            ReporteRevistasPopulares reporte = new ReporteRevistasPopulares();
                            AtributosAdmin atributo = new AtributosAdmin();
                            ArrayList<AtributosAdmin> listar = reporte.listarRevistasPopulares(fechaInicial, fechaFinal);
                        %>
                        <table>
                            <thead>
                                <tr>
                                    <th>Titulo Revista:</th>
                                    <th>Editor:</th>
                                    <th>No. Suscripciones:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(listar.size() > 0 && listar.size() < 5){
                                    for(AtributosAdmin listar2: listar){
                                        atributo = listar2;
                                %>
                                <tr>
                                    <td><%=atributo.getTitulo_revista()%></td>
                                    <td><%=atributo.getEditor()%></td>
                                    <td><%=atributo.getNo_suscriptores()%></td>
                                    <td><a href="SubControladorReportesAdmin?titulo=<%=atributo.getTitulo_revista()%>">Ver suscriptores</a></td>
                                </tr>
                                    <%}
                                } else if(listar.size() > 0 && listar.size() > 5) { 
                                    for(int x = 0; x<5; x++){
                                        atributo = listar.get(x);
                                %>
                                <tr>
                                    <td><%=atributo.getTitulo_revista()%></td>
                                    <td><%=atributo.getEditor()%></td>
                                    <td><%=atributo.getNo_suscriptores()%></td>
                                    <td><a href="SubControladorReportesAdmin?titulo=<%=atributo.getTitulo_revista()%>">Ver suscriptores</a></td>
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
