package es.piscis.piscisoutoffice.view.comercial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.presenter.comercial.ComercialPresenter;
import es.piscis.piscisoutoffice.view.articulo.ListaArticulosActivity;
import es.piscis.piscisoutoffice.view.cliente.ListaClientesActivity;
import es.piscis.piscisoutoffice.view.visita.ListaVisitasActivity;

public class MenuPrincipalComercialActivity extends AppCompatActivity implements IContratoComercial.View, View.OnClickListener {

    // PRESENTER
    private IContratoComercial.Presenter presenter;

    // ROOM

    BaseDeDatos db;

    // ATRIBUTOS

    private Button btn_agendaClientes;
    private Button btn_agendaVisitas;
    private Button btn_buscarArticulo;

    private Toast msgToast;

    private String busqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_comercial);

        // Necesario para poder establecer una conexion
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ROOM
        db = BaseDeDatos.getInstancia(this);

        presenter = new ComercialPresenter(this, db);

        btn_agendaClientes = findViewById(R.id.btn_agendaClientes);
        btn_agendaVisitas = findViewById(R.id.btn_agendaVisitas);
        btn_buscarArticulo = findViewById(R.id.btn_buscarArticulo);

        btn_agendaClientes.setOnClickListener(this);
        btn_agendaVisitas.setOnClickListener(this);
        btn_buscarArticulo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_agendaClientes:
                presenter.onAgendaClientesClicked();
                break;
            case R.id.btn_agendaVisitas:
                presenter.onAgendaVisitasClicked();
                break;
            case R.id.btn_buscarArticulo:
                presenter.onBuscarArticuloClicked();
                break;
            default:
                break;
        }
    }

    @Override
    public void onMostrarAgendaClientes() {
        Intent intent = new Intent(this, ListaClientesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAgendaClientesVacia() {
        msgToast = Toast.makeText(this, "No hay clientes en su agenda", Toast.LENGTH_SHORT);
        msgToast.show();
    }

    @Override
    public void onMostrarAgendaVisitas() {
        Intent intent = new Intent(this, ListaVisitasActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAgendaVisitasVacia() {
        msgToast = Toast.makeText(this, "No hay visitas en su agenda para los próximos 7 dias", Toast.LENGTH_SHORT);
        msgToast.show();
    }

    @Override
    public void onMostrarArticulos() {
//        msgToast = Toast.makeText(this, DatosTrabajador.getArticulos().size(), Toast.LENGTH_SHORT);
//        msgToast.show();
        Intent intent = new Intent(this, ListaArticulosActivity.class);
        startActivity(intent);
    }


    @Override
    public void onArticulosVacio() {
        msgToast = Toast.makeText(this, "La descripcion no coincide con ningun articulo de la base de datos", Toast.LENGTH_SHORT);
        msgToast.show();
    }

    @Override
    public void onConexionInternetError() {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("Error de conexión a Internet");
        alert.setMessage("Compruebe que está conectado a una red WI-FI o red móvil y pulse 'ACTUALIZAR'");
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"Actualizar", (dialog, which) ->
                presenter.onActualizarClicked()
        );
//        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "Cerrar", (dialogInterface, i) -> alert.dismiss());
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = alert.getWindow().getAttributes();

        wmlp.gravity = Gravity.CENTER;
        alert.show();
    }

    @Override
    public void onConexionBBDDError() {
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("Error de conexión con la Base de Datos");
        alert.setMessage("En este momento no se puede establecer conexion con la base de datos." +
                " Intentelo mas tarde");
        alert.setButton(DialogInterface.BUTTON_POSITIVE,"Actualizar", (dialog, which) ->
                presenter.onActualizarClicked()
        );
        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "Cerrar", (dialogInterface, i) -> finish());
        alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams wmlp = alert.getWindow().getAttributes();

        wmlp.gravity = Gravity.CENTER;
        alert.show();
    }

    @Override
    public boolean hasInternetConnection() {
        ConnectivityManager connectivityManager =  (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        Log.d("DEBUG", "RED ACTIVA " + connectivityManager.getActiveNetwork());
        return connectivityManager.getActiveNetwork() != null;
    }
}