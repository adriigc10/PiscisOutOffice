package es.piscis.piscisoutoffice.view.detalleCliente;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import es.piscis.piscisoutoffice.R;

import es.piscis.piscisoutoffice.model.Datos.DatosTrabajador;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Dao.IClienteDAO;
import es.piscis.piscisoutoffice.presenter.detalleCliente.DetalleClientePresenter;
import es.piscis.piscisoutoffice.view.factura.ListaFacturasActivity;
import es.piscis.piscisoutoffice.view.presupuesto.ListaPresupuestosActivity;
import es.piscis.piscisoutoffice.view.reserva.ListaReservasActivity;

public class DetalleClienteActivity extends AppCompatActivity implements IContratoDetalleCliente.View, View.OnClickListener {

    // PRESENTER
    private IContratoDetalleCliente.Presenter presenter;

    // ROOM

    BaseDeDatos bd;
    IClienteDAO clienteDAO;

    // ATRIBUTOS
    private TextView tv_campoClienteSeleccionado;
    private TextView tv_nombreClientePulsado;
    private Button btn_facturas;
    private Button btn_presupuestos;
    private Button btn_reservas;

    private Toast msgToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cliente);

        // Necesario para poder establecer una conexion
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ROOM
        bd = BaseDeDatos.getInstancia(this.getApplicationContext());
        clienteDAO = bd.clienteDAO();

        presenter = new DetalleClientePresenter(this, bd);

        tv_campoClienteSeleccionado = findViewById(R.id.tv_campoClienteSeleccionado);
        tv_nombreClientePulsado = findViewById(R.id.tv_nombreClientePulsado);
        btn_facturas = findViewById(R.id.btn_facturas);
        btn_presupuestos = findViewById(R.id.btn_presupuestos);
        btn_reservas = findViewById(R.id.btn_reservas);

        tv_campoClienteSeleccionado.setText("Cliente seleccionado");
        tv_nombreClientePulsado.setText(clienteDAO.getCliente(DatosTrabajador.getCodigoClienteSeleccionado()).getNombre());
        btn_facturas.setOnClickListener(this);
        btn_presupuestos.setOnClickListener(this);
        btn_reservas.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_facturas:
                presenter.onFacturasClicked();
                break;
            case R.id.btn_presupuestos:
                presenter.onPresupuestosClicked();
                break;
            case R.id.btn_reservas:
                presenter.onReservasClicked();
                break;
            default:
                break;
        }
    }

    @Override
    public void onMostrarFacturasCliente() {
        Intent intent = new Intent(this, ListaFacturasActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFacturasClienteVacias() {
        msgToast = Toast.makeText(this, "Este cliente no posee facturas", Toast.LENGTH_SHORT);
        msgToast.show();
    }

    @Override
    public void onMostrarPresupuestosCliente() {
        Intent intent = new Intent(this, ListaPresupuestosActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPresupuestosClienteVacios() {
        msgToast = Toast.makeText(this, "Este cliente  no posee presupuestos", Toast.LENGTH_SHORT);
        msgToast.show();
    }

    @Override
    public void onMostrarReservasCliente() {
        Intent intent = new Intent(this, ListaReservasActivity.class);
        startActivity(intent);
    }

    @Override
    public void onReservasClienteVacias() {
        msgToast = Toast.makeText(this, "Este cliente tiene ninguna reserva pendiente", Toast.LENGTH_SHORT);
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
        return connectivityManager.getActiveNetwork() != null;
    }

    @SuppressLint("WrongThread")
    @Override
    public void onBackPressed() {
        // Cada vez que salga de un cliente, borramos todas las tablas de la BBDD
//        db.clearAllTables();
        // Con esto hacemos que tambien vaya a la pantalla (actividad) anterior
        super.onBackPressed();
    }
}