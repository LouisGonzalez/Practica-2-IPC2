package practica2.reportes;

import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class Atributos {
    
    private String nombre_usuario, comentario, titulo_revista;
    private Date fecha_creado, ultimo_pago;
    private float total;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    

    public Date getUltimo_pago() {
        return ultimo_pago;
    }

    public void setUltimo_pago(Date ultimo_pago) {
        this.ultimo_pago = ultimo_pago;
    }

    
    
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTitulo_revista() {
        return titulo_revista;
    }

    public void setTitulo_revista(String titulo_revista) {
        this.titulo_revista = titulo_revista;
    }

    public Date getFecha_creado() {
        return fecha_creado;
    }

    public void setFecha_creado(Date fecha_creado) {
        this.fecha_creado = fecha_creado;
    }
    
    
}
