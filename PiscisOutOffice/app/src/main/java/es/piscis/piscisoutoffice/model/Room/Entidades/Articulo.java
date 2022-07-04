package es.piscis.piscisoutoffice.model.Room.Entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Articulo {

    // ATRIBUTOS

    @PrimaryKey
    private Long codArt;
    private String descripcion;
    private Float pvp;
//    private Integer stock;
//    private List<Tarifa> tarifas;
//    private String urlDescarga;

    // CONSTRUCTORES

    public Articulo() { }

    public Articulo(Long codArt, String descripcion, Float pvp) {
        this.codArt = codArt;
        this.descripcion = descripcion;
        this.pvp = pvp;
    }

    // GETTERS

    public Long getCodArt() {
        return codArt;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Float getPvp() {
        return pvp;
    }
/*

    public Integer getStock() {
        return stock;
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }

    public String getUrlDescarga() {
        return urlDescarga;
    }
*/


    // SETTERS

    public void setCodArt(Long codArt) {
        this.codArt = codArt;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPvp(Float pvp) {
        this.pvp = pvp;
    }
/*

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setTarifas(List<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }

    public void setUrlDescarga(String urlDescarga) {
        this.urlDescarga = urlDescarga;
    }
*/
}
