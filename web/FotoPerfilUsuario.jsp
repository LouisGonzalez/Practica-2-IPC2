<%-- 
    Document   : resultado
    Created on : 8/09/2019, 05:01:55 PM
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
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                        </li> 
                    </ul>
                </div>
            </div>
        </nav>
    </header><br><br>
        <STYLE>A {text-decoration: none;} </STYLE>
        <div class="container">
             <style>
                .image-upload > input{
                    display: none;
                }
                .image-upload img{
                    width: 130px;
                    cursor: pointer;
                }
            </style>
            <form action="Controlador" method="POST" enctype="multipart/form-data">
                <div class="image-upload">
                    <h1>Nombre de usuario: ${nombre}</h1><br>
                    <div>Click en la imagen para modificar tu foto de perfil</div>
                           
                        <label id="a" for="fotoPerfil">
                            <img id="imagen" src="anonimo.jpg" alt="Click para subir tu foto" title="Click para subir tu foto">        
                        </label>
                        <input id="fotoPerfil"  name="fotoPerfil" type="file" accept=".jpg,.png"/>
                    </div>
                    <input type ="submit" name="accion" class="btn btn-primary mb-2" value="Guardar">
                </div>    
            </form>                        
        </div>            
    </body>    
</html>
