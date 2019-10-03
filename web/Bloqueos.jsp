<%-- 
    Document   : Bloqueos
    Created on : 2/10/2019, 10:07:31 PM
    Author     : luisGonzalez
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            String user = (String)session.getAttribute("nombre");
            int id = (int) session.getAttribute("idRevistas");
        %>
    </head>
    <body>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <form action="ControladorBloqueos" method="POST">
                            <input type="submit" name="accion" value="Bloquear Suscripciones"><br><br>
                            <input type="submit" name="accion" value="Bloquear Likes"><br><br>
                            <input type="submit" name="accion" value="Bloquear Comentarios"><br><br>                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
