        <%-- 
    Document   : InfoPerfil
    Created on : 20/09/2019, 11:19:16 PM
    Author     : luisitopapurey
--%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario" class="practica2.clases.Usuario"></jsp:useBean>
<jsp:useBean id="llamada" class="practica2.clases.Llamadas"></jsp:useBean>
<jsp:setProperty name="usuario" property="*"></jsp:setProperty>
<!DOCTYPE html>
<html>
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
        </header><br><br>
        <div class="container">                    
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
                    <td>Nombre:</td>
                    <td>${use.nombres}</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Apellidos:</td>
                    <td>${use.apellidos}</td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td>Tipo de usuario:</td>
                    <td>${use.tipo_usuario}</td>
                </tr>
                <tr>
                    <th scope="row">4</th>
                    <td>Fecha de nacimiento:</td>
                    <td>${use.nacimiento}</td>
                </tr>
                <tr>
                    <th scope="row">5</th>
                    <td>Lugar de estudio:</td>
                    <td>${use.lugar_estudio}</td>
                </tr>
                <tr>
                    <th scope="row">6</th>
                    <td>Hobbies:</td>
                    <td>${use.hobbies}</td>
                </tr>
                <tr>
                    <th scope="row">7</th>
                    <td>Temas de interes:</td>
                    <td>${use.temas_interes}</td>
                </tr>
                <tr>
                    <th scope="row">8</th>
                    <td>Descripcion:</td>
                    <td>${use.descripcion}</td>
                </tr>
            </tbody>
        </table>    
        </div>        
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>                                     
</html>
