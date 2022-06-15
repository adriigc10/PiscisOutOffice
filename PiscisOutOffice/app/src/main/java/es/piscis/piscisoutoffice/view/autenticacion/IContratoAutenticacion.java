package es.piscis.piscisoutoffice.view.autenticacion;

public interface IContratoAutenticacion {
    public interface Presenter{
        void onLoginClicked(String dni, String contrasena);
    }

    public interface View {
        void onComercialAutenticado();
        void onRepartidorAutenticado();
        void onAutenticacionError();
        void onRolError();
    }
}
