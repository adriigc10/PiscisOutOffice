package es.piscis.piscisoutoffice.presenter.articulo;

import android.os.StrictMode;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.piscis.piscisoutoffice.model.Datos.DatosTrabajador;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.OperacionesBBDD;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Dao.IArticulosDAO;
import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.view.articulo.IContratoArticulo;

public class ArticuloPresenter implements IContratoArticulo.Presenter{

    private final IContratoArticulo.View vista;

    // ROOM

    IArticulosDAO articulosDAO;

    // Listas

    List<Articulo> articulos;
    List<Articulo> articulosFiltrados;

    public ArticuloPresenter(IContratoArticulo.View vista, BaseDeDatos bd) {
        // Necesario para poder establecer conexion con la base de datos
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ROOM

        articulosDAO = bd.articulosDAO();

        this.vista = vista;
        this.articulos = articulosDAO.getAllArticulos();

        cargarDatos();
    }

    private void cargarDatos() {
        vista.onArticulosCargados(articulosDAO.getAllArticulos());
        vista.onCargaSatisfactoria(articulosDAO.getAllArticulos().size());
    }


    @Override
    public void onArticuloPulsado(int articuloIndex) {
        Articulo articuloPulsado = articulosDAO.getAllArticulos().get(articuloIndex);
        DatosTrabajador.setCodigoArticuloSeleccionado(articuloPulsado.getCodArt());
        vista.abrirDetalleArticulo();
    }

    @Override
    public void onFiltroBusquedaArticulos(String busqueda) {
        articulosFiltrados = new ArrayList<>();

        // Normalizamos la busqueda para que detecte caracteres especiales y los pase a ASCII
        busqueda = Normalizer.normalize(busqueda, Normalizer.Form.NFD);
        // Eliminamos los caracteres ASCII: Ejemplo: https://docs.oracle.com/javase/tutorial/i18n/text/normalizerapi.html
        busqueda = busqueda.replaceAll("[^\\p{ASCII}]", "");

        // Pasamos la busqueda a minuscualas
        Pattern p = Pattern.compile(busqueda, Pattern.CASE_INSENSITIVE);
        Matcher m1;
        Matcher m2;

        for (Articulo a: articulos){
            m1 = p.matcher(String.valueOf(a.getCodArt()));
            m2 = p.matcher(a.getDescripcion());

            // Si la busqueda es positiva, lo anhadimos a la lista de clientes filtrados
            if (m1.find() || m2.find()) {
                articulosFiltrados.add(a);
            }
        }

        articulosDAO.eliminarListaArticulos(articulos);
        articulosDAO.insertarListaArticulos(articulosFiltrados);

        vista.onArticulosCargados(articulosFiltrados);
    }

    @Override
    public void onRecargarClicked() {
        cargarDatos();
    }
}
