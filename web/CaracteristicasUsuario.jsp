<%-- 
    Document   : CaracteristicasUsuario
    Created on : 10/09/2019, 10:39:01 PM
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
                            <a class="nav-link" href="registro.jsp">Volver a la pagina principal
                                <span class="sr-only">(current)</span>
                            </a>
                        </li> 
                    </ul>
                </div>
            </div>
        </nav>
    </header><br><br>
        <STYLE>A {text-decoration: none;} </STYLE>
        <div class="container">
            <h1>Ingreso de caracteristicas:</h1>
            <form action="Controlador" method="POST">                            
                <textarea name="hobbie" rows="3" cols="100" spellcheck="true" placeholder="Hobbies"></textarea><br>
                <textarea name="temas" rows="3" cols="100" spellcheck="true" placeholder="Temas de interes"></textarea><br>
                <textarea name="descripcion" rows="5" cols="100" spellcheck="true" placeholder="Descripcion"></textarea><br>
                <textarea name="estudio"  rows="1" cols="100" spellcheck="true" placeholder="Lugar de estudio"></textarea><br>    
                <input type="submit" name="accion" class="btn btn-primary mb-2" value="Aceptar">
            </form>
        </div>  
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
