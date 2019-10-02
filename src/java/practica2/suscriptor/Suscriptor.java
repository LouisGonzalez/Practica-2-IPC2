package practica2.suscriptor;
import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class Suscriptor {
    
    private int id, id_revista;
    private String nombre_usuario, titulo_revista;
    private Date fecha_suscripcion, ultimo_pago, fecha_creacion;
    private byte[] miRevista;

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    
    public String getTitulo_revista() {
        return titulo_revista;
    }

    public void setTitulo_revista(String titulo_revista) {
        this.titulo_revista = titulo_revista;
    }
    
    public Date getUltimo_pago() {
        return ultimo_pago;
    }

    public void setUltimo_pago(Date ultimo_pago) {
        this.ultimo_pago = ultimo_pago;
    }

    
    
    public byte[] getMiRevista() {
        return miRevista;
    }

    public void setMiRevista(byte[] miRevista) {
        this.miRevista = miRevista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_revista() {
        return id_revista;
    }

    public void setId_revista(int id_revista) {
        this.id_revista = id_revista;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public Date getFecha_suscripcion() {
        return fecha_suscripcion;
    }

    public void setFecha_suscripcion(Date fecha_suscripcion) {
        this.fecha_suscripcion = fecha_suscripcion;
    }
    
    
}
