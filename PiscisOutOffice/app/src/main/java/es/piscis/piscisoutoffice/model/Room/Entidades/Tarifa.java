package es.piscis.piscisoutoffice.model.Room.Entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tarifa {

    // ATRIBUTOS

    @PrimaryKey
    private Integer codigoTarifa;
    private String nombreTarifa;
    private Float precioTarifa;

    // CONSTRUCTORES

    public Tarifa() { }

    public Tarifa(Integer codigoTarifa, String nombreTarifa, Float precioTarifa) {
        this.nombreTarifa = nombreTarifa;
        this.codigoTarifa = codigoTarifa;
        this.precioTarifa = precioTarifa;
    }

    // GETTERS

    public Integer getCodigoTarifa() {
        return codigoTarifa;
    }

    public String getNombreTarifa() {
        return nombreTarifa;
    }

    public Float getPrecioTarifa() { return precioTarifa; }

    // SETTERS

    public void setCodigoTarifa(Integer codigoTarifa) {
        this.codigoTarifa = codigoTarifa;
    }

    public void setNombreTarifa(String nombreTarifa) {
        this.nombreTarifa = nombreTarifa;
    }

    public void setPrecioTarifa(Float precioTarifa) { this.precioTarifa = precioTarifa; }
}
