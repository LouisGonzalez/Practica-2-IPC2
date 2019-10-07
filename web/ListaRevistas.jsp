<%-- 
    Document   : ListaRevistas
    Created on : 26/09/2019, 11:33:01 AM
    Author     : luisGonzalez
--%>

<%@page import="practica2.categorias.CategoriasDAO"%>
<%@page import="java.sql.*"%>
<%@page import="practica2.clases.Conexion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="practica2.revistas.Revista"%>
<%@page import="practica2.revistas.RevistaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimun-scale=1.0">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/estilosBoots.css" rel="stylesheet">
        <%
            String user = (String)session.getAttribute("nombre");
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
        
            <div class="container"><br><br>
                <h1>Listado revistas publicadas</h1>
                                
                        <form action="ControladorCategoria" method="POST">
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <select name="eleccion" class="form-control">
                                        <%Conexion login= new Conexion();
                                        Connection cn = login.getConnection();       
                                        String consulta = "SELECT * FROM Categorias ORDER BY id";
                                        PreparedStatement declaracionConsulta = cn.prepareStatement(consulta);
                                        ResultSet result = declaracionConsulta.executeQuery();
                                        while(result.next()){
                                            %><option value="<%=result.getString("descripcion")%>"><%=result.getString("descripcion")%></option><%
                                        }%>                                
                                    </select>
                                </div>
                            </div>
                            <div class="form-check-inline">        
                                <label><input type="checkbox" name="busqueda" value="busqueda" class="form-check-input">Deseo ver todas las revistas</label>
                            </div>
                            <input type="submit" name="accion" value="Buscar" class="btn btn-primary mb-2">                       
                        </form>
                            <%
                                Revista revista = new Revista();
                                ArrayList<Revista> listar;
                                boolean verificador = (boolean) session.getAttribute("verificador");
                                if(verificador == false){
                                    CategoriasDAO dao = new CategoriasDAO();
                                    listar = dao.ListarFiltros((String) session.getAttribute("eleccion"));
                                } else {
                                    RevistaDAO dao = new RevistaDAO();
                                    listar = dao.ListarTitulos();
                                }                               
                            %>        
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Revista</th>
                                    <th scope="col">Titulo</th>
                                    <th scope="col">Editor</th>
                                    <th scope="col">Descripcion</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int x = 0;
                                    if(listar.size() > 0){
                                    for(Revista listar2: listar){
                                        revista = listar2;
                                        x++;
                                %>
                                <tr>
                                    <th scope="row"><%=x%></th>
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
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
