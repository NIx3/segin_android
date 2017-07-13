package christopher.workshopandroid;

/**
 * Created by Christopher on 24-06-2017.
 */

public class imagenGaleria {
    String titulo, detalle, imagen;

    public imagenGaleria(String titulo, String detalle, String imagen) {
        this.titulo = titulo;
        this.detalle = detalle;
        this.imagen = imagen;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
