<%-- 
    Document   : ModificacionDatosUsuario
    Created on : 21/09/2019, 01:09:27 PM
    Author     : luisitopapurey
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario" class="practica2.clases.Usuario"></jsp:useBean>
<jsp:useBean id="llamada" class="practica2.clases.Llamadas"></jsp:useBean>
<jsp:setProperty name="usuario" property="*"></jsp:setProperty>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            String user = (String)session.getAttribute("nombre");
            String tipo = (String)session.getAttribute("tipo");
        %>
        <p>UserName: <%=user%></p>        
    </head>
    <body>
        <div class="container">
            <div class="tutorial">
                <h1>Modificacion de datos:</h1>  
                <div class="slider">
                    <div class="information">
                        <form action="ControladorPerfil" method="POST">
                            <%usuario.setNombres(llamada.mostrarDatos(user, tipo, "Usuarios", "nombre_usuario").toString());
                            if(!usuario.getNombres().equals("null") && !usuario.getNombres().equals("")) {%>
                                <%=tipo%> actual: <%=usuario.getNombres()%><br><br>
                            <%}%>         
                            <label for="valor">Nuevo Valor:</label>
                            <textarea id="valor" name="valor" rows="5" cols="85" spellcheck="true"></textarea>
                            <br><br>                         
                            <input type="submit" name="accion" value="Guardar">
                        </form>
                    </div>
                </div>    
            </div>
        </div>     
    </body>
</html>
