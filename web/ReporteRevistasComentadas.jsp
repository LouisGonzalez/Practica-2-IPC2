<%-- 
    Document   : ReporteRevistasComentadas
    Created on : 5/10/2019, 01:35:27 AM
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
            session.setAttribute("pagina", "SubReporteComentariosAdmin.jsp");
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
                            ReporteRevistasComentadas reporte = new ReporteRevistasComentadas();
                            AtributosAdmin atributo = new AtributosAdmin();
                            ArrayList<AtributosAdmin> listar = reporte.listarRevistasComentadas(fechaInicial, fechaFinal);
                        %>
                        <table>
                            <thead>
                                <tr>
                                    <th>Titulo revista:</th>
                                    <th>Editor:</th>
                                    <th>No. comentarios:</th>
                                </tr>
                            </thead>
                            <%if(listar.size() > 0 && listar.size() < 5){
                                    for(AtributosAdmin listar2: listar){
                                        atributo = listar2;
                                %>
                                <tr>
                                    <td><%=atributo.getTitulo_revista()%></td>
                                    <td><%=atributo.getEditor()%></td>
                                    <td><%=atributo.getNo_comentarios()%></td>
                                    <td><a href="SubControladorReportesAdmin?titulo=<%=atributo.getTitulo_revista()%>">Ver comentarios</a></td>
                                </tr>
                                    <%}
                                } else if(listar.size() > 0 && listar.size() > 5){ 
                                    for(int x = 0; x<5; x++){
                                        atributo = listar.get(x);
                                %>
                                <tr>
                                    <td><%=atributo.getTitulo_revista()%></td>
                                    <td><%=atributo.getEditor()%></td>
                                    <td><%=atributo.getNo_comentarios()%></td>
                                    <td><a href="SubControladorReportesAdmin?titulo=<%=atributo.getTitulo_revista()%>">Ver comentarios</a></td>
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
