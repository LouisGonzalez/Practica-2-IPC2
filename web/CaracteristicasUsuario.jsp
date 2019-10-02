<%-- 
    Document   : CaracteristicasUsuario
    Created on : 10/09/2019, 10:39:01 PM
    Author     : luisitopapurey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
         <p>Isadf: <strong>${nombre}</strong></p>
    </head>
    <body>
        <STYLE>A {text-decoration: none;} </STYLE>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <img src="ControladorImagen?us=${nombre}" width="250" height="230">
                        <form action="Controlador" method="POST">
                            <%@include file="ingreso-caracteristicas.html"%>
                            <input type="submit" name="accion" value="Aceptar">
                        </form>
                    </div>
                </div>
            </div>
        </div>                    
    </body>
</html>
