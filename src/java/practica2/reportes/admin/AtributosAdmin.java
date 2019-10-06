package practica2.reportes.admin;

import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class AtributosAdmin {
    
    private String nombre_usuario, editor, titulo_revista, comentario;
    private int no_suscriptores, no_comentarios;
    private Date fecha_suscripcion, fecha_comentario, fecha_pago;
    private float total, total_cuota;

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    
    public float getTotal_cuota() {
        return total_cuota;
    }

    public void setTotal_cuota(float total_cuota) {
        this.total_cuota = total_cuota;
    }

    

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha_comentario() {
        return fecha_comentario;
    }

    public void setFecha_comentario(Date fecha_comentario) {
        this.fecha_comentario = fecha_comentario;
    }
    
    
    public int getNo_comentarios() {
        return no_comentarios;
    }

    public void setNo_comentarios(int no_comentarios) {
        this.no_comentarios = no_comentarios;
    }

    
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getTitulo_revista() {
        return titulo_revista;
    }

    public void setTitulo_revista(String titulo_revista) {
        this.titulo_revista = titulo_revista;
    }

    public int getNo_suscriptores() {
        return no_suscriptores;
    }

    public void setNo_suscriptores(int no_suscriptores) {
        this.no_suscriptores = no_suscriptores;
    }

    public Date getFecha_suscripcion() {
        return fecha_suscripcion;
    }

    public void setFecha_suscripcion(Date fecha_suscripcion) {
        this.fecha_suscripcion = fecha_suscripcion;
    }
    
    
}
