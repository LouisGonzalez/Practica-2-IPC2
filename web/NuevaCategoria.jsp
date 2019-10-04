<%-- 
    Document   : NuevaCategoria
    Created on : 3/10/2019, 09:17:34 AM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.categorias.Categoria"%>
<%@page import="practica2.categorias.CategoriasDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            String user = (String) session.getAttribute("nombre");
        %>
    </head>
    <body>
        <%
            CategoriasDAO dao = new CategoriasDAO();
            Categoria categoria = new Categoria();
            ArrayList<Categoria> listar = dao.ListarCategoriasAdmin();
        %>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <form action="ControladorCategoria" method="POST">
                            <label for="categoria">Nombre:</label>
                            <input id="categoria" name="categoria" required/>
                            <input type="submit" name="accion" value="Crear"/>
                        </form>
                        <table>
                            <thead>
                                <tr>
                                    <th>Id:</th>
                                    <th>Descripcion:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    if(listar.size() > 0){
                                        for(Categoria listar2: listar){
                                            categoria = listar2;
                                %>
                                <tr>
                                    <td><%=categoria.getId()%></td>
                                    <td><%=categoria.getDescripcion()%></td>
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
