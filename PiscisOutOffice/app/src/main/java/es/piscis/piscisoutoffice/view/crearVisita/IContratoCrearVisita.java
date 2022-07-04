package es.piscis.piscisoutoffice.view.crearVisita;

public interface IContratoCrearVisita {
    interface Presenter {
        void onCrearVisitaClicked();

        // Errores de conexion
        void onActualizarClicked();
    }

    interface View {


        // Errores de conexion
        void onConexionInternetError();
        void onConexionBBDDError();
        boolean hasInternetConnection();
    }
}
