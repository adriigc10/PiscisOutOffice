package es.piscis.piscisoutoffice.view.comercial;

import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;

public interface IContratoComercial {

    public interface Presenter{
        void onAgendaClientesClicked();
        void onAgendaVisitasClicked();
        void onBuscarArticuloClicked();
        void onBuscarClicked(String busqueda, boolean buscarCliente);
    }

    public interface View{
        void onMostrarAgendaClientes();
        void onAgendaClientesVacia();
        void onMostrarAgendaVisitas();
        void onAgendaVisitasVacia();
        void onArticuloBuscado();
        void onArticuloError();
    }
}
