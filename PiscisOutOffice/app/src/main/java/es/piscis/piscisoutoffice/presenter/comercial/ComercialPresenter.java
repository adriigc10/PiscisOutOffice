package es.piscis.piscisoutoffice.presenter.comercial;

import android.os.StrictMode;

import java.util.Collections;
import java.util.List;

import es.piscis.piscisoutoffice.model.Comparadores.ComparadorClientePorNombre;
import es.piscis.piscisoutoffice.model.Datos.DatosComercial;
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

    // LISTAS
    List<Cliente> clientes;
    List<Visita> visitas;

    // CONSTRUCTOR
    public ComercialPresenter(IContratoComercial.View vista){
        // Necesario para poder establecer la conexion con la BBDD
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.vista = vista;
        operacionesBBDD = new OperacionesBBDD();
        compararPorNombreCliente = new ComparadorClientePorNombre();
    }


    @Override
    public void onAgendaClientesClicked() {
        clientes = operacionesBBDD.buscarAgendaClientesComercial();
        Collections.sort(clientes, compararPorNombreCliente);
        DatosComercial.setClientes(clientes);
        if (clientes.isEmpty()){
            vista.onAgendaClientesVacia();
        } else {
            vista.onMostrarAgendaClientes();
        }
    }

    @Override
    public void onAgendaVisitasClicked() {
/*
    visitas = operaciones.buscarAgendaVisitasComercial();
    Collections.sort(visitas, compararPorHoraVisitas);
    DatosComercial.setVisitas(visitas);
    if(visitas.isEmpty()) {
        vista.onAgendaVisitasVacia();
    } else {
        vista.onMostrarAgendaVisitas();
    }
*/
    }

    @Override
    public void onBuscarArticuloClicked() {

    }

    @Override
    public void onBuscarClicked(String busqueda, boolean buscarCliente) {
/*        if (busqueda.isEmpty()) {
            vista.onBusquedaError();
        } else if (buscarCliente) {
            List<Cliente> clientes;
            clientes = operacionesBBDD.buscarCliente(busqueda);
            DatosComercial.setClientes(clientes);
            if (clientes.isEmpty()) {
                vista.onClienteError();
            } else {
                vista.onAgendaClientesVacia();
            }

        } else if (!buscarCliente) {
            List<Articulo> articulos;
            articulos = operacionesBBDD.buscarArticulo(busqueda);
            DatosComercial.setArticulos(articulos);
            if (articulos.isEmpty()) {
                vista.onArticuloError();
            } else {
                vista.onArticuloBuscado();
            }
            //
        }
*/    }
}
