<%-- 
    Document   : SubReporteLikesEditor
    Created on : 6/10/2019, 03:34:38 AM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.reportes.Atributos"%>
<%@page import="practica2.reportes.TercerReporte"%>
<%@page import="java.sql.*"%>
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
            Date fechaInicial = (Date) session.getAttribute("fechaInicial");
            Date fechaFinal = (Date) session.getAttribute("fechaFinal");
            String titulo = (String) session.getAttribute("titulo_revista");
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
        
            <div class="container"><br><br>
                        <%
                            TercerReporte reporte = new TercerReporte();
                            Atributos atributo = new Atributos();
                            ArrayList<Atributos> listar = reporte.ListarLikes(fechaInicial, fechaFinal, titulo);
                        %>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">#</th>                                
                                    <th scope="col">Nombre de usuario:</th>
                                    <th scope="col">Fecha like:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int x = 0;
                                    if(listar.size() > 0){
                                    for(Atributos listar2: listar){
                                        atributo = listar2;
                                        x++;
                                %>
                                <tr>
                                    <th scope="row"><%=x%></th>                                                                     
                                    <td><a href="ControladorPerfil?usuario=<%=atributo.getNombre_usuario()%>" target="blank"><%=atributo.getNombre_usuario()%></a></td>
                                    <td><%=atributo.getFecha_creado()%></td>
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
