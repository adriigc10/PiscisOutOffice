package es.piscis.piscisoutoffice.view.autenticacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;

import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.Connection.FactoriaDeConexiones;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.presenter.autenticacion.AutenticacionPresenter;
import es.piscis.piscisoutoffice.view.comercial.MenuPrincipalComercialActivity;

public class MenuAutenticacionActivity extends AppCompatActivity implements IContratoAutenticacion.View, View.OnClickListener {

    // Presenter
    IContratoAutenticacion.Presenter presenter;

    // Room

    private BaseDeDatos db;

    //ATRIBUTOS
    private EditText et_usuario;
    private EditText et_password;

    private Button btn_login;
    private Toast msgToast;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_autenticacion);

        // Necesario para poder establecer una conexion
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ROOM
        db = BaseDeDatos.getInstancia(this);
        db.clearAllTables();

        // Instanciamos el presenter
        presenter = new AutenticacionPresenter(this, db);

        // Bindeamos el layout con objetos Java
        et_usuario = findViewById(R.id.et_usuario);
        et_password = findViewById(R.id.et_password);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String dni = et_usuario.getText().toString();
        String contrasena = et_password.getText().toString();

        presenter.onLoginClicked(dni, contrasena);
    }

    @Override
    public void onComercialAutenticado() {
       Intent intent = new Intent(this, MenuPrincipalComercialActivity.class);
       startActivity(intent);
    }

    @Override
    public void onRepartidorAutenticado() {
        Log.d ("DEBUG", "SE QUE ES UN REPARTIDOR");
//        Intent intent = new Intent(this, RepartidorPrincipalActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onAutenticacionError() {
        msgToast = Toast.makeText(this, "El dni o la contrasena son incorrectos", Toast.LENGTH_SHORT);
        msgToast.show();
    }

    @Override
    public void onRolError() {
        String texto = "Ha ocurrido un error al encontrar su rol, por favor, intentelo de nuevo";
        msgToast = Toast.makeText(this, texto, Toast.LENGTH_SHORT);
        msgToast.show();
    }

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

    public boolean hasInternetConnection() {
        ConnectivityManager connectivityManager =  (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        Log.d("DEBUG", "RED ACTIVA " + connectivityManager.getActiveNetwork());

        Network hayConexion =  connectivityManager.getActiveNetwork();

        if (hayConexion == null) {
            FactoriaDeConexiones.eliminarConexion();
        }

        return hayConexion != null;
    }
}