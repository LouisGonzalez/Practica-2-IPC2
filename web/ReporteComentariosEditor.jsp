<%-- 
    Document   : ReporteComentariosEditor
    Created on : 3/10/2019, 11:03:58 PM
    Author     : luisGonzalez
--%>

<%@page import="java.sql.*"%>
<%@page import="practica2.clases.Conexion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="practica2.reportes.Atributos"%>
<%@page import="practica2.reportes.ReportesEditor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            String user = (String) session.getAttribute("nombre");
            Date fechaInicial = (Date) session.getAttribute("fechaInicial");
            Date fechaFinal = (Date) session.getAttribute("fechaFinal");
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
                            <select name="revista">
                            <%
                                Conexion login = new Conexion();
                                Connection cn = login.getConnection();
                                String consulta = "SELECT * FROM Revista WHERE editor = ?";
                                PreparedStatement declaracionConsulta = cn.prepareStatement(consulta);
                                declaracionConsulta.setString(1, user);
                                ResultSet result = declaracionConsulta.executeQuery();
                                while(result.next()){
                                    %><option value="<%=result.getString("titulo_revista")%>"><%=result.getString("titulo_revista")%></option><%
                                }%>    
                            </select><br><br>
                            <label><input type="checkbox" name="busqueda" value="busqueda">Deseo ver todas las revistas</label><br><br>                        
                            <input type="submit" name="accion" value="Buscar">
                        </form>
                        <%  
                            ReportesEditor reportes = new ReportesEditor();
                            Atributos atributo = new Atributos();
                            ArrayList<Atributos> listar;
                            boolean verificador = (boolean) session.getAttribute("verificar");
                            if(verificador == false){
                                listar = reportes.ListarComentariosFiltrados(fechaInicial, fechaFinal, (String) session.getAttribute("listaRev"));
                            } else {
                                listar = reportes.ListarComentarios(fechaInicial, fechaFinal, user);                            
                            }
                        %>        
                        <table>
                            <thead>
                                <tr>
                                    <th>Titulo Revista:</th>
                                    <th>Nombre de usuario:</th>
                                    <th>Fecha del comentario:</th>
                                    <th>Comentario:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(listar.size() > 0){
                                    for(Atributos listar2 : listar){
                                       atributo = listar2;
                                %>
                                <tr>
                                    <td><%=atributo.getTitulo_revista()%></td>
                                    <td><%=atributo.getNombre_usuario()%></td>
                                    <td><%=atributo.getFecha_creado()%></td>
                                    <td><%=atributo.getComentario()%></td>
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
