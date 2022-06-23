package es.piscis.piscisoutoffice.presenter.reserva;

import android.os.StrictMode;

import java.util.List;

import es.piscis.piscisoutoffice.model.Datos.DatosComercial;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;
import es.piscis.piscisoutoffice.view.reserva.IContratoReserva;

public class ReservaPresenter implements IContratoReserva.Presenter {

    private final IContratoReserva.View vista;
    private final IOperacionesBBDD operacionesBBDD;

    List<Reserva> reservas;
    List<Reserva> reservasFiltrada;

    public ReservaPresenter(IContratoReserva.View vista) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.vista = vista;
        this.reservas = DatosComercial.getReservas();
        operacionesBBDD = new OperacionesBBDD();

        cargarDatos();
    }

    private void cargarDatos() {
        if (!operacionesBBDD.comprobarConexion()){
            vista.onCargaError();
        } else {
            vista.onReservasCargadas(DatosComercial.getReservas());
            vista.onCargaSatisfactoria(DatosComercial.getReservas().size());
        }
    }

    @Override
    public void onReservaPulsado(int reservaIndex) {

    }

    public void onRecargarClicked() {
        cargarDatos();
    }
}
