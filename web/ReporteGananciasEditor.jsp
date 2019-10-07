<%-- 
    Document   : ReporteGananciasEditor
    Created on : 4/10/2019, 11:48:29 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.reportes.Atributos"%>
<%@page import="practica2.reportes.CuartoReporte"%>
<%@page import="practica2.clases.Conexion"%>
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
            session.setAttribute("pagina", "SubReporteGananciasEditor.jsp");
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
                <h1>Reporte de ganancias:</h1>
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
                            <div class="form-row">
                                <div class="form-group col-md-4">                                                                           
                            
                                    <select name="revista" class="form-control">
                                        <%
                                        Conexion login = new Conexion();
                                        Connection cn = login.getConnection();
                                        String consulta = "SELECT * FROM Revista WHERE editor = ?";
                                        PreparedStatement declaracionConsulta = cn.prepareStatement(consulta);
                                        declaracionConsulta.setString(1, user);
                                        ResultSet result = declaracionConsulta.executeQuery();
                                        while(result.next()){
                                            %><option value="<%=result.getString("titulo_revista")%>"><%=result.getString("titulo_revista")%></option><%
                                        }%>    
                                    </select>
                                </div>
                            </div>
                            <div class="form-check-inline">                                  
                                <label><input type="checkbox" name="busqueda" value="busqueda">Deseo ver todas las revistas</label><br><br>                        
                            </div>    
                            <input type="submit" name="accion" value="Buscar" class="btn btn-primary mb-2">
                        </form>  
                        <%
                            CuartoReporte reportes = new CuartoReporte();
                            Atributos atributo = new Atributos();
                            ArrayList<Atributos> listar;
                            boolean verificador = (boolean) session.getAttribute("verificar");
                            if(verificador == false){
                                listar = reportes.listar(fechaInicial, fechaFinal, (String) session.getAttribute("listaRev"), "titulo_revista");
                            } else {
                                listar = reportes.listar(fechaInicial, fechaFinal, user, "editor");
                            }
                        %>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">#</th>                                
                                    <th scope="col">Titulo Revista:</th>
                                    <th scope="col">Editor:</th>
                                    <th scope="col">Total acumulado:</th>
                                    <th scope="col">Ganancias:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int x = 0;
                                    float total = 0;
                                    if(listar.size() > 0){
                                    for(Atributos listar2: listar){
                                        atributo = listar2;
                                        total += atributo.getTotal();
                                        x++;
                                %>
                                <tr>
                                    <th scope="row"><%=x%></th>                                                                     
                                    <td><%=atributo.getTitulo_revista()%></td>
                                    <td><%=atributo.getEditor()%></td>
                                    <td><%=atributo.getTotal()%></td>
                                    <td><a href="SubControladorReportesAdmin?titulo=<%=atributo.getTitulo_revista()%>">Ver ganancias por suscripcion</a></td>
                                </tr>
                                    <%}
                                }%>    
                            </tbody>
                        </table>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th>Ganancias totales:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><%=total%></td>
                                </tr>
                            </tbody>
                        </table>    
        </div>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>                            
    </body>
</html>
