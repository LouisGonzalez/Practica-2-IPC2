<%-- 
    Document   : ListaRevistas
    Created on : 26/09/2019, 11:33:01 AM
    Author     : luisitopapurey
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.revistas.Revista"%>
<%@page import="practica2.revistas.RevistaDAO"%>
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
        <%
            RevistaDAO dao = new RevistaDAO();
            Revista revista = new Revista();
            ArrayList<Revista> listar = dao.ListarTitulos();
        %>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <table>
                            <thead>
                                <tr>
                                    <th>Revista</th>
                                    <th>Titulo</th>
                                    <th>Editor</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%if(listar.size() > 0){
                                    for(Revista listar2: listar){
                                        revista = listar2;
                                %>
                                <tr>
                                    <td><%=revista.getId()%></td>
                                    <td><%=revista.getTitulo_revista()%></td>
                                    <td><a href="ControladorPerfil?usuario=<%=revista.getEditor()%>" target="blank"><%=revista.getEditor()%></a></td>
                                    <td>
                                        <a href="ControladorTitulos?titulo=<%=revista.getTitulo_revista()%>" target="blanck"><img src="pdf (1).jpeg" title="pdf" width="50" height="50"/></a>
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
