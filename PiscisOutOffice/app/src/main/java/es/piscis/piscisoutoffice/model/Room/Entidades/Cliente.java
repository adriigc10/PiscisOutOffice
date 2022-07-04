package es.piscis.piscisoutoffice.model.Room.Entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cliente {

    // ATRIBUTOS

    @PrimaryKey
    private Integer codigoCliente;
    private String nombre;
    private String nombreComercial;
    private String telefono;
    private String dirección;
    private String localidad;

    // CONSTRUCTORES

    public Cliente() { }

    public Cliente(Integer id, String nombre, String nombreComercial, String telefono, String dirección, String localidad) {
        this.codigoCliente = id;
        this.nombre = nombre;
        this.nombreComercial = nombreComercial;
        this.telefono = telefono;
        this.dirección = dirección;
        this.localidad = localidad;
    }

    // GETTERS

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDirección() {
        return dirección;
    }

    public String getLocalidad() {
        return localidad;
    }

    // SETTERS

    public void setCodigoCliente(@NonNull Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    // METODOS AUXILIARES

    @Override
    public String toString() {
        String textoCliente = "";

        textoCliente += "CCliente: " + this.getCodigoCliente() + "\n"
                + "Nombre: " + this.getNombre() + "\n"
                + "Nombre Comercial: " + this.getNombreComercial() + "\n"
                + "Telefono: " + this.getTelefono() + "\n"
                + "Direccion: " + this.getDirección() + "\n";

        return textoCliente;
    }
}
