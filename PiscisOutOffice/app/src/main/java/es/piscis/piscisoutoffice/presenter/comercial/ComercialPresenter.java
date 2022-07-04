package es.piscis.piscisoutoffice.presenter.comercial;

import android.os.StrictMode;

import java.util.Collections;
import java.util.List;

import es.piscis.piscisoutoffice.model.Comparadores.ComparadorClientePorNombre;
import es.piscis.piscisoutoffice.model.Comparadores.ComparadorVisitasPorFecha;
import es.piscis.piscisoutoffice.model.Connection.FactoriaDeConexiones;
import es.piscis.piscisoutoffice.model.Datos.DatosTrabajador;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Dao.IArticulosDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IClienteDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IVisitasDAO;
import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;
import es.piscis.piscisoutoffice.model.Room.Entidades.Visita;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.view.comercial.IContratoComercial;

public class ComercialPresenter implements IContratoComercial.Presenter {

    private final IContratoComercial.View vista;
    private final IOperacionesBBDD operacionesBBDD;
    private final ComparadorClientePorNombre compararPorNombreCliente;
    private final ComparadorVisitasPorFecha compararPorFechaVisitas;

    // ROOM

    IArticulosDAO articulosDAO;
    IClienteDAO clientesDAO;
    IVisitasDAO visitasDAO;

    // LISTAS
    List<Cliente> clientes;
    List<Visita> visitas;
    List<Articulo> articulos;

    // CONSTRUCTOR
    public ComercialPresenter(IContratoComercial.View vista, BaseDeDatos db){
        // Necesario para poder establecer la conexion con la BBDD
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ROOM
        articulosDAO = db.articulosDAO();
        clientesDAO = db.clienteDAO();
        visitasDAO = db.visitasDAO();

        this.vista = vista;
        operacionesBBDD = new OperacionesBBDD();
        compararPorNombreCliente = new ComparadorClientePorNombre();
        compararPorFechaVisitas = new ComparadorVisitasPorFecha();

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
    public void onAgendaClientesClicked() {
        onActualizarClicked(); // volvemos a comprobar si hay conexion con la bbdd

        // Obtenemos los clientes de la base de datos de la empresa
        clientes = operacionesBBDD.buscarAgendaClientesComercial();
        // Los ordenamos por nombre
        Collections.sort(clientes, compararPorNombreCliente);
        // Los insertamos en una base de datos local
        clientesDAO.insertarListaClientes(clientes);

        if (clientes.isEmpty()){
            vista.onAgendaClientesVacia();
        } else {
            vista.onMostrarAgendaClientes();
        }
    }

    @Override
    public void onAgendaVisitasClicked() {
        onActualizarClicked(); // volvemos a comprobar si hay conexion con la bbdd

        // Obtnemos la visita de la base de datos
        visitas = operacionesBBDD.buscarAgendaVisitasComercial();
        // Las ordenamos por fecha
        Collections.sort(visitas, compararPorFechaVisitas);
        // Las insertamos en la base de datos local
        visitasDAO.insertarListaVisitas(visitas);

        if(visitas.isEmpty()) {
            vista.onAgendaVisitasVacia();
        } else {
            vista.onMostrarAgendaVisitas();
        }
    }

    @Override
    public void onBuscarArticuloClicked() {
        onActualizarClicked(); // volvemos a comprobar si hay conexion con la bbdd

        // Obtenemos los articulos de la base de datos
        articulos = operacionesBBDD.buscarArticulo();

        articulosDAO.eliminarListaArticulos(articulosDAO.getAllArticulos());
        articulosDAO.insertarListaArticulos(articulos);

    if(articulos.isEmpty()) {
            vista.onArticulosVacio();
        } else {
            vista.onMostrarArticulos();
        }
    }
}
