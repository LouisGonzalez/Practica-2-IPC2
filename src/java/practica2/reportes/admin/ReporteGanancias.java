package practica2.reportes.admin;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import practica2.clases.Conexion;
import practica2.general.LlamadasGenerales;

/**
 *
 * @author luisGonzalez
 */
public class ReporteGanancias {
    
    private static Connection cn;
    private static Conexion login;
    private AtributosAdmin atributo;
    private LlamadasGenerales llamadaGeneral = new LlamadasGenerales();
    private static final String LLAMADA = "SELECT id_revista, titulo_revista, editor, SUM(total_acumulado) AS total_acumulado FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE fecha_pago <= ? GROUP BY id_revista";
    private static final String SEGUNDA_LLAMADA = "SELECT id_revista, titulo_revista, editor, SUM(total_acumulado) AS total_acumulado FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE fecha_pago >= ? GROUP BY id_revista";
    private static final String TERCERA_LLAMADA = "SELECT id_revista, titulo_revista, editor, SUM(total_acumulado) AS total_acumulado FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista GROUP BY id_revista";
    private static final String CUARTA_LLAMADA = "SELECT id_revista, titulo_revista, editor, SUM(total_acumulado) AS total_acumulado FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE fecha_pago >= ? AND fecha_pago <= ? GROUP BY id_revista";
    
    private static final String LLAMADA_FILTRO = "SELECT id_revista, titulo_revista, editor, SUM(total_acumulado) AS total_acumulado FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE titulo_revista = ? AND fecha_pago <= ? GROUP BY id_revista";
    private static final String SEGUNDA_LLAMADA_FILTRO = "SELECT id_revista, titulo_revista, editor, SUM(total_acumulado) AS total_acumulado FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE titulo_revista = ? AND fecha_pago >= ? GROUP BY id_revista";
    private static final String TERCERA_LLAMADA_FILTRO = "SELECT id_revista, titulo_revista, editor, SUM(total_acumulado) AS total_acumulado FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE titulo_revista = ? GROUP BY id_revista";
    private static final String CUARTA_LLAMADA_FILTRO = "SELECT id_revista, titulo_revista, editor, SUM(total_acumulado) AS total_acumulado FROM Revista r JOIN Pagos_suscriptor p ON r.id = p.id_revista WHERE titulo_revista = ? AND fecha_pago >= ? AND fecha_pago <= ? GROUP BY id_revista";
    
    
    
    public static Connection obtenerConexion(){
        login = new Conexion();
        cn = login.getConnection();
        return cn;
    }
    
    public ArrayList<AtributosAdmin> listarGananciasRevistas(Date fechaInicial, Date fechaFinal) throws SQLException, IOException{
        ArrayList<AtributosAdmin> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionGanancias = null;
        ResultSet result = null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionGanancias = cn.prepareStatement(LLAMADA);
            declaracionGanancias.setDate(1, fechaFinal);
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                atributo = new AtributosAdmin();        
                seteo(result);
                Date fechaCreacion = (Date) llamadaGeneral.mostrarDatos(atributo.getTitulo_revista(), "fecha_creacion", "Revista", "titulo_revista");
                int cuota = (int) llamadaGeneral.mostrarDatos(atributo.getTitulo_revista(), "costo_mensual", "Revista", "titulo_revista");
                int dias = (int) ((fechaFinal.getTime() - fechaCreacion.getTime())/86400000);
                atributo.setTotal_cuota(cuota * dias);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionGanancias = cn.prepareStatement(SEGUNDA_LLAMADA);
            declaracionGanancias.setDate(1, fechaInicial);
            java.util.Date fechaActual = new java.util.Date();
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                atributo = new AtributosAdmin();   
                seteo(result);
                int cuota = (int) llamadaGeneral.mostrarDatos(atributo.getTitulo_revista(), "costo_mensual", "Revista", "titulo_revista");
                int dias = (int) ((fechaActual.getTime() - fechaInicial.getTime())/86400000);
                atributo.setTotal_cuota(cuota * dias);
                
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial == null){
            declaracionGanancias = cn.prepareStatement(TERCERA_LLAMADA);
            result = declaracionGanancias.executeQuery();
            java.util.Date fechaActual = new java.util.Date();
            while(result.next()){
                atributo = new AtributosAdmin();             
                seteo(result);
                int cuota = (int) llamadaGeneral.mostrarDatos(atributo.getTitulo_revista(), "costo_mensual", "Revista", "titulo_revista");
                Date fechaCreacion = (Date) llamadaGeneral.mostrarDatos(atributo.getTitulo_revista(), "fecha_creacion", "Revista", "titulo_revista");
                int dias = (int) ((fechaActual.getTime() - fechaCreacion.getTime())/86400000);
                atributo.setTotal_cuota(cuota * dias);
                list.add(atributo);
            }
        } else {
            declaracionGanancias = cn.prepareStatement(CUARTA_LLAMADA);
            declaracionGanancias.setDate(1, fechaInicial);
            declaracionGanancias.setDate(2, fechaFinal);
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                atributo = new AtributosAdmin();     
                seteo(result);
                int cuota = (int) llamadaGeneral.mostrarDatos(atributo.getTitulo_revista(), "costo_mensual", "Revista", "titulo_revista");
                int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime())/86400000);
                atributo.setTotal_cuota(cuota * dias);
                list.add(atributo);
            }
        }
        login.Desconectar();
        return list;
    }
    
    private AtributosAdmin seteo(ResultSet result) throws SQLException{
        atributo = new AtributosAdmin();
        atributo.setTitulo_revista(result.getString("titulo_revista"));
        atributo.setEditor(result.getString("editor"));
        atributo.setTotal(result.getFloat("total_acumulado"));
        return atributo;
    }
    
    //---------------------------------------------------------------------------------------------
    
    public ArrayList<AtributosAdmin> listarGananciasFiltros(Date fechaInicial, Date fechaFinal, String titulo_revista) throws SQLException, IOException{
        ArrayList<AtributosAdmin> list = new ArrayList<>();
        obtenerConexion();
        PreparedStatement declaracionGanancias = null;
        ResultSet result = null;
        if(fechaInicial == null && fechaFinal != null){
            declaracionGanancias = cn.prepareStatement(LLAMADA_FILTRO);
            declaracionGanancias.setString(1, titulo_revista);
            declaracionGanancias.setDate(2, fechaFinal);
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                atributo = new AtributosAdmin();   
                seteoFiltrado(result, titulo_revista);
                Date fechaCreacion = (Date) llamadaGeneral.mostrarDatos(atributo.getTitulo_revista(), "fecha_creacion", "Revista", "titulo_revista");
                int cuota = (int) llamadaGeneral.mostrarDatos(atributo.getTitulo_revista(), "costo_mensual", "Revista", "titulo_revista");
                int dias = (int) ((fechaFinal.getTime() - fechaCreacion.getTime())/86400000);
                atributo.setTotal_cuota(cuota * dias);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial != null){
            declaracionGanancias = cn.prepareStatement(SEGUNDA_LLAMADA_FILTRO);
            declaracionGanancias.setString(1, titulo_revista);
            declaracionGanancias.setDate(2, fechaInicial);
            java.util.Date fechaActual = new java.util.Date();
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                atributo = new AtributosAdmin();    
                seteoFiltrado(result, titulo_revista);
                float cuota = (float) llamadaGeneral.mostrarDatos(atributo.getTitulo_revista(), "costo_mensual", "Revista", "titulo_revista");
                int dias = (int) ((fechaActual.getTime() - fechaInicial.getTime())/86400000);
                atributo.setTotal_cuota(cuota * dias);
                list.add(atributo);
            }
        } else if(fechaFinal == null && fechaInicial == null){
            declaracionGanancias = cn.prepareStatement(TERCERA_LLAMADA_FILTRO);
            declaracionGanancias.setString(1, titulo_revista);
            result = declaracionGanancias.executeQuery();
            java.util.Date fechaActual = new java.util.Date();
            while(result.next()){
                atributo = new AtributosAdmin();        
                seteoFiltrado(result, titulo_revista);
                int cuota = (int) llamadaGeneral.mostrarDatos(atributo.getTitulo_revista(), "costo_mensual", "Revista", "titulo_revista");
                Date fechaCreacion = (Date) llamadaGeneral.mostrarDatos(atributo.getTitulo_revista(), "fecha_creacion", "Revista", "titulo_revista");
                int dias = (int) ((fechaActual.getTime() - fechaCreacion.getTime())/86400000);
                atributo.setTotal_cuota(cuota * dias);
                list.add(atributo);
            }
        } else {
            declaracionGanancias = cn.prepareStatement(CUARTA_LLAMADA_FILTRO);
            declaracionGanancias.setString(1, titulo_revista);
            declaracionGanancias.setDate(2, fechaInicial);
            declaracionGanancias.setDate(3, fechaFinal);
            result = declaracionGanancias.executeQuery();
            while(result.next()){
                atributo = new AtributosAdmin();        
                seteoFiltrado(result, titulo_revista);
                int cuota = (int) llamadaGeneral.mostrarDatos(atributo.getTitulo_revista(), "costo_mensual", "Revista", "titulo_revista");
                int dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime())/86400000);
                atributo.setTotal_cuota(cuota * dias);
                list.add(atributo);
            }
        }
        login.Desconectar();
        return list;
    }
    
    private AtributosAdmin seteoFiltrado(ResultSet result, String titulo_revista) throws SQLException{
        atributo.setTitulo_revista(titulo_revista);
        atributo.setEditor(result.getString("editor"));
        atributo.setTotal(result.getFloat("total_acumulado"));
        return atributo;
    }
    
}
