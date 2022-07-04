package es.piscis.piscisoutoffice.view.reserva;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;

public interface IContratoReserva {

    public interface Presenter {
        void onRecargarClicked();
    }

    public interface View {
        void onReservasCargadas(List<Reserva> reservas);
        void onCargaSatisfactoria(int reservasCargadas);
        void onCargaError();
    }
}
