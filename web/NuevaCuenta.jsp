<%-- 
    Document   : NuevaCuenta
    Created on : 8/09/2019, 09:47:09 PM
    Author     : luisitopapurey
--%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario" class="practica2.clases.Usuario"></jsp:useBean>
<jsp:useBean id="llamadas" class="practica2.clases.Llamadas"></jsp:useBean> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title> 
        <link href="estiloPerfil.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">            
            <div class="tutorial">
                <h1>Registrate:</h1>
                <div class="slider">
                    <div class="information">                
                        <form action="Controlador" method="POST">
                            <%@include file="ingreso-usuarios.html"%>
                            <label><input type="radio" name="tipoUsuario" value="usuario">Usuario</label><br>
                            <label><input type="radio" name="tipoUsuario" value="editor">Editor</label><br><br>
                            <input class="boton" type ="submit" name="accion" value='Registrar'>
                        </form>
                    </div>
                </div> 
            </div>
        </div>                    
    </body>
</html>
