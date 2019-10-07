<%-- 
    Document   : NuevaCategoria
    Created on : 3/10/2019, 09:17:34 AM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.categorias.Categoria"%>
<%@page import="practica2.categorias.CategoriasDAO"%>
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
            CategoriasDAO dao = new CategoriasDAO();
            Categoria categoria = new Categoria();
            ArrayList<Categoria> listar = dao.ListarCategoriasAdmin();
        %>
        <div class="container"><br><br>
            <h1>Categorias:</h1>
                        <form action="ControladorCategoria" method="POST">
                            <div class="form-row">
                                <div class="form-group col-md-4">
                            
                                    <label for="categoria">Nombre:</label>
                                    <input id="categoria" name="categoria" class="form-control" required/>
                                </div>
                            </div>    
                            <input type="submit" name="accion" value="Crear" class="btn btn-primary mb-2" />
                        </form>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Descripcion:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int x = 0;
                                    if(listar.size() > 0){
                                        for(Categoria listar2: listar){
                                            categoria = listar2;
                                            x++;
                                %>
                                <tr>
                                    <th scope="row"><%=x%></th>
                                    <td><%=categoria.getDescripcion()%></td>
                                </tr>
                                    <%}
                                }%>
                            </tbody>
                        </table>
        </div>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>                            
    </body>
</html>
