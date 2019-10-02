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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="estiloPerfil.css" rel="stylesheet">
        <%
            String user = (String) session.getAttribute("nombre");
        %>
    <p>Username: <%=user%></p>
    </head>
    <body>
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
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <table>
                            <thead>
                                <tr>
                                    <th>Id Revista:</th>
                                    <th>Fecha Suscripcion:</th>
                                    <th>Ultimo Pago:</th>
                                    <th>Titulo de la revista:</th>
                                </tr>                                
                            </thead>
                            <tbody>
                                <%if(listar.size() > 0){
                                    for(Suscriptor listar2: listar){
                                        suscriptor = listar2;
                                %>
                                <tr>
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
                </div>
            </div>
        </div>
    </body>
</html>
