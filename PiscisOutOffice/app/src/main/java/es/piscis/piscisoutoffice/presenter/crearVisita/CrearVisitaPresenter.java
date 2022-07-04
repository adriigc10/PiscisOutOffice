package es.piscis.piscisoutoffice.presenter.crearVisita;

import android.os.StrictMode;

import java.util.List;

import es.piscis.piscisoutoffice.model.Connection.FactoriaDeConexiones;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Dao.IClienteDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IVisitasDAO;
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;
import es.piscis.piscisoutoffice.view.crearVisita.IContratoCrearVisita;

public class CrearVisitaPresenter implements IContratoCrearVisita.Presenter {

    private final IContratoCrearVisita.View vista;
    private final IOperacionesBBDD operacionesBBDD;

    private IClienteDAO clienteDAO;
    private IVisitasDAO visitasDAO;

    private List<Cliente> clientes;


    public CrearVisitaPresenter(IContratoCrearVisita.View vista, BaseDeDatos db) {
        // Necesario para poder establecer la conexion con la BBDD
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ROOM
        clienteDAO = db.clienteDAO();
        visitasDAO = db.visitasDAO();

        this.vista = vista;
        operacionesBBDD = new OperacionesBBDD();

        // Comprobamos que hay conexion para saber si podemos obtener la conexion con la BBDD
        comprobarConexionInternet();
        // Comprbamos si hay conexion con la base de datos
        comprobarConexionBBDD();
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

    @Override
    public void onCrearVisitaClicked() {

    }
}
