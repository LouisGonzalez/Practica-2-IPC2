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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
        String user = (String)session.getAttribute("nombre");
        %>
        <p>UserName: <%=user%></p>
        <link href="estiloPerfil.css" rel="stylesheet">
    </head>
    <body>
        <STYLE>A {text-decoration: none;} </STYLE>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <form action="ControladorPDF" method="POST">
                            <select name="eleccion">
                                <%Conexion login= new Conexion();
                                Connection cn = login.getConnection();       
                                String consulta = "SELECT * FROM Revista WHERE editor = ?";
                                PreparedStatement declaracionConsulta = cn.prepareStatement(consulta);
                                declaracionConsulta.setString(1, user);
                                ResultSet result = declaracionConsulta.executeQuery();
                                while(result.next()){
                                %><option value="<%=result.getString("titulo_revista")%>"><%=result.getString("titulo_revista")%></option><%
                                }%>                                
                            </select><br><br>
                            <label><input type="checkbox" name="nuevo" value="nuevo">Deseo crear un nuevo titulo</label><br><br>
                            <input type="submit" name="accionar" value="Siguiente"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>                
    </body>
</html>
