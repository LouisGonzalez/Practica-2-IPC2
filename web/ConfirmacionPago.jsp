<%-- 
    Document   : ConfirmacionPago
    Created on : 29/09/2019, 06:51:33 PM
    Author     : luisGonzalez
--%>

<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            Date fechaUltimoPago = (Date)session.getAttribute("ultimaFecha");
            Date fechaAPagar = (Date)session.getAttribute("fechaPago");
            Date fechaHoy = (Date)session.getAttribute("fechaActual");   
            String user = (String)session.getAttribute("nombre");
        %>
    </head>
    <body>
        <STYLE>A {text-decoration: none;}</STYLE>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <form action="ControladorSuscriptor?nombre=<%=user%>" method="POST">
                            <input type="submit" name="accion" value="Deseo pagar"/>
                            <input type="submit" name="accion" value="En otro momento"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
