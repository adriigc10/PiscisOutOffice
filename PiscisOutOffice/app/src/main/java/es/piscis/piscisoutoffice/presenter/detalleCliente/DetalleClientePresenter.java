package es.piscis.piscisoutoffice.presenter.detalleCliente;

import android.os.StrictMode;

import java.util.List;

import es.piscis.piscisoutoffice.model.Datos.DatosComercial;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.view.detalleCliente.IContratoDetalleCliente;

public class DetalleClientePresenter implements IContratoDetalleCliente.Presenter {

    // ATRIBUTOS

    private final IContratoDetalleCliente.View vista;
    private final IOperacionesBBDD operacionesBBDD;

    // LISTAS

    List<Reserva> reservas;
    List<Factura> facturas;

    // CONSTRUCTOR
    public DetalleClientePresenter(IContratoDetalleCliente.View vista) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.vista = vista;
        operacionesBBDD = new OperacionesBBDD();
    }

    @Override
    public void onFacturasClicked() {
        facturas = operacionesBBDD.buscarFacturasCliente();
        DatosComercial.setFacturas(facturas);
        if (facturas.isEmpty()){
            vista.onFacturasClienteVacias();
        } else {
            vista.onMostrarFacturasCliente();
        }
    }

    @Override
    public void onPresupuestosClicked() {

    }

    @Override
    public void onReservasClicked() {
        reservas = operacionesBBDD.buscarReservasCliente();
        DatosComercial.setReservas(reservas);
        if (reservas.isEmpty()) {
            vista.onReservasClienteVacias();
        } else {
            vista.onMostrarReservasCliente();
        }
   }
}
