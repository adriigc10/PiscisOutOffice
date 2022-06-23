package es.piscis.piscisoutoffice.view.factura;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;

public interface IContratoFactura {

    public interface Presenter {
        void onFacturaPulsado(int facturaIndex);
        void onRecargarClicked();
    }

    public interface View {
        void onFacturasCargadas(List<Factura> facturas);
        void onCargaSatisfactoria(int facturasCargadas);
        void onCargaError();
        void descargarFactura(String urlDescarga);
    }
}
