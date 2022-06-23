package es.piscis.piscisoutoffice.model.OperacionesBBDD;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;

public interface IOperacionesBBDD {

    boolean comprobarConexion();

    boolean autenticarseBBDD(String dni, String contrasena);

    String conocerRol();

    List<Cliente> buscarAgendaClientesComercial();

    List<Cliente> buscarCliente(String busqueda);

    List<Articulo> buscarArticulo(String busqueda);

    List<Reserva> buscarReservasCliente();

    List<Factura> buscarFacturasCliente();
}
