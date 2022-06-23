package es.piscis.piscisoutoffice.presenter.autenticacion;

import android.os.StrictMode;
import android.view.View;

import java.sql.Connection;

import es.piscis.piscisoutoffice.model.Connection.FactoriaDeConexiones;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.view.autenticacion.IContratoAutenticacion;

public class AutenticacionPresenter implements IContratoAutenticacion.Presenter {

    private final IContratoAutenticacion.View vista;
    private final IOperacionesBBDD operacionesBBDD;

    String rol;

    public AutenticacionPresenter(IContratoAutenticacion.View vista) {
        // Necesario para poder establecer la conexion con la BBDD
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.vista = vista;
        operacionesBBDD = new OperacionesBBDD();
        FactoriaDeConexiones.obtenerConexionLocal();
    }

    @Override
    public void onLoginClicked(String dni, String contrasena) {
        Boolean autenticado = operacionesBBDD.autenticarseBBDD(dni, contrasena);
        if (autenticado){
            rol = operacionesBBDD.conocerRol();
            if (rol.equals("comercial")){
                vista.onComercialAutenticado();
            } else if (rol.equals("repartidor")){
                vista.onRepartidorAutenticado();
            }
            else{
                vista.onRolError();
            }
        } else{
            vista.onAutenticacionError();
        }


    }
}
