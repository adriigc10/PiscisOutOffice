package es.piscis.piscisoutoffice.model.Room.Entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Reserva {
    // ATRIBUTOS

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private Integer codigoArticulo;
    private String codigoBa;
    private String nombreArticulo;
    private Integer unidades;
    private String comentario;
    private String UrlDescarga;

    // CONSTRUCTOR

    public Reserva(Integer codigoArticulo, String codigoBa, String nombreArticulo,
                   Integer unidades, String comentario) {
        this.codigoArticulo = codigoArticulo;
        this.codigoBa = codigoBa;
        this.nombreArticulo = nombreArticulo;
        this.unidades = unidades;
        this.comentario = comentario;
        UrlDescarga = "https://www.almacenespiscis.com/images/articulos/"
                + codigoBa + ".jpg";
    }

    //GETTERS

    public Integer getId() { return id; }

    public Integer getCodigoArticulo() {
        return codigoArticulo;
    }

    public String getCodigoBa() {
        return codigoBa;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public String getComentario() { return comentario;}

    public String getUrlDescarga() {
        return UrlDescarga;
    }

    // SETTERS

    public void setId (Integer id) { this.id = id; }

    public void setCodigoArticulo(Integer codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public void setCodigoBa(String codigoBa) {
        this.codigoBa = codigoBa;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public void setComentario(String comentario) { this.comentario = comentario; }

    public void setUrlDescarga(String urlDescarga) {
        UrlDescarga = urlDescarga;
    }
}
