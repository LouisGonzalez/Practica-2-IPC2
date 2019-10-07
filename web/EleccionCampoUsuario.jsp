<%-- 
    Document   : EleccionCampoUsuario
    Created on : 21/09/2019, 02:12:46 PM
    Author     : luisGonzalez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8"> 
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimun-scale=1.0">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/estilosBoots.css" rel="stylesheet">
        <%
            String user = (String)session.getAttribute("nombre");
        %>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
                <div class="container">
                    <a class="navbar-brand" href="#">
                        <img src="ControladorImagen?us=<%=user%>" alt="" class="rounded-circle" width="50">
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarResponsive">
                        <ul class="navbar-nav">                   
                            <li class="nav-item active">
                                <a class="nav-link" href="ControladorPerfil?usuario=<%=user%>"><h5><%=user%></h5></a>         
                            </li>
                        </ul>
      
                        <ul class="navbar-nav ml-auto">        
                            <li class="nav-item active">
                                <a class="nav-link" href="ControladorRedireccion">Home
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Opciones
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="FotoPerfilUsuario.jsp">Cambiar mi foto</a>
                                    <a class="dropdown-item" href="ControladorSesion">Salir</a>
                                </div>
                            </li> 
     
                        </ul>
                    </div>
                </div>
            </nav> 
        </header> 
            <div class="container"><br><br>            
                <h1>Elige el campo que deseas cambiar:</h1>
                <form action="ControladorPerfil" method="POST">
                    <div class="form-row">
                        <label><input type="radio" name="datos" value="nombres" class="form-control">Nombre.</label>
                        <label><input type="radio" name="datos" value="apellidos" class="form-control">Apellidos.</label><br><br>
                        <label><input type="radio" name="datos" value="nombre_usuario" class="form-control">UserName.</label>
                        <label><input type="radio" name="datos" value="password" class="form-control">Password.</label><br><br>
                        <label><input type="radio" name="datos" value="lugar_estudio" class="form-control">Lugar de estudio.</label>
                        <label><input type="radio" name="datos" value="hobbies" class="form-control">Hobbies.</label><br><br>
                        <label><input type="radio" name="datos" value="temas_interes" class="form-control">Temas de Interes.</label>
                        <label><input type="radio" name="datos" value="descripcion" class="form-control">Descripcion.</label><br><br>
                    </div>
                    <input type="submit" name="accion" value="Aceptar" class="btn btn-primary mb-2">            
                </form>
            </div>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
