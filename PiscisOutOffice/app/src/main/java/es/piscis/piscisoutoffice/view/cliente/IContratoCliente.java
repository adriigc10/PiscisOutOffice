package es.piscis.piscisoutoffice.view.cliente;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;

public interface IContratoCliente {
    public interface Presenter {
        /**
         * Metodo que se encarga de recoger el cliente que se ha pulsado
         * @param clienteIndex indice del cliente que se quiere ver en detalle
         */
        void onClientePulsado(int clienteIndex);

        void onFiltroBusquedaClientesTiempoReal(String busqueda);

        void onRecargarClicked();
    }

    public interface View {
        /**
         * Metodo que se encarga de abrir una nueva actividad personalizada para el cliente seleccionado
         */
        void abrirDetalleCliente();

        /**
         * Metodo que se encarga de generar la nueva actividad con la lista de los clientes de un comercial
         * @param clientes lista con los clientes del comercial
         */
        void onClientesCargados(List<Cliente> clientes);

        /**
         * Metodo que se encarga de mostrar que ha habido un error en la carga de los clientes
         */
        void onCargaError();

        /**
         * Metodo que se encarga de generar un pop-up con el numero de clientes cargados
         * @param clientesCargados
         */
        void onCargaSatisfactoria(int clientesCargados);
    }
}
