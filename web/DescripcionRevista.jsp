<%-- 
    Document   : DescripcionRevista
    Created on : 26/09/2019, 11:53:17 AM
    Author     : luisGonzalez
--%>

<%@page import="practica2.revistas.Revista"%>
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
    </head>
    <body>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <p>Titulo: <strong>${revista.titulo_revista}</strong></p><br>
                        <p>Editor:<strong>${revista.editor}</strong></p><br>
                        <p>Descripcion: <strong>${revista.descripcion}</strong></p><br>
                        <p>Cuota de Suscripcion: <strong>${revista.cuota_suscripcion}</strong></p><br>
                        <p>No. revista subidas: <strong>${revista.titulos_subidos}</strong></p><br>
                        <p>Suscripciones: <strong>${revista.no_suscriptores}</strong></p>
                        <form action="ControladorSuscriptor?nombre=<%=user%>" method="POST">
                            <input type="date" id="fecha_suscripcion" name="fecha_suscripcion" required/>
                            <input type="submit" name="accion" value="Suscribirme"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
