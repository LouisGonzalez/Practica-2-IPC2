<%-- 
    Document   : ListadoRevistasAdmin
    Created on : 1/10/2019, 09:38:04 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.revistas.Revista"%>
<%@page import="practica2.revistas.RevistaDAO"%>
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
            RevistaDAO dao = new RevistaDAO();
            Revista revista = new Revista();
            ArrayList<Revista> listar = dao.ListarRevistasAdmin();
        %>
        <div class="container"><br><br>
            <h1>Listado de revistas publicadas:</h1>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Id:</th>
                                    <th scope="col">Editor:</th>
                                    <th scope="col">Titulos subidos:</th>
                                    <th scope="col">No. Suscriptores:</th>
                                    <th scope="col">Descripcion:</th>
                                    <th scope="col">Titulo revista:</th>
                                    <th scope="col">Cuota de suscripcion:</th>
                                    <th scope="col">No. Likes:</th>
                                    <th scope="col">Costo Mensual:</th>
                                    <th scope="col">Cuota</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int x = 0;
                                    if(listar.size() > 0){
                                        for(Revista listar2: listar){
                                            revista = listar2;
                                            x++;
                                %>
                                <tr>
                                    <th scope="row"><%=x%></th>
                                    <td><%=revista.getId()%></td>
                                    <td><a href="ControladorPerfil?usuario=<%=revista.getEditor()%>" target="blanck"><%=revista.getEditor()%></a></td>
                                    <td><%=revista.getTitulos_subidos()%></td>
                                    <td><%=revista.getNo_suscriptores()%></td>
                                    <td><%=revista.getDescripcion()%></td>
                                    <td><%=revista.getTitulo_revista()%></td>
                                    <td><%=revista.getCuota_suscripcion()%></td>
                                    <td><%=revista.getNo_likes()%></td>
                                    <td><%=revista.getCosto_mensual()%></td>
                                    <td>
                                        <form action="ControladorAdministrador?user=<%=revista.getTitulo_revista()%>" method="POST">
                                            <input type="submit" name="accion" value="Personalizar cuota mensual" class="btn btn-primary mb-2"/>
                                        </form>
                                    </td>
                                </tr>
                            </tbody>
                                    <%}
                                }%>    
                        </table>
        </div>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>                        
    </body>
</html>
