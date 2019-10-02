<%-- 
    Document   : CambioCuotaGloblal
    Created on : 1/10/2019, 06:58:48 PM
    Author     : luisGonzalez
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            String user = (String)session.getAttribute("nombre");
            int cuota = (int)session.getAttribute("cuota");
        %>
    </head>
    <body>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        Cuota global actual : Q. <%=cuota%><br><br>
                        <form action="ControladorCuota" method="POST">
                            <label for="cuota">Nuevo valor de la Cuota Global:</label>
                            <input type="number" id="cuotaGlobal" name="cuotaGlobal" required/>
                            <input type="submit" name="accion" value="Guardar Cambios"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
