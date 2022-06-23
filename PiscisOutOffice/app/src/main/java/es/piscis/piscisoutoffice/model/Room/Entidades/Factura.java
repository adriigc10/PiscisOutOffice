package es.piscis.piscisoutoffice.model.Room.Entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import net.sourceforge.jtds.jdbc.DateTime;

import java.text.DateFormat;
import java.util.Date;

@Entity
public class Factura {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String fechaDocumento;
    private Float totalFactura;
    private Integer numeroFra;
    private String urlDescarga;

    // CONSTRUCTOR

    public Factura(String fechaDocumento, Float totalFactura, Integer numeroFra, String urlDescarga) {
        this.fechaDocumento = fechaDocumento;
        this.totalFactura = totalFactura;
        this.numeroFra = numeroFra;
        this.urlDescarga = urlDescarga;
    }

    // GETTERS

    public Integer getId(){ return id; }

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public Float getTotalFactura() {
        return totalFactura;
    }

    public Integer getNumeroFra() {
        return numeroFra;
    }

    public String getUrlDescarga(){ return urlDescarga; }

    // SETTERS

    public void setId(Integer id) { this.id = id; }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public void setTotalFactura(Float totalFactura) {
        this.totalFactura = totalFactura;
    }

    public void setNumeroFra(Integer numeroFra) {
        this.numeroFra = numeroFra;
    }

    public void setUrlDescarga(String urlDescarga) { this.urlDescarga = urlDescarga; }
}
