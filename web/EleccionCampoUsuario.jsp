<%-- 
    Document   : EleccionCampoUsuario
    Created on : 21/09/2019, 02:12:46 PM
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
    </head>
    <body>
        <div class="container">
            <div class="tutorial">
                <h1>Elige el campo que deseas cambiar:</h1>
                <div class="slider">
                    <div class="information">
                        <form action="ControladorPerfil" method="POST">
                            <label><input type="radio" name="datos" value="nombres">Nombre.</label>
                            <label><input type="radio" name="datos" value="apellidos">Apellidos.</label><br><br>
                            <label><input type="radio" name="datos" value="nombre_usuario">UserName.</label>
                            <label><input type="radio" name="datos" value="password">Password.</label><br><br>
                            <label><input type="radio" name="datos" value="lugar_estudio">Lugar de estudio.</label>
                            <label><input type="radio" name="datos" value="hobbies">Hobbies.</label><br><br>
                            <label><input type="radio" name="datos" value="temas_interes">Temas de Interes.</label>
                            <label><input type="radio" name="datos" value="descripcion">Descripcion.</label><br><br>
                            <input type="submit" name="accion" value="Aceptar">            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
