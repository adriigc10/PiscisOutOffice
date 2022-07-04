package es.piscis.piscisoutoffice.presenter.factura;

import android.os.StrictMode;
import android.util.Log;

import java.util.List;

import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Dao.IFacturasDAO;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.view.factura.IContratoFactura;

public class FacturaPresenter implements IContratoFactura.Presenter {

    private final IContratoFactura.View vista;

    // ROOM
    IFacturasDAO facturasDAO;

    List<Factura> facturas;

    public FacturaPresenter(IContratoFactura.View vista, BaseDeDatos bd) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ROOM
        facturasDAO = bd.facturasDAO();

        this.vista = vista;
        this.facturas = facturasDAO.getAllFacturas();

        cargarDatos();


    }

    private void cargarDatos() {
        vista.onFacturasCargadas(facturasDAO.getAllFacturas());
        vista.onCargaSatisfactoria(facturasDAO.getAllFacturas().size());
    }

    @Override
    public void onFacturaPulsado(int facturaIndex) {
        Factura facturaPulsada = facturasDAO.getAllFacturas().get(facturaIndex);
        Log.d("DEBUG", "ha entrado en facturas presenter");
        vista.descargarFactura(facturaPulsada.getUrlDescarga());
    }

    @Override
    public void onRecargarClicked() {

    }
}
