package es.piscis.piscisoutoffice.view.articulo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.presenter.articulo.ArticuloPresenter;

public class ListaArticulosActivity extends AppCompatActivity implements IContratoArticulo.View{

    private IContratoArticulo.Presenter presenter;

    BaseDeDatos db;

    private SearchView searchView;
    private ListView listView;
    private ArticuloArrayAdapter adapter;

    private Toast msgToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_articulos);

        // Necesario para poder establecer una conexion
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // INSTANCIAMOS ROOM
        db = BaseDeDatos.getInstancia(this.getApplicationContext());

        presenter = new ArticuloPresenter(this, db);
    }

    @Override
    public void onArticulosCargados(List<Articulo> articulos) {
        adapter = new ArticuloArrayAdapter(ListaArticulosActivity.this, 0, articulos);
        listView = findViewById(R.id.articulosListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> presenter.onArticuloPulsado(position));
        searchView = findViewById(R.id.sv_buscarCliente2);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String busqueda) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String busqueda) {
                presenter.onFiltroBusquedaArticulos(busqueda);
                return true;
            }
        });
    }

    @Override
    public void abrirDetalleArticulo() {
//        Intent intent = new Intent(this, DetalleArticuloActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onCargaError() {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("Error de conexión");
        alert.setMessage("Compruebe que está conectado a una red WI-FI o red móvil y pulse 'ACTUALIZAR'");
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"Actualizar", (dialog, which) ->
                presenter.onRecargarClicked()
        );
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "Cerrar", (dialogInterface, i) -> dialogInterface.dismiss());
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = alert.getWindow().getAttributes();

        wmlp.gravity = Gravity.CENTER;
        alert.show();
    }

    @Override
    public void onCargaSatisfactoria(int articulosCargados) {
        String texto = String.format("Se han cargado %d articulos", articulosCargados);
        msgToast = Toast.makeText(this, texto, Toast.LENGTH_SHORT);
        msgToast.show();
    }
}