package es.piscis.piscisoutoffice.view.crearVisita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.OperacionesBBDD.IOperacionesBBDD;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.presenter.crearVisita.CrearVisitaPresenter;

public class CrearVisitaActivity extends AppCompatActivity implements IContratoCrearVisita.View {

    private IContratoCrearVisita.Presenter presenter;
    private BaseDeDatos db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_visita);

        // Necesario para poder establecer una conexion
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ROOM
        db = BaseDeDatos.getInstancia(this);

        presenter = new CrearVisitaPresenter(this, db);
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