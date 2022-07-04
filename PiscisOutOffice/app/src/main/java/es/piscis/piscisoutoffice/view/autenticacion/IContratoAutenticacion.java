package es.piscis.piscisoutoffice.view.autenticacion;

import es.piscis.piscisoutoffice.model.Room.Entidades.Comercial;
import es.piscis.piscisoutoffice.model.Room.Entidades.Repartidor;

public interface IContratoAutenticacion {
    interface Presenter{
        void onLoginClicked(String dni, String contrasena);

        // Errores de conexion
        void onActualizarClicked();
    }

    interface View {
        void onComercialAutenticado();
        void onRepartidorAutenticado();
        void onAutenticacionError();
        void onRolError();

        // Errores de conexion
        void onConexionInternetError();
        void onConexionBBDDError();
        boolean hasInternetConnection();

    }
}
