package practica2.revistas;

import java.io.InputStream;
import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class Revista {
    
    private String editor, titulo_revista, descripcion;
    private int id_revista, no_titulo, cuota_suscripcion, id, titulos_subidos, no_suscriptores, no_likes, costo_mensual;
    private Date fecha_creacion;
    private InputStream pdf;
    private byte[] archivoPdf;

    public int getNo_likes() {
        return no_likes;
    }

    public void setNo_likes(int no_likes) {
        this.no_likes = no_likes;
    }

    public int getCosto_mensual() {
        return costo_mensual;
    }

    public void setCosto_mensual(int costo_mensual) {
        this.costo_mensual = costo_mensual;
    }

    
    public int getTitulos_subidos() {
        return titulos_subidos;
    }

    public void setTitulos_subidos(int titulos_subidos) {
        this.titulos_subidos = titulos_subidos;
    }

    public int getNo_suscriptores() {
        return no_suscriptores;
    }

    public void setNo_suscriptores(int no_suscriptores) {
        this.no_suscriptores = no_suscriptores;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public byte[] getArchivoPdf() {
        return archivoPdf;
    }

    public void setArchivoPdf(byte[] archivoPdf) {
        this.archivoPdf = archivoPdf;
    }
    
    public int getCuota_suscripcion() {
        return cuota_suscripcion;
    }

    public void setCuota_suscripcion(int cuota_suscripcion) {
        this.cuota_suscripcion = cuota_suscripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getId_revista() {
        return id_revista;
    }

    public void setId_revista(int id_revista) {
        this.id_revista = id_revista;
    }

    public String getTitulo_revista() {
        return titulo_revista;
    }

    public void setTitulo_revista(String titulo_revista) {
        this.titulo_revista = titulo_revista;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public InputStream getPdf() {
        return pdf;
    }

    public void setPdf(InputStream pdf) {
        this.pdf = pdf;
    }

    public int getNo_titulo() {
        return no_titulo;
    }

    public void setNo_titulo(int no_titulo) {
        this.no_titulo = no_titulo;
    }
    
    
    
    
}
