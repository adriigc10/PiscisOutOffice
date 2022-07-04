package es.piscis.piscisoutoffice.presenter.reserva;

import android.os.StrictMode;

import java.util.List;

import es.piscis.piscisoutoffice.model.Datos.DatosTrabajador;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Dao.IReservaDAO;
import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;
import es.piscis.piscisoutoffice.view.reserva.IContratoReserva;

public class ReservaPresenter implements IContratoReserva.Presenter {

    private final IContratoReserva.View vista;

    //ROOM
    IReservaDAO reservasDAO;

    List<Reserva> reservas;

    public ReservaPresenter(IContratoReserva.View vista, BaseDeDatos bd) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //ROOM
        reservasDAO = bd.reservasDAO();

        this.vista = vista;
        this.reservas = reservasDAO.getAllReservas();

        cargarDatos();
    }

    private void cargarDatos() {
        vista.onReservasCargadas(reservasDAO.getAllReservas());
        vista.onCargaSatisfactoria(reservasDAO.getAllReservas().size());
    }

    public void onRecargarClicked() {
        cargarDatos();
    }
}
