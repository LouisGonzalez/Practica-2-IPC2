<%-- 
    Document   : Comentarios
    Created on : 30/09/2019, 11:23:56 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.comentarios.Comentarios"%>
<%@page import="practica2.comentarios.ComentariosDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimun-scale=1.0">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/estilosBoots.css" rel="stylesheet">
        <%
            int id = (int) session.getAttribute("id_revista");
            String user = (String) session.getAttribute("nombre");
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
        
        
        
        
        <%
            ComentariosDAO dao = new ComentariosDAO();
            Comentarios comentario = new Comentarios();
            ArrayList<Comentarios> listar = dao.ListarComentarios(id);
        %>
        <STYLE>A {text-decoration: none;}</STYLE>
        <div class="container"><br><br>
            <h1>Bandeja de comentarios</h1>
                        <form action="ControladorComentarios?user=<%=user%>" method="POST" enctype="multipart/form-data">
                            <div class="form-group col-md-6">
                                <textarea name="comentario" rows="2" cols="85" class="form-control" spellcheck="true" placeholder="Agrega un comentario"></textarea> 
                            </div>  
                                <input type="submit" name="accion" class="btn btn-primary mb-2" value="Enviar"/>
                        </form>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">#</th>                                    
                                    <th scope="col">No.</th>
                                    <th scope="col">Usuario:</th>
                                    <th scope="col">Comentario:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int x = 0;
                                    if(listar.size() > 0){
                                    for(Comentarios listar2: listar){
                                        comentario = listar2;
                                        x++;
                                %>
                                <tr>
                                    <th scope="row"><%=x%></th>
                                    <td><%=comentario.getId()%></td>
                                    <td><%=comentario.getNombre_usuario()%></td>
                                    <td><%=comentario.getDescripcion()%></td>
                                </tr>
                                    <%}
                                }%>
                            </tbody>
                        </table>
        </div>
    </body>
</html>
