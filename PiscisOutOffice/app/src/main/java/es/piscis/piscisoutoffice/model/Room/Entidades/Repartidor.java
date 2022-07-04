package es.piscis.piscisoutoffice.model.Room.Entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Repartidor extends Trabajador{

    // ATRIBUTOS

    @PrimaryKey
    @NonNull
    String dni;

    // CONSTRUCTOR

    public Repartidor() { }

    public Repartidor(String dni, String nombre, String apellidos,
                     String contrasenha){
        super(dni, nombre, apellidos, contrasenha);
    }

    // GETTERS

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
