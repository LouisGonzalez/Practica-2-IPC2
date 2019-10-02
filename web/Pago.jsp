<%-- 
    Document   : Pago
    Created on : 29/09/2019, 07:31:14 PM
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
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        Ultimo Pago: <%=fechaUltimoPago%>
                        Dia en que termino la vigencia del ultimo pago: <%=fechaAPagar%>
                        <form action="ControladorSuscriptor" method="POST">
                            <label for="fecha">Fecha de Pago:</label>
                            <input type="date" id="fechaPago" name="fechaPago" required/>
                            <input type="submit" name="accion" value="Pagar"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>
