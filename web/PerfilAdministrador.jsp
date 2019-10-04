<%-- 
    Document   : PerfilAdministrador
    Created on : 1/10/2019, 03:38:41 PM
    Author     : luisitopapurey
--%>

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
        User: <%=user%>
    </head>
    <body>
        <STYLE>A {text-decoration: none;}</STYLE>
        <div class="container">
            <div class="tutorial">
                <ul>
                    <li>Agregar/Eliminar un administrador<i class="fa fa-angle-down">
                            <ul>
                                <li><a href="AgregarAdministrador.jsp">Agregar:</a></li>
                                <li><a href="EliminarAdministrador.jsp">Eliminar:</a></li>
                            </ul>                                 
                    </li>        
                    <li><a href="ControladorCuota">Nueva cuota global</a></li>
                    <li><a href="ListadoRevistasAdmin.jsp">Listado revistas</a></li>
                    <li><a href="NuevaCategoria.jsp">Nueva categoria</a></li>
                    <li>Reportes</li>
                </ul>
            </div>
        </div>
    </body>
</html>
