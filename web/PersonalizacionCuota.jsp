<%-- 
    Document   : PersonalizacionCuota
    Created on : 1/10/2019, 10:01:22 PM
    Author     : luisGonzalez
--%>

<%@page import="practica2.general.LlamadasGenerales"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            String titulo = (String) session.getAttribute("titulo");
            LlamadasGenerales llamadaGeneral = new LlamadasGenerales();
            int cuota = (int) llamadaGeneral.mostrarDatos(titulo, "costo_mensual", "Revista", "titulo_revista");
        %>
    </head>
    <body>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        Cuota Actual: <%=cuota%>
                        <form action="ControladorAdministrador?user=<%=titulo%>" method="POST">
                            <label for="cuota">Nuevo valor de la Cuota local:</label>
                            <input type="number" id="cuotaLocal" name="cuotaLocal" required/>
                            <input type="submit" name="accion" value="Guardar Cambios"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
