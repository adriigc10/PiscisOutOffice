package es.piscis.piscisoutoffice.model.OperacionesBBDD;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.model.Room.Entidades.Presupuesto;
import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;
import es.piscis.piscisoutoffice.model.Room.Entidades.Trabajador;
import es.piscis.piscisoutoffice.model.Room.Entidades.Visita;

public interface IOperacionesBBDD {

    boolean hayConexionBBDD();

    Trabajador buscarTrabajador(String dni, String contrasena);

    List<Cliente> buscarAgendaClientesComercial();

    List<Visita> buscarAgendaVisitasComercial();

    List<Articulo> buscarArticulo();

    Articulo buscarDetallesArticulo(Integer codArt);

    List<Reserva> buscarReservasCliente();

    List<Factura> buscarFacturasCliente();

    List<Presupuesto> buscarPresupuestosCliente();
}
