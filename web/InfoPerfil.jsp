<%-- 
    Document   : InfoPerfil
    Created on : 20/09/2019, 11:19:16 PM
    Author     : luisitopapurey
--%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario" class="practica2.clases.Usuario"></jsp:useBean>
<jsp:useBean id="llamada" class="practica2.clases.Llamadas"></jsp:useBean>
<jsp:setProperty name="usuario" property="*"></jsp:setProperty>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            String user = (String)session.getAttribute("nombre");
        %>
    </head>
    <body>
        <STYLE>A {text-decoration: none;} </STYLE>
        <div class="container">
            <div class="tutorial">
                <h1>
                    <img src="ControladorImagen?us=${use.nombre_usuario}" width="100" height="100">                   
                    UserName: <%=user%>
                </h1>    
                <div class="slider">
                    <div class="information">
                        
                        <p>Nombres: <strong>${use.nombres}</strong></p>
                        <p>Apellidos: <strong>${use.apellidos}</strong></p>
                        <p>Tipo de Usuario: <strong>${use.tipo_usuario}</strong></p>
                        <p>Fecha de Nacimiento: <strong>${use.nacimiento}</strong></p>
                        <p>Lugar de estudio: <strong>${use.lugar_estudio}</strong></p>
                        <p>Hobbies: <strong>${use.hobbies}</strong></p>
                        <p>Temas de interes: <strong>${use.temas_interes}</strong></p>
                        <p>Descripcion: <strong>${use.descripcion}</strong></p>
                        <form action="EleccionCampoUsuario.jsp" method="POST">
                            <input type="submit" value="envar"/>
                        </form>                     
                    </div>                                                          
                </div>                    
            </div>           
        </div>           
    </body>                                     
</html>
