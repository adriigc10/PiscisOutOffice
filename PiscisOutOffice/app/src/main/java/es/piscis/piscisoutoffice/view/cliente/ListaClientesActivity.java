package es.piscis.piscisoutoffice.view.cliente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
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
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;
import es.piscis.piscisoutoffice.presenter.cliente.ClientePresenter;
import es.piscis.piscisoutoffice.view.detalleCliente.DetalleClienteActivity;

public class ListaClientesActivity extends AppCompatActivity implements IContratoCliente.View{

    private IContratoCliente.Presenter presenter;

    BaseDeDatos db;

    SearchView searchView;
    ListView listView;
    ClienteArrayAdapter adapter;

    private Toast msgToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        // Necesario para poder establecer una conexion
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // INSTANCIAMOS ROOM
        db = BaseDeDatos.getInstancia(this.getApplicationContext());

        presenter = new ClientePresenter(this, db);
    }

    @Override
    public void onClientesCargados(List<Cliente> clientes) {
        adapter = new ClienteArrayAdapter(ListaClientesActivity.this, 0,  clientes);
        listView = findViewById(R.id.clientesListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> presenter.onClientePulsado(position));

        searchView = findViewById(R.id.sv_buscarCliente);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String busqueda) {
                // Lo dejamos vacio ya que buscamos en tiempo real
                return false;
            }

            @Override
            public boolean onQueryTextChange(String busqueda) {
                presenter.onFiltroBusquedaClientesTiempoReal(busqueda);
                return true;
            }
        });
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
    public void onCargaSatisfactoria(int clientesCargados) {
        String texto = String.format("Se han cargado %d clientes", clientesCargados);
        msgToast = Toast.makeText(this, texto, Toast.LENGTH_SHORT);
        msgToast.show();
    }

    @Override
    public void abrirDetalleCliente() {
        Intent intent = new Intent(this, DetalleClienteActivity.class);
        startActivity(intent);
    }
}