<%-- 
    Document   : ListadoRevistasAdmin
    Created on : 1/10/2019, 09:38:04 PM
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
            ArrayList<Revista> listar = dao.ListarRevistasAdmin();
        %>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <table>
                            <thead>
                                <tr>
                                    <th>Id:</th>
                                    <th>Editor:</th>
                                    <th>Titulos subidos:</th>
                                    <th>No. Suscriptores:</th>
                                    <th>Descripcion:</th>
                                    <th>Titulo revista:</th>
                                    <th>Cuota de suscripcion:</th>
                                    <th>No. Likes:</th>
                                    <th>Costo Mensual:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    if(listar.size() > 0){
                                        for(Revista listar2: listar){
                                            revista = listar2;
                                %>
                                <tr>
                                    <td><%=revista.getId()%></td>
                                    <td><a href="ControladorPerfil?usuario=<%=revista.getEditor()%>" target="blanck"><%=revista.getEditor()%></a></td>
                                    <td><%=revista.getTitulos_subidos()%></td>
                                    <td><%=revista.getNo_suscriptores()%></td>
                                    <td><%=revista.getDescripcion()%></td>
                                    <td><%=revista.getTitulo_revista()%></td>
                                    <td><%=revista.getCuota_suscripcion()%></td>
                                    <td><%=revista.getNo_likes()%></td>
                                    <td><%=revista.getCosto_mensual()%></td>
                                    <td>
                                        <form action="ControladorAdministrador?user=<%=revista.getTitulo_revista()%>" method="POST">
                                            <input type="submit" name="accion" value="Personalizar cuota mensual"/>
                                        </form>
                                    </td>
                                </tr>
                            </tbody>
                                    <%}
                                }%>    
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
