<%-- 
    Document   : ListaRevistasSuscritas
    Created on : 29/09/2019, 12:04:41 PM
    Author     : luisGonzalez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="practica2.suscriptor.Suscriptor"%>
<%@page import="practica2.suscriptor.SuscriptorDAO"%>
<%@page import="practica2.likes.LlamadasLikes"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimun-scale=1.0">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/estilosBoots.css" rel="stylesheet">
        <%
            String user = (String) session.getAttribute("nombre");
        %>
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
                <div class="container">
                    <a class="navbar-brand" href="#">
                        <img src="ControladorImagen?us=<%=user%>" alt="" class="rounded-circle" width="50">
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarResponsive">
                        <ul class="navbar-nav">                   
                            <li class="nav-item active">
                                <a class="nav-link" href="ControladorPerfil?usuario=<%=user%>"><h5><%=user%></h5></a>         
                            </li>
                        </ul>
      
                        <ul class="navbar-nav ml-auto">        
                            <li class="nav-item active">
                                <a class="nav-link" href="ControladorRedireccion">Home
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Opciones
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="EleccionCampoUsuario.jsp">Modificar datos</a>
                                    <a class="dropdown-item" href="FotoPerfilUsuario.jsp">Cambiar mi foto</a>
                                    <a class="dropdown-item" href="ControladorSesion">Salir</a>
                                </div>
                            </li> 
     
                        </ul>
                    </div>
                </div>
            </nav> 
        </header>
        
        <%  
            LlamadasLikes llamada = new LlamadasLikes();
            String BUSQUEDA = "SELECT * FROM Likes WHERE nombre_usuario = ? AND id_revista = ?";    
            SuscriptorDAO dao = new SuscriptorDAO();
            Suscriptor suscriptor = new Suscriptor();
            ArrayList<Suscriptor> listar = dao.ListarSuscripciones(user);
        %>
        <script>
            function alerta(){
                var opcion == confirm
            }
        </script>    
        <STYLE>A {text-decoration: none;}</STYLE>
        <div class="container"><br><br>
            <h1>Mis revistas suscritas:</h1>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Id Revista:</th>
                                    <th scope="col">Fecha Suscripcion:</th>
                                    <th scope="col">Ultimo Pago:</th>
                                    <th scope="col">Titulo de la revista:</th>
                                    <th scope="col">Revistas:</th>
                                    <th scope="col">Comentarios:</th>
                                    <th scope="col">Likes:</th>
                                </tr>                                
                            </thead>
                            <tbody>
                                <%
                                    int x = 0;
                                    if(listar.size() > 0){
                                    for(Suscriptor listar2: listar){
                                        suscriptor = listar2;
                                        x++;
                                %>
                                <tr>
                                    <th scope="row"><%=x%></th>
                                    <td><%=suscriptor.getId_revista()%></td>
                                    <td><%=suscriptor.getFecha_suscripcion()%></td>
                                    <td><%=suscriptor.getUltimo_pago()%></td>
                                    <td><%=suscriptor.getTitulo_revista()%></td>
                                    <td><a href="ControladorSuscriptor?id=<%=suscriptor.getId()%>" target="blanck">Ver mis revistas</a></td> 
                                    <td><a href="ControladorComentarios?id=<%=suscriptor.getId()%>" target="blank">Ver comentarios</a></td>
                                    <td>
                                        <form action="ControladorLike?id=<%=suscriptor.getId()%>" method="POST">
                                            <%
                                                boolean verificador = llamada.siExiste(BUSQUEDA, user, suscriptor.getId_revista());
                                                if(verificador == true){%>
                                                    <STYLE>
                                                        .botonA{
                                                            background-color: #0016b0;
                                                        }
                                                    </STYLE>
                                                    <input type="submit" name="accion" value="Me gusta" class="botonA" >            
                                                <%} else if(verificador == false){%>
                                                    <STYLE>
                                                    .botonB{
                                                        background-color: #FFF87F;
                                                    }
                                                    </STYLE>
                                                   <input type="submit" name="accion" value="Me gusta" class="botonB" >                                           
                                                <%}
                                            %>                            
                                        </form>
                                    </td>
                                </tr>                                
                                    <%}
                                }%>          
                            </tbody>
                        </table>
        </div>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>         
    </body>
</html>
