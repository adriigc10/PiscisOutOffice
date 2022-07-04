package es.piscis.piscisoutoffice.presenter.detalleCliente;

import android.os.StrictMode;

import java.util.List;

import es.piscis.piscisoutoffice.model.Connection.FactoriaDeConexiones;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Dao.IFacturasDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IPresupuestosDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IReservaDAO;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.model.Room.Entidades.Presupuesto;
import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.view.detalleCliente.IContratoDetalleCliente;

public class DetalleClientePresenter implements IContratoDetalleCliente.Presenter {

    // ATRIBUTOS

    private final IContratoDetalleCliente.View vista;
    private final IOperacionesBBDD operacionesBBDD;

    // ROOM
    IFacturasDAO facturasDAO;
    IPresupuestosDAO presupuestosDAO;
    IReservaDAO reservasDAO;

    // LISTAS

    List<Reserva> reservas;
    List<Factura> facturas;
    List<Presupuesto> presupuestos;

    // CONSTRUCTOR
    public DetalleClientePresenter(IContratoDetalleCliente.View vista, BaseDeDatos bd) {
        // Necesario para poder establecer conexion con la base de datos
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ROOM
        facturasDAO = bd.facturasDAO();
        presupuestosDAO = bd.presupuestosDAO();
        reservasDAO = bd.reservasDAO();

        this.vista = vista;
        operacionesBBDD = new OperacionesBBDD();

        // Comprobamos que hay conexion para saber si podemos obtener la conexion con la BBDD
        comprobarConexionInternet();
        // Comprbamos si hay conexion con la base de datos
        comprobarConexionBBDD();
    }

    @Override
    public void onActualizarClicked() {
        comprobarConexionInternet();
        comprobarConexionBBDD();
    }

    private void comprobarConexionInternet() {

        if (!vista.hasInternetConnection()) {
            vista.onConexionInternetError();
        } else {
            FactoriaDeConexiones.obtenerConexionLocal();
        }
    }

    private void comprobarConexionBBDD() {
        if (!operacionesBBDD.hayConexionBBDD()) {
            vista.onConexionBBDDError();
        }
    }

    @Override
    public void onFacturasClicked() {
        onActualizarClicked(); // volvemos a comprobar si hay conexion con la bbdd

        facturas = operacionesBBDD.buscarFacturasCliente();

        facturasDAO.eliminarListaFacturas(facturasDAO.getAllFacturas());
        facturasDAO.insertarListaFacturas(facturas);

        if (facturas.isEmpty()){
            vista.onFacturasClienteVacias();
        } else {
            vista.onMostrarFacturasCliente();
        }
    }

    @Override
    public void onPresupuestosClicked() {
        onActualizarClicked(); // volvemos a comprobar si hay conexion con la bbdd

        presupuestos = operacionesBBDD.buscarPresupuestosCliente();

        presupuestosDAO.eliminarListaPresupuestos(presupuestosDAO.getAllPresupuesto());
        presupuestosDAO.insertarListaPresupuestos(presupuestos);

        if(presupuestos.isEmpty()) {
            vista.onPresupuestosClienteVacios();
        } else {
            vista.onMostrarPresupuestosCliente();
        }
    }

    @Override
    public void onReservasClicked() {
        onActualizarClicked(); // volvemos a comprobar si hay conexion con la bbdd

        reservas = operacionesBBDD.buscarReservasCliente();

        reservasDAO.eliminarListaReservas(reservasDAO.getAllReservas());
        reservasDAO.insertarListaReservas(reservas);

        if (reservas.isEmpty()) {
            vista.onReservasClienteVacias();
        } else {
            vista.onMostrarReservasCliente();
        }
   }
}
