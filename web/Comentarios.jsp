<%-- 
    Document   : Comentarios
    Created on : 30/09/2019, 11:23:56 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.comentarios.Comentarios"%>
<%@page import="practica2.comentarios.ComentariosDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            int id = (int) session.getAttribute("id_revista");
            String user = (String) session.getAttribute("nombre");
        %>
        user = <%=user%>
        id = <%=id%>
    </head>
    <body>
        <%
            ComentariosDAO dao = new ComentariosDAO();
            Comentarios comentario = new Comentarios();
            ArrayList<Comentarios> listar = dao.ListarComentarios(id);
        %>
        <STYLE>A {text-decoration: none;}</STYLE>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <form action="ControladorComentarios?user=<%=user%>" method="POST" enctype="multipart/form-data">
                            <label for="comentario">Agrega un comentario:</label>
                            <textarea name="comentario" rows="5" cols="85" spellcheck="true"></textarea><br>  
                            <input type="submit" name="accion" value="Enviar"/>
                        </form>
                        <table>
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Usuario:</th>
                                    <th>Comentario:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(listar.size() > 0){
                                    for(Comentarios listar2: listar){
                                        comentario = listar2;
                                %>
                                <tr>
                                    <td><%=comentario.getId()%></td>
                                    <td><%=comentario.getNombre_usuario()%></td>
                                    <td><%=comentario.getDescripcion()%></td>
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
