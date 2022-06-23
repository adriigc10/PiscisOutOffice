package es.piscis.piscisoutoffice.presenter.factura;

import android.os.StrictMode;
import android.util.Log;

import androidx.room.Dao;

import java.util.List;

import es.piscis.piscisoutoffice.model.Datos.DatosComercial;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.view.factura.IContratoFactura;

public class FacturaPresenter implements IContratoFactura.Presenter {

    private final IContratoFactura.View vista;
    private final IOperacionesBBDD operacionesBBDD;

    List<Factura> facturas;

    public FacturaPresenter(IContratoFactura.View vista) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.vista = vista;
        this.facturas = DatosComercial.getFacturas();
        operacionesBBDD = new OperacionesBBDD();

        cargarDatos();
    }

    private void cargarDatos() {
        if (!operacionesBBDD.comprobarConexion()){
            vista.onCargaError();
        } else {
            vista.onFacturasCargadas(DatosComercial.getFacturas());
            vista.onCargaSatisfactoria(DatosComercial.getFacturas().size());
        }
    }

    @Override
    public void onFacturaPulsado(int facturaIndex) {
        Factura facturaPulsada = DatosComercial.getFacturas().get(facturaIndex);
        Log.d("DEBUG", "ha entrado en facturas presenter");
        vista.descargarFactura(facturaPulsada.getUrlDescarga());
    }

    @Override
    public void onRecargarClicked() {

    }
}
