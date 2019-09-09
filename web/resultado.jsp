<%-- 
    Document   : resultado
    Created on : 8/09/2019, 05:01:55 PM
    Author     : luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            session.setAttribute("user", request.getParameter("nombre"));
        %>    
    </head>
    <body>
        <h1>Registro completo</h1>
        Nombre: <%=request.getParameter("nombre")%>
        <br>
        password: <%=request.getParameter("pass")%>
        <br>
    </body>
</html>
