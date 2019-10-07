<%-- 
    Document   : PerfilEditor
    Created on : 14/09/2019, 10:35:43 PM
    Author     : luisitopapurey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimun-scale=1.0">     
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/estilosBoots.css" rel="stylesheet">
        <link rel='stylesheet' href='estilo.css'>  
        <%
        String user = (String)session.getAttribute("nombre");
        String primero = "ReporteComentariosEditor.jsp";
        String segundo = "ReporteSuscripcionesEditor.jsp";
        String tercero = "ReporteLikesEditor.jsp";
        String cuarto = "ReporteGananciasEditor.jsp";
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
            <a class="nav-link" href="ControladorPerfil?usuario=<%=user%>"><h5>Bienvenido: <%=user%></h5></a>         
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        
        
         <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Revistas
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="EleccionRevista.jsp">Publicar una nueva revista</a>
          <a class="dropdown-item" href="ListaMisTitulos.jsp">Ver mis revistas publicadas</a>
          <a class="dropdown-item" href="ControladorCategoria">Suscribirme a una nueva revista</a>
          <a class="dropdown-item" href="ListaRevistasSuscritas.jsp">Ver mis revistas sucristas</a>
       
        </div>
      </li> 
     
        
        
        
        <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Reportes
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="ControladorReportesEditor?direccion=<%=primero%>">Reporte de comentarios</a>
          <a class="dropdown-item" href="ControladorReportesEditor?direccion=<%=segundo%>">Reporte de suscripciones</a>
          <a class="dropdown-item" href="ControladorReportesEditor?direccion=<%=tercero%>">Reporte de likes</a>
          <a class="dropdown-item" href="ControladorReportesEditor?direccion=<%=cuarto%>">Reporte de ganancias</a>
       
        </div>
      </li> 
     
        
        
     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Opciones
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="EleccionCampoUsuario.jsp">Modificar datos</a>
          <a class="dropdown-item" href="FotoPerfilUsuario.jsp">Cambiar mi foto</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="ControladorSesion">Salir</a>
        </div>
      </li> 
      </ul>
    </div>
  </div>
</nav>
        </header>
        <section class="main container"></section>
        <footer></footer>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>




