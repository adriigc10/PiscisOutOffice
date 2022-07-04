package es.piscis.piscisoutoffice.view.comercial;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;

public interface IContratoComercial {

    public interface Presenter{
        void onAgendaClientesClicked();
        void onAgendaVisitasClicked();
        void onBuscarArticuloClicked();

        // Errores de conexion
        void onActualizarClicked();
    }

    public interface View{
        void onMostrarAgendaClientes();
        void onAgendaClientesVacia();
        void onMostrarAgendaVisitas();
        void onAgendaVisitasVacia();
        void onMostrarArticulos();
        void onArticulosVacio();

        // Errores de conexion
        void onConexionInternetError();
        void onConexionBBDDError();
        boolean hasInternetConnection();
    }
}
