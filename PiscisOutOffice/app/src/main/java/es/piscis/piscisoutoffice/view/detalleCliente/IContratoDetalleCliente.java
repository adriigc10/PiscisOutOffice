package es.piscis.piscisoutoffice.view.detalleCliente;

public interface IContratoDetalleCliente {

    public interface Presenter {
        void onFacturasClicked();
        void onPresupuestosClicked();
        void onReservasClicked();

        // Errores de conexion
        void onActualizarClicked();
    }

    public interface View {
        void onMostrarFacturasCliente();
        void onFacturasClienteVacias();
        void onMostrarPresupuestosCliente();
        void onPresupuestosClienteVacios();
        void onMostrarReservasCliente();
        void onReservasClienteVacias();

        // Errores de conexion
        void onConexionInternetError();
        void onConexionBBDDError();
        boolean hasInternetConnection();
    }
}
