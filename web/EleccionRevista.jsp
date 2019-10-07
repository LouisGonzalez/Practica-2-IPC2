<%-- 
    Document   : EleccionRevista
    Created on : 24/09/2019, 11:26:20 AM
    Author     : luisitopapurey
--%>

<%@page import="practica2.clases.Conexion"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="llamada" class="practica2.general.LlamadasGenerales"></jsp:useBean>
<jsp:useBean id="conexion" class="practica2.clases.Conexion"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
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
        <STYLE>A {text-decoration: none;} </STYLE>
        <div class="container">
            <h1>Elige una revista o bien crea una nueva:</h1>
            <form action="ControladorPDF" method="POST">
                <div class="form-group col-md-4">
                    <select name="eleccion" class="form-control">
                        <%Conexion login= new Conexion();
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
                <label><input type="checkbox" name="nuevo" value="nuevo" class="form-check-inline">Deseo crear un nuevo titulo</label><br><br>
                <input type="submit" name="accionar" class="btn btn-primary mb-2" value="Siguiente"/>
            </form>
        </div>    
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
