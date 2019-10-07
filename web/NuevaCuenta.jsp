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
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimun-scale=1.0">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/estilosBoots.css" rel="stylesheet">
        <title>JSP Page</title> 
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
    <div class="container">            
        <h1>Registrate</h1>
            <form action="Controlador" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <input class="form-control" id="nombres" name="nombres" placeholder="Nombres" required/>
                    </div>
                    <div class="form-group col-md-4">
                        <input class="form-control" id="apellidos" name="apellidos" placeholder="Apellidos" />
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <input class="form-control" id="username" name="username" placeholder="Nombre de usuario" />
                    </div>
                    <div class="form-group col-md-4">
                       <input class="form-control" type="password" id="pass" name="pass" placeholder="password" />
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="nacimiento">Fecha de Nacimiento:</label>
                        <input class="form-control" type="date" id="nacimiento" name="nacimiento"/> 
                    </div>
                </div>
                <div class="form-check-label">        
                    <label><input type="radio" name="tipoUsuario" value="usuario" class="form-check-inline" >Usuario</label>
                    <label><input type="radio" name="tipoUsuario" value="editor" class="form-check-inline">Editor</label>
                </div> 
                    <input class="btn btn-primary mb-2" type ="submit" name="accion" value='Registrar'>
                   
            </form>
        </div>   
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
