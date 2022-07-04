package es.piscis.piscisoutoffice.presenter.presupuesto;

import android.os.StrictMode;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Dao.IPresupuestosDAO;
import es.piscis.piscisoutoffice.model.Room.Entidades.Presupuesto;
import es.piscis.piscisoutoffice.view.presupuesto.IContratoPresupuesto;

public class PresupuestoPresenter implements IContratoPresupuesto.Presenter {

    private final IContratoPresupuesto.View vista;

    // ROOM
    IPresupuestosDAO presupuestoDAO;

    List<Presupuesto> presupuestos;

    public PresupuestoPresenter(IContratoPresupuesto.View vista, BaseDeDatos bd) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ROOM
        presupuestoDAO = bd.presupuestosDAO();

        this.vista = vista;
        this.presupuestos = presupuestoDAO.getAllPresupuesto();

        cargarDatos();
    }

    private void cargarDatos() {
        vista.onPresupuestosCargados(presupuestoDAO.getAllPresupuesto());
        vista.onCargaSatisfactoria(presupuestoDAO.getAllPresupuesto().size());
    }

    @Override
    public void onPresupuestoPulsado(int presupuestoIndex) {

    }

    @Override
    public void onRecargarClicked() {

    }
}
