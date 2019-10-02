<%-- 
    Document   : AgregarAdministrador
    Created on : 1/10/2019, 04:19:30 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.clases.Usuario"%>
<%@page import="practica2.administrador.AdministradorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <%
            AdministradorDAO dao = new AdministradorDAO();
            Usuario usuario = new Usuario();
            ArrayList<Usuario> listar = dao.ListarUsuarios();
        %>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <table>
                            <thead>
                                <tr>
                                    <th>Usuario</th>
                                    
                                </tr>
                            </thead>
                            <tbody>
                                <%if(listar.size() > 0){ 
                                    for(Usuario listar2: listar){
                                        usuario = listar2;                                    
                                %>
                                <tr>
                                    <td><a href="ControladorPerfil?usuario=<%=usuario.getNombre_usuario()%>" target="blanck"><%=usuario.getNombre_usuario()%></a></td>
                                    <td>
                                        <form action="ControladorAdministrador?user=<%=usuario.getNombre_usuario()%>" method="POST">
                                            <input type="submit" name="accion" value="Agregar"/>
                                        </form>
                                    </td>
                                    
                                </tr>
                                  <%}
                                }%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
