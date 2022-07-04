package es.piscis.piscisoutoffice.presenter.autenticacion;

import android.os.StrictMode;

import es.piscis.piscisoutoffice.model.Connection.FactoriaDeConexiones;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Dao.IComercialDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IRepartidorDAO;
import es.piscis.piscisoutoffice.model.Room.Entidades.Comercial;
import es.piscis.piscisoutoffice.model.Room.Entidades.Repartidor;
import es.piscis.piscisoutoffice.model.Room.Entidades.Trabajador;
import es.piscis.piscisoutoffice.view.autenticacion.IContratoAutenticacion;

public class AutenticacionPresenter implements IContratoAutenticacion.Presenter  {

    private final IContratoAutenticacion.View vista;
    private final IOperacionesBBDD operacionesBBDD;

    // ROOM
    IComercialDAO comercialDAO;
    IRepartidorDAO repartidorDAO;

    public AutenticacionPresenter(IContratoAutenticacion.View vista, BaseDeDatos db) {
        // Necesario para poder establecer la conexion con la BBDD
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ROOM
        comercialDAO = db.comercialDAO();
        repartidorDAO = db.repartidorDAO();

        this.vista = vista;
        operacionesBBDD = new OperacionesBBDD();

        // Comprobamos que hay conexion para saber si podemos obtener la conexion con la BBDD
        comprobarConexionInternet();
        // Comprbamos si hay conexion con la base de datos
//        comprobarConexionBBDD();
    }

    @Override
    public void onLoginClicked(String dni, String contrasena) {
        onActualizarClicked(); // volvemos a comprobar si hay conexion con la bbdd

        Trabajador trabajador = operacionesBBDD.buscarTrabajador(dni, contrasena);

        if (trabajador instanceof Comercial){ // El trabajador es comercial
            comercialDAO.insertarComercial((Comercial) trabajador);
            vista.onComercialAutenticado();
        } else if (trabajador instanceof Repartidor){ // El trabajdor es repartidor
            repartidorDAO.insertarRepartidor((Repartidor) trabajador);
            vista.onRepartidorAutenticado();
        } else { // No es ni repartidor ni comercial
            vista.onAutenticacionError();
        }
    }

    @Override
    public void onActualizarClicked() {
        comprobarConexionInternet();
        comprobarConexionBBDD();
    }

    private void comprobarConexionInternet() {

        if (!vista.hasInternetConnection()) {
            vista.onConexionInternetError();
        } else {
            FactoriaDeConexiones.obtenerConexionLocal();
        }
    }

    private void comprobarConexionBBDD() {
        if (!operacionesBBDD.hayConexionBBDD()) {
            vista.onConexionBBDDError();
        }
    }
}
