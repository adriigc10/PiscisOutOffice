package es.piscis.piscisoutoffice.model.Datos;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;
import es.piscis.piscisoutoffice.model.Room.Entidades.Visita;

public class DatosTrabajador {

    // ATRIBUTOS

    public static String nombreTrabajador;
    public static Integer codigoComercial;
    public static boolean esComercial;

    // OBJETOS ACTUALES
    public static Integer codigoClienteSeleccionado;
    public static Long codigoArticuloSeleccionado;
    public static Integer codigoVisitaSeleccionada;

    //GETTERS

    public static Integer getCodigoComercial() { return codigoComercial; }

    public static String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public static boolean getEsComercial() {return esComercial; }

    public static Integer getCodigoClienteSeleccionado() {
        return codigoClienteSeleccionado;
    }

    public static Long getCodigoArticuloSeleccionado() { return codigoArticuloSeleccionado; }

    public static Integer getCodigoVisitaSeleccionada() {
        return codigoVisitaSeleccionada;
    }

    // SETTERS

    public static void setCodigoComercial(Integer codigoComercial) {
        DatosTrabajador.codigoComercial = codigoComercial;
    }

    public static void setNombreTrabajador(String nombreTrabajador) {
        DatosTrabajador.nombreTrabajador = nombreTrabajador;
    }

    public static void setEsComercial (boolean esComercial) { DatosTrabajador.esComercial = esComercial; }

    public static void setCodigoClienteSeleccionado(Integer codigoClienteSeleccionado) {
        DatosTrabajador.codigoClienteSeleccionado = codigoClienteSeleccionado;
    }

    public static void setCodigoArticuloSeleccionado(Long codigoArticuloSeleccionado) {
        DatosTrabajador.codigoArticuloSeleccionado = codigoArticuloSeleccionado;
    }

    public static void setCodigoVisitaSeleccionada(Integer codigoVisitaSeleccionada) {
        DatosTrabajador.codigoVisitaSeleccionada = codigoVisitaSeleccionada;
    }
}
