<%-- 
    Document   : PublicacionRevista
    Created on : 22/09/2019, 01:39:29 AM
    Author     : luisitopapurey
--%>
<%@page import="java.sql.*"%>
<%@page import="practica2.clases.Conexion"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
        String user = (String)session.getAttribute("nombre");
        %>
        <p>UserName: <%=user%></p>    
    </head>
    <body>
        <STYLE>A {text-decoration: none;} </STYLE>
            <div class="container">
                <div class="tutorial">
                    <h1>Mi primer revista</h1>
                    <div class="slider">
                        <div class="information">
                            <style>
                                .image-upload > input {
                                    display: none;
                                }
                                .image-upload img{
                                    width: 130px;
                                    cursor: pointer;
                                }
                            </style>


                            <form action="ControladorPDF" method="POST" enctype="multipart/form-data">
                                <label for="titulo">Titulo de tu nueva linea de revistas:</label>
                                <input id="titulo" name="titulo" required/>
                                <br><br>
                                <label for="cuota">Defina la cuota de suscripcion:</label>
                                <input type="number" id="cuota" name="cuota" required/><br><br>
                                <label for="cat">A que categoria pertenecera su revista:</label>                                
                                <select name="categoria">
                                    <%Conexion login = new Conexion();
                                    Connection cn = login.getConnection();
                                    String LISTADO = "SELECT * FROM Categorias ORDER BY id";
                                    PreparedStatement declaracionCategoria = cn.prepareStatement(LISTADO);
                                    ResultSet result = declaracionCategoria.executeQuery();
                                    while(result.next()){%>
                                    <option value="<%=result.getString("descripcion")%>"><%=result.getString("descripcion")%></option><%                                        
                                    }%>                                    
                                </select><br><br>
                                <label for="descripcion">Agrega una descripcion sobre tu revista:</label>
                                <textarea name="descripcion" rows="5" cols="85" spellcheck="true"></textarea><br><br>
                                Al momento de publicar tu nueva revista pasaras tu cuenta pasara a ser<br>
                                de tipo editor, tendras acceso a opciones nuevas dentro de tu perfil que<br>
                                te iremos explicando lentamente, si deseas continuar pulsa en "Guardar.<br>
                                <input type="submit" name="accionar" value="Guardar">
                            </form>    
                        </div>
                    </div>
                </div>
            </div>
    </body>
</html>
