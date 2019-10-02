<%-- 
    Document   : resultado
    Created on : 8/09/2019, 05:01:55 PM
    Author     : luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <div class="slider">
                    <div class="information">
                        <h1>Registro completo</h1>  
                        <br>
                        <%@include file="cambio-foto.html"%>
                    </div>
                </div>
            </div>
        </div>            
    </body>    
</html>
