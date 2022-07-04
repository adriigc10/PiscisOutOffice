package es.piscis.piscisoutoffice.model.Room.Entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Presupuesto {

    // ATRIBUTOS

    @PrimaryKey(autoGenerate = true)
    private Integer idPresupuesto;
    private Integer codigoPresupuesto;
    private String referenciaPresupuesto;
    private String fechaPresupuesto;
    private float total;
    private String estado;

    // CONSTRUCTORES

    public Presupuesto() {}

    public Presupuesto(Integer codigoPresupuesto,
                       String referenciaPresupuesto, String fechaPresupuesto,
                       float total, String estado) {
        this.codigoPresupuesto = codigoPresupuesto;
        this.referenciaPresupuesto = referenciaPresupuesto;
        this.fechaPresupuesto = fechaPresupuesto;
        this.total = total;
        this.estado = estado;
    }

    // GETTERS

    public Integer getIdPresupuesto() {
        return idPresupuesto;
    }

    public Integer getCodigoPresupuesto() {
        return codigoPresupuesto;
    }

    public String getReferenciaPresupuesto() {
        return referenciaPresupuesto;
    }

    public String getFechaPresupuesto() {
        return fechaPresupuesto;
    }

    public float getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    // SETTERS

    public void setIdPresupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public void setCodigoPresupuesto(Integer codigoPresupuesto) {
        this.codigoPresupuesto = codigoPresupuesto;
    }

    public void setReferenciaPresupuesto(String referenciaPresupuesto) {
        this.referenciaPresupuesto = referenciaPresupuesto;
    }

    public void setFechaPresupuesto(String fechaPresupuesto) {
        this.fechaPresupuesto = fechaPresupuesto;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
