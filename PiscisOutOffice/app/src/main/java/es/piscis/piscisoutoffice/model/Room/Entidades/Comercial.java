package es.piscis.piscisoutoffice.model.Room.Entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Comercial extends Trabajador{

    //ATRIBUTOS

    @PrimaryKey
    private Integer codigoComercial;

    // CONSTRUCTORES

    public Comercial() { }

    public Comercial(String dni, String nombre, String apellidos,
                     String contrasenha, int codigoComercial){
        super(dni, nombre, apellidos, contrasenha);
        this.codigoComercial = codigoComercial;
    }

    // GETTERS

    public Integer getCodigoComercial() {
        return codigoComercial;
    }

    @Override
    public String getDni() {
        return super.getDni();
    }

    public String getNombreComercial() {
        return super.getNombre();
    }

    public String getApellidosComercial() {
        return super.getApellidos();
    }

    @Override
    public String getContrasenha() {
        return super.getContrasenha();
    }

    // SETTERS


    public void setCodigoComercial(Integer codigoComercial) {
        this.codigoComercial = codigoComercial;
    }

    @Override
    public void setDni(String dni) {
        super.setDni(dni);
    }

    public void setNombreComercial(String nombreComercial) {
        super.setNombre(nombreComercial);
    }

    public void setApellidosComercial(String apellidosComercial) {
        super.setApellidos(apellidosComercial);
    }

    @Override
    public void setContrasenha(String contrasenha) {
        super.setContrasenha(contrasenha);
    }
}
