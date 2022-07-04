package es.piscis.piscisoutoffice.presenter.visita;

import android.os.StrictMode;

import java.util.List;

import es.piscis.piscisoutoffice.model.Datos.DatosTrabajador;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Dao.IVisitasDAO;
import es.piscis.piscisoutoffice.model.Room.Entidades.Visita;
import es.piscis.piscisoutoffice.view.visita.IContratoVisita;

public class VisitaPresenter implements IContratoVisita.Presenter {

    private final IContratoVisita.View vista;

    // ROOM

    IVisitasDAO visitaDAO;

    List<Visita> visitas;

    public VisitaPresenter(IContratoVisita.View view, BaseDeDatos db){
        // Necesario para poder establecer conexion con la base de datos
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ROOM
        visitaDAO = db.visitasDAO();

        this.vista = view;
        this.visitas = visitaDAO.getAllVisitas();

        cargarDatos();
    }

    private void cargarDatos() {
        vista.onVisitasCargadas(visitaDAO.getAllVisitas());
        vista.onCargaSatisfactoria(visitaDAO.getAllVisitas().size());
    }

    @Override
    public void onVisitaPulsada(int visitaIndex) {
        Visita visita = visitaDAO.getAllVisitas().get(visitaIndex);
        DatosTrabajador.setCodigoVisitaSeleccionada(visita.getIdVisita());
        vista.abrirDetalleVisita();
    }


    @Override
    public void onRecargarClicked() {
        cargarDatos();
    }
}
