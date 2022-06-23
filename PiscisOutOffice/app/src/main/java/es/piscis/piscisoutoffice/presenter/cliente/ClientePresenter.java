package es.piscis.piscisoutoffice.presenter.cliente;

import android.os.StrictMode;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.piscis.piscisoutoffice.model.Datos.DatosComercial;
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;

import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.view.cliente.IContratoCliente;

public class ClientePresenter implements IContratoCliente.Presenter {

    private final IContratoCliente.View vista;
    private IOperacionesBBDD operacionesBBDD;

    List<Cliente> clientes;
    List<Cliente> clientesFiltrados;

    public ClientePresenter(IContratoCliente.View vista) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        this.vista = vista;
        this.clientes = DatosComercial.getClientes();
        operacionesBBDD = new OperacionesBBDD();

        cargarDatos();
    }


    @Override
    public void onClientePulsado(int clienteIndex) {
        Cliente clientePulsado = DatosComercial.getClientes().get(clienteIndex);
        DatosComercial.setClienteSeleccionado(clientePulsado);
        vista.abrirDetalleCliente(clientePulsado);
    }



    @Override
    public void onFiltroBusquedaClientesTiempoReal(String busqueda) {

        clientesFiltrados = new ArrayList<>();

        // Normalizamos la busqueda para que detecte caracteres especiales y los pase a ASCII
        busqueda = Normalizer.normalize(busqueda, Normalizer.Form.NFD);
        // Eliminamos los caracteres ASCII: Ejemplo: https://docs.oracle.com/javase/tutorial/i18n/text/normalizerapi.html
        busqueda = busqueda.replaceAll("[^\\p{ASCII}]", "");

        // Pasamos la busqueda a minuscualas
        Pattern p = Pattern.compile(busqueda, Pattern.CASE_INSENSITIVE);
        Matcher m1;
        Matcher m2;

        for (Cliente c : clientes){
            // Buscamos si el nombre del cliente contiene la busqueda introducida en el teléfono
            m1 = p.matcher(c.getNombre());
            m2 = p.matcher(c.getNombreComercial());

            // Si la busqueda es positiva, lo anhadimos a la lista de clientes filtrados
            if (m1.find() || m2.find()) {
                clientesFiltrados.add(c);
            }
        }

        // Establecemos los clientes como los filtrados para la actualizacion del indice
        DatosComercial.setClientes(clientesFiltrados);

        vista.onClientesCargados(clientesFiltrados);
    }

    public void onRecargarClicked() {
        cargarDatos();
    }

    private void cargarDatos() {
        if (!operacionesBBDD.comprobarConexion()){
            vista.onCargaError();
        } else {
            vista.onClientesCargados(DatosComercial.getClientes());
            vista.onCargaSatisfactoria(DatosComercial.getClientes().size());
        }
    }
}
