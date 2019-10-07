<%-- 
    Document   : DescripcionRevista
    Created on : 26/09/2019, 11:53:17 AM
    Author     : luisGonzalez
--%>

<%@page import="practica2.revistas.Revista"%>
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
                                    <a class="dropdown-item" href="EleccionCampoUsuario.jsp">Modificar datos</a>
                                    <a class="dropdown-item" href="FotoPerfilUsuario.jsp">Cambiar mi foto</a>
                                    <a class="dropdown-item" href="ControladorSesion">Salir</a>
                                </div>
                            </li> 
     
                        </ul>
                    </div>
                </div>
            </nav> 
        </header><br><br>
        <div class="container">
            <h1>Descripcion:</h1>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Tipo:</th>
                        <th scope="col">Descripcion:</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Titulo:</td>
                        <td>${revista.titulo_revista}</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Editor:</td>
                        <td>${revista.editor}</td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>Titulo:</td>
                        <td>${revista.titulo_revista}</td>
                    </tr>
                    <tr>
                        <th scope="row">4</th>
                        <td>Descripcion:</td>
                        <td>${revista.descripcion}</td>
                    </tr>
                    <tr>
                        <th scope="row">5</th>
                        <td>Cuota de suscripcion:</td>
                        <td>${revista.cuota_suscripcion}</td>
                    </tr>
                    <tr>
                        <th scope="row">6</th>
                        <td>Suscripciones:</td>
                        <td>${revista.no_suscriptores}</td>
                    </tr>
                </tbody>
            </table>
            <form action="ControladorSuscriptor?nombre=<%=user%>" method="POST">
                <label>Fecha de suscripcion:</label>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <input type="date" id="fecha_suscripcion" name="fecha_suscripcion" class="form-control" required/>
                    </div>
                    <input type="submit" name="accion" class="btn btn-primary mb-2" value="Suscribirme"/>
                </div>    
            </form>
        </div>                                         
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>
