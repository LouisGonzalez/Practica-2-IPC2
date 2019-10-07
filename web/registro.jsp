<%-- 
    Document   : registro
    Created on : 8/09/2019, 04:59:20 PM
    Author     : luis
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang ='es'>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimun-scale=1.0">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilosBoots.css" rel="stylesheet">
        <link rel='stylesheet' href='estilo.css'>        
    </head>
    <body>
        
        
        
        <div class="container">
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <h5 class="card-title text-center">LOGIN</h5>
                            <form class="form-signin" action="Controlador" method="POST">
                                <div class="form-label-group">
                                    <input id="nombre" name="nombre" class="form-control" placeholder="Nombre de usuario" required>
                                </div>
                                <div class="form-label-group">
                                    <input type="password" id="pass" name="pass" class="form-control" placeholder="Password" required>
                                </div>
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" name="accion" value="Entrar">Entrar</button>
                                <hr class="my-4">
                                <a href="NuevaCuenta.jsp">Deseo crear una cuenta</a>
                            </form>    
                        </div>    
                    </div>    
                </div>
            </div>
        </div>
        
        
        
        
        
        
        
        
        
        
        
        
        <script src="js/jquery-3.4.1.min"></script>
        <script src="js/bootstrap.min"></script>
    </body>    
</html>
