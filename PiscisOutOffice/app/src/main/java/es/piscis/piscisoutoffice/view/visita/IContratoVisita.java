package es.piscis.piscisoutoffice.view.visita;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.model.Room.Entidades.Visita;

public interface IContratoVisita {

    public interface Presenter {
        void onVisitaPulsada(int visitaIndex);
        void onRecargarClicked();
    }

    public interface View {
        void onVisitasCargadas(List<Visita> visitas);
        void onCargaSatisfactoria(int visitasCargadas);
        void onCargaError();
        void abrirDetalleVisita();
    }
}
