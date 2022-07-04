package es.piscis.piscisoutoffice.view.presupuesto;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Presupuesto;

public interface IContratoPresupuesto {
    interface Presenter {
        void onPresupuestoPulsado(int presupuestoIndex);
        void onRecargarClicked();
    }

    interface View {
        void onPresupuestosCargados(List<Presupuesto> presupuestos);
        void onCargaSatisfactoria(int presupuestosCargadas);
        void onCargaError();
    }
}
