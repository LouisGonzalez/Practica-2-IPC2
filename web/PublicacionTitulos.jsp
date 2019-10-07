<%-- 
    Document   : PublicacionRevista
    Created on : 22/09/2019, 01:39:29 AM
    Author     : luisitopapurey
--%>
<%@page import="java.sql.*"%>
<%@page import="practica2.clases.Conexion"%>
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
        </header>
        <style>
            .image-upload > input {
                display: none;
            }
            .image-upload img{
                width: 130px;
                cursor: pointer;
            }
        </style>

        <div class="container"><br><br>
            <h1>Publicar un nuevo titulo:</h1>
                   
            <form action="ControladorPDF" method="POST" enctype="multipart/form-data">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <input id="titulo" class="form-control" name="titulo" placeholder="Titulo" required/>
                    </div>   
                    <div class="form-group col-md-4">
                        <input type="number" class="form-control" id="cuota" name="cuota" placeholder="Cuota de suscripcion" required/>
                    </div>      
                </div>
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="cat">A que categoria pertenecera su revista:</label>                                
                        <select name="categoria" class="form-control">
                            <%Conexion login = new Conexion();
                            Connection cn = login.getConnection();
                            String LISTADO = "SELECT * FROM Categorias ORDER BY id";
                            PreparedStatement declaracionCategoria = cn.prepareStatement(LISTADO);
                            ResultSet result = declaracionCategoria.executeQuery();
                            while(result.next()){%>
                                <option value="<%=result.getString("descripcion")%>"><%=result.getString("descripcion")%></option><%                                        
                            }%>                                        
                        </select>
                    </div>
                    <div class="form-group col-md-4">    
                        <label for="creacion">Define la fecha de publicacion de su revista:</label>
                        <input type="date" id="creacionRev" name="creacionRev" class="form-control" required/>
                    </div>
                </div>
                <div class="form-row">        
                    <div class="form-group col-md-8">    
                        <textarea name="descripcion" class="form-control" rows="5" cols="85" spellcheck="true" placeholder="Agrega una descripcion sobre tu revista"></textarea>
                    </div>    
                </div>    
                <input type="submit" class="btn btn-primary mb-2" name="accionar" value="Guardar">
            </form>  
        </div>
            <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>
