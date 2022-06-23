package es.piscis.piscisoutoffice.view.detalleCliente;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.Datos.DatosComercial;

import es.piscis.piscisoutoffice.presenter.detalleCliente.DetalleClientePresenter;
import es.piscis.piscisoutoffice.view.factura.ListaFacturasActivity;
import es.piscis.piscisoutoffice.view.reserva.ListaReservasActivity;

public class DetalleClienteActivity extends AppCompatActivity implements IContratoDetalleCliente.View, View.OnClickListener {

    // PRESENTER
    private IContratoDetalleCliente.Presenter presenter;

    // ROOM

//    BaseDeDatos db;
//    IClienteDAO dao;

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

        presenter = new DetalleClientePresenter(this);

        // ROOM
//        db = BaseDeDatos.getInstancia(this.getApplicationContext());
//        dao = db.clienteDAO();

        tv_campoClienteSeleccionado = findViewById(R.id.tv_campoClienteSeleccionado);
        tv_nombreClientePulsado = findViewById(R.id.tv_nombreClientePulsado);
        btn_facturas = findViewById(R.id.btn_facturas);
        btn_presupuestos = findViewById(R.id.btn_presupuestos);
        btn_reservas = findViewById(R.id.btn_reservas);

        tv_campoClienteSeleccionado.setText("Cliente seleccionado");
        tv_nombreClientePulsado.setText(DatosComercial.getClienteSeleccionado().getNombre());
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

    }

    @Override
    public void onMostrarPresupuestosCliente() {

    }

    @Override
    public void onPresupuestosClienteVacios() {

    }

    @Override
    public void onMostrarReservasCliente() {
        Intent intent = new Intent(this, ListaReservasActivity.class);
        startActivity(intent);
    }

    @Override
    public void onReservasClienteVacias() {

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