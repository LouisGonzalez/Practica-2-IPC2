<%-- 
    Document   : PerfilUsuario
    Created on : 14/09/2019, 10:35:54 PM
    Author     : luisitopapurey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
        String user = (String)session.getAttribute("nombre");
        %>
        <p>UserName: <%=user%></p>
    </head>
    <body>
        <STYLE>A {text-decoration: none;} </STYLE>
            <div class="container">
                <div class="tutorial">
                    <ul>                
                        <li><a href="ControladorPerfil?usuario=<%=user%>">Ver Perfil</li>
                        <li><a href="PublicacionTitulos.jsp">Publicar mi primer revista</li>
                        <li><a href="ControladorCategoria">Suscribirme a una nueva revista</li>
                    <li>asfdds<i class="fa fa-angle-down"></i>
                    <ul>    
                        <li>asd</li>
                        <li>dsf</li>
                    </ul>
                </li>
                <li>asda<i class="fa fa-angle-down"></i>
                    <ul>
                        <li>CSS <span> 12 available</li>
                        <li>html <span> 9 available</li>                        
                    </ul>                    
                </li>               
            </ul>
            <div class="slider">
            <div class="information">               
                <h1>Este es el perfil de un usuario</h1>
        <p>UserName: <%=user%></p>
                <img src="ControladorImagen?us=${user}" width="250" height="230">
                </div>
            </div>
        </div>
        </div>    
    </body>
</html>
