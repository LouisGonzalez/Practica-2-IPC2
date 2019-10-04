package practica2.likes;

import java.sql.Date;

/**
 *
 * @author luisGonzalez
 */
public class Likes {
    
    private int id, id_revista;
    private String nombre_usuario;
    private Date fecha_like;

    public Date getFecha_like() {
        return fecha_like;
    }

    public void setFecha_like(Date fecha_like) {
        this.fecha_like = fecha_like;
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
    
    
}
