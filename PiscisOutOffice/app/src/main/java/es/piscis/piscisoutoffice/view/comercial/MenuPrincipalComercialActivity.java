package es.piscis.piscisoutoffice.view.comercial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.presenter.comercial.ComercialPresenter;
import es.piscis.piscisoutoffice.view.cliente.ListaClientesActivity;

public class MenuPrincipalComercialActivity extends AppCompatActivity implements IContratoComercial.View, View.OnClickListener {

    // PRESENTER
    private IContratoComercial.Presenter presenter;

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

        presenter = new ComercialPresenter(this);

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

    }

    @Override
    public void onAgendaVisitasVacia() {
        msgToast = Toast.makeText(this, "No hay visitas en su agenda para hoy", Toast.LENGTH_SHORT);
        msgToast.show();
    }

    @Override
    public void onArticuloBuscado() {

    }


    @Override
    public void onArticuloError() {
        msgToast = Toast.makeText(this, "La descripcion no coincide con ningun articulo de la base de datos", Toast.LENGTH_SHORT);
        msgToast.show();
    }
}