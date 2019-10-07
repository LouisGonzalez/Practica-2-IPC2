<%-- 
    Document   : ReporteGananciasAdmin
    Created on : 5/10/2019, 01:34:21 AM
    Author     : luisGonzalez
--%>

<%@page import="practica2.reportes.Atributos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="practica2.reportes.admin.AtributosAdmin"%>
<%@page import="practica2.reportes.admin.ReporteRevistasPopulares"%>
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
            session.setAttribute("pagina", "SubReporteSuscripcionesAdmin.jsp");
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
            <h1>Las 5 revistas mas populares:</h1>
                        <form action="ControladorReportesEditor" method="POST">
                            <div class="form-row">
                                <div class="form-group col-md-4">                                                                                
                                    <label for="fechaInicial">Fecha Inicial:</label>
                                    <input type="date" id="fechaInicial" name="fechaInicial" class="form-control">
                                </div>
                                <div class="form-group col-md-4">                                                    
                            
                                    <label for="fechaFinal">Fecha Final:</label>
                                    <input type="date" id="fechaFinal" name="fechaFinal" class="form-control">
                                </div>
                            </div>    
                            <div class="form-check-inline">                                  
                                <label><input type="checkbox" name="busqueda" value="busqueda">Deseo ver todas las revistas</label><br><br>                        
                            </div>
                            <input type="submit" name="accion" value="Buscar" class="btn btn-primary mb-2">
                        </form>  
                        <%
                            ReporteRevistasPopulares reporte = new ReporteRevistasPopulares();
                            AtributosAdmin atributo = new AtributosAdmin();
                            ArrayList<AtributosAdmin> listar = reporte.listarRevistasPopulares(fechaInicial, fechaFinal);
                        %>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">#</th>                                
                                    <th scope="col">Titulo Revista:</th>
                                    <th scope="col">Editor:</th>
                                    <th scope="col">No. Suscripciones:</th>
                                    <th scope="col">Suscriptores:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int y = 0;
                                    if(listar.size() > 0 && listar.size() < 5){
                                    for(AtributosAdmin listar2: listar){
                                        atributo = listar2;
                                        y++;
                                %>
                                <tr>
                                    <th scope="row"><%=y%></th>                                                                     
                                    <td><%=atributo.getTitulo_revista()%></td>
                                    <td><a href="ControladorPerfil?usuario=<%=atributo.getEditor()%>" target="blank"><%=atributo.getEditor()%></a></td>
                                    <td><%=atributo.getNo_suscriptores()%></td>
                                    <td><a href="SubControladorReportesAdmin?titulo=<%=atributo.getTitulo_revista()%>">Ver suscriptores</a></td>
                                </tr>
                                    <%}
                                } else if(listar.size() > 0 && listar.size() > 5) { 
                                    for(int x = 0; x<5; x++){
                                        atributo = listar.get(x);
                                %>
                                <tr>
                                    <th scope="row"><%=x%></th>                                                                     
                                    <td><%=atributo.getTitulo_revista()%></td>
                                    <td><a href="ControladorPerfil?usuario=<%=atributo.getEditor()%>" target="blank"><%=atributo.getEditor()%></a></td>
                                    <td><%=atributo.getNo_suscriptores()%></td>
                                    <td><a href="SubControladorReportesAdmin?titulo=<%=atributo.getTitulo_revista()%>">Ver suscriptores</a></td>
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
