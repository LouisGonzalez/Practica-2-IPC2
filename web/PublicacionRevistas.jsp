<%-- 
    Document   : PublicacionRevistas
    Created on : 23/09/2019, 11:15:49 PM
    Author     : luisitopapurey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            String user = (String)session.getAttribute("nombre");
            String titulo = (String)session.getAttribute("tituloRev");
        %>    
        <p>UserName: <%=user%></p>  
        <p>UserName: <%=titulo%></p>  
        
    </head>
    <body>
        <STYLE>A {text-decoration: none;} </STYLE>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <form action="ControladorPDF" method="POST" enctype="multipart/form-data">
                            <label for="creacion">Define la fecha de publicacion del titulo:</label>
                            <input type="date" id="creacion" name="creacion" required/>
                            <br><br>
                            <div>Click sobre la imagen para subir tu revista</div>
                            <div class="image-upload">
                                <label id="a" for="revista">
                                <img id="pdf" src="pdf (1).jpeg" alt="Click para subir tu nueva revista">                                       
                                </label>   
                            </div>     
                            <input id="revista" name="revista" type="file" accept=".pdf" required/><br><br>
                                
                            <input type="submit" name="accionar" value="Guardar Revista"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
