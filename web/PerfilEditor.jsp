<%-- 
    Document   : PerfilEditor
    Created on : 14/09/2019, 10:35:43 PM
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
        <form action="ControladorPerfil" method="POST">
            <div class="container">
                <div class="tutorial">
                    <p>asfsdafasdf</p>
                    <ul>
                
                        <li><a href="ControladorPerfil?usuario=<%=user%>" target="blank">Ver Perfil</a></li>
                        <li><a href="EleccionRevista.jsp">Publicar una nueva revista</li>
                        <li><a href="ListaMisTitulos.jsp">Ver mis revistas subidas</li>
                        <li><a href="ListaRevistas.jsp">Suscribirme a una nueva revista</li>
                        <li><a href="ListaRevistasSuscritas.jsp">Ver mis revistas suscritas</li>
                        <li><a href="Bloqueos.jsp">Bloqueos</a></li>
                        <li>asfdds<i class="fa fa-angle-down">
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
                            <h1>Este es el perfil de un editor</h1>
                            <p>UserName: <%=user%></p>
                            <img src="ControladorImagen?us=${user}" width="250" height="230">
                        </div>
                    </div>
                </div>
            </div>    
        </form>
    </body>
</html>




