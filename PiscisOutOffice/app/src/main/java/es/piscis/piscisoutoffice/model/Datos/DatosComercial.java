package es.piscis.piscisoutoffice.model.Datos;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;

public class DatosComercial {

    // ATRIBUTOS

    public static Integer codigoComercial;
    public static String nombreTrabajador;

    // OBJETOS ACTUALES
    public static Cliente clienteSeleccionado;
    public static Reserva reservaSeleccionada;

    // Listas

    public static List<Cliente> clientes;
    public static List<Articulo> articulos;
    public static List<Reserva> reservas;
    public static List<Factura> facturas;


    //GETTERS

    public static Integer getCodigoComercial() {
        return codigoComercial;
    }

    public static String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public static Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }



    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static List<Articulo> getArticulos() {
        return articulos;
    }

    public static List<Reserva> getReservas() {return reservas; }

    public static List<Factura> getFacturas() { return facturas; }

    // SETTERS

    public static void setCodigoComercial(Integer codigoComercial) {
        DatosComercial.codigoComercial = codigoComercial;
    }

    public static void setNombreTrabajador(String nombreTrabajador) {
        DatosComercial.nombreTrabajador = nombreTrabajador;
    }

    public static void setClienteSeleccionado(Cliente clienteSeleccionado) {
        DatosComercial.clienteSeleccionado = clienteSeleccionado;
    }

    public static void setClientes(List<Cliente> clientes) {
        DatosComercial.clientes = clientes;
    }

    public static void setArticulos(List<Articulo> articulos) {
        DatosComercial.articulos = articulos;
    }

    public static void setReservas(List<Reserva> reservas) {
        DatosComercial.reservas = reservas;
    }

    public static void setFacturas(List<Factura> facturas) {
        DatosComercial.facturas = facturas;
    }
}
