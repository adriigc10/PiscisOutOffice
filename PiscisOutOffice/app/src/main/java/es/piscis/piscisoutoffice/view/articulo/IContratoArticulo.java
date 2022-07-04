package es.piscis.piscisoutoffice.view.articulo;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;

public interface IContratoArticulo {

    public interface Presenter {
        /**
         * Metodo que se encarga de recoger el cliente que se ha pulsado
         * @param articuloIndex indice del cliente que se quiere ver en detalle
         */
        void onArticuloPulsado(int articuloIndex);

        void onFiltroBusquedaArticulos(String busqueda);

        void onRecargarClicked();
    }

    public interface View {
        /**
         * Metodo que se encarga de abrir una nueva actividad personalizada para el cliente seleccionado
         */
        void abrirDetalleArticulo();

        /**
         * Metodo que se encarga de generar la nueva actividad con la lista de los clientes de un comercial
         * @param articulos lista con los clientes del comercial
         */
        void onArticulosCargados(List<Articulo> articulos);

        /**
         * Metodo que se encarga de mostrar que ha habido un error en la carga de los clientes
         */
        void onCargaError();

        /**
         * Metodo que se encarga de generar un pop-up con el numero de clientes cargados
         * @param articulosCargados
         */
        void onCargaSatisfactoria(int articulosCargados);
    }
}
