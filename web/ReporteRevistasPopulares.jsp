<%-- 
    Document   : ReporteRevistasPopulares
    Created on : 5/10/2019, 01:35:03 AM
    Author     : luisGonzalez
--%>
<%@page import="practica2.reportes.Atributos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="practica2.reportes.admin.AtributosAdmin"%>
<%@page import="practica2.reportes.admin.ReporteGanancias"%>
<%@page import="practica2.clases.Conexion"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="estiloPerfil.css" rel="stylesheet">
        <title>JSP Page</title>
        <%
            String user = (String) session.getAttribute("nombre");
            Date fechaInicial = (Date) session.getAttribute("fechaInicial");
            Date fechaFinal = (Date) session.getAttribute("fechaFinal");  
            session.setAttribute("pagina", "SubReporteGananciasAdmin.jsp");
        %>
    </head>
    <body>
        <div class="container">
            <div class="tutorial">
                <div class="slider">
                    <div class="information">
                        <form action="ControladorReportesEditor" method="POST">
                            <label for="fechaInicial">Fecha Inicial:</label>
                            <input type="date" id="fechaInicial" name="fechaInicial"/> 
                            <label for="fechaFinal">Fecha Final:</label>
                            <input type="date" id="fechaFinal" name="fechaFinal"/><br><br>
                            <select name="revista">
                            <%
                                Conexion login = new Conexion();
                                Connection cn = login.getConnection();
                                String consulta = "SELECT * FROM Revista ORDER BY id";
                                PreparedStatement declaracionConsulta = cn.prepareStatement(consulta);
                                ResultSet result = declaracionConsulta.executeQuery();
                                while(result.next()){
                                    %><option value="<%=result.getString("titulo_revista")%>"><%=result.getString("titulo_revista")%></option><%
                                }%>    
                            </select><br><br>
                            <label><input type="checkbox" name="busqueda" value="busqueda">Deseo ver todas las revistas</label><br><br>                        
                            <input type="submit" name="accion" value="Buscar">
                        </form>
                        <%
                            ReporteGanancias reporte = new ReporteGanancias();
                            AtributosAdmin atributo = new AtributosAdmin();
                            ArrayList<AtributosAdmin> listar;
                            boolean verificador = (boolean) session.getAttribute("verificar");
                            if(verificador == false){
                                listar = reporte.listarGananciasFiltros(fechaInicial, fechaFinal, (String) session.getAttribute("listaRev"));
                            } else {
                                listar = reporte.listarGananciasRevistas(fechaInicial, fechaFinal);
                            }
                        %>
                        <table>
                            <thead>
                                <tr>
                                    <th>Titulo revista:</th>
                                    <th>Editor:</th>
                                    <th>Ingresos:</th>
                                    <th>Costos:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    float totalIngresos = 0;
                                    float totalEgresos = 0;
                                    if(listar.size() > 0){
                                    for(AtributosAdmin listar2: listar){
                                        atributo = listar2;
                                        totalIngresos += atributo.getTotal();
                                        totalEgresos += atributo.getTotal_cuota();
                                %>
                                <tr>
                                    <td><%=atributo.getTitulo_revista()%></td>
                                    <td><%=atributo.getEditor()%></td>
                                    <td><%=atributo.getTotal()%></td>
                                    <td><%=atributo.getTotal_cuota()%></td>
                                    
                                    <td><a href="SubControladorReportesAdmin?titulo=<%=atributo.getTitulo_revista()%>">Ver ganancias por suscriptor</a></td>
                                </tr>
                                    <%}
                                    
                                }
                                float suma = totalIngresos - totalEgresos;
                                %>                      
                            </tbody>
                        </table>
                        <table>
                            <thead>
                                <tr>
                                    <th>Total de ingresos:</th>
                                    <th>Total de egresos:</th>
                                    <th>Ganancia total:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><%=totalIngresos%></td>
                                    <td><%=totalEgresos%></td>
                                    <td><%=suma%></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
