package es.piscis.piscisoutoffice.view.presupuesto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.Datos.DatosTrabajador;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Dao.IClienteDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IPresupuestosDAO;
import es.piscis.piscisoutoffice.model.Room.Entidades.Presupuesto;
import es.piscis.piscisoutoffice.presenter.presupuesto.PresupuestoPresenter;

public class ListaPresupuestosActivity extends AppCompatActivity implements IContratoPresupuesto.View{

    private IContratoPresupuesto.Presenter presenter;

    BaseDeDatos bd;
    IClienteDAO clienteDAO;
    IPresupuestosDAO presupuestosDAO;

    ListView listView;
    PresupuestoArrayAdapter adapter;

    Toast msgToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_presupuestos);

        bd = BaseDeDatos.getInstancia(this.getApplicationContext());
        clienteDAO = bd.clienteDAO();
        presupuestosDAO = bd.presupuestosDAO();

        presenter = new PresupuestoPresenter(this, bd);
    }

    @Override
    public void onPresupuestosCargados(List<Presupuesto> presupuestos) {
        TextView campoClienteSeleccionado = findViewById(R.id.tv_campoClienteSeleccionado4);
        TextView clientePulsado = findViewById(R.id.tv_nombreClientePulsado4);

        campoClienteSeleccionado.setText("Nombre Cliente: ");
        clientePulsado.setText(clienteDAO.getCliente(DatosTrabajador.getCodigoClienteSeleccionado()).getNombre());

        adapter = new PresupuestoArrayAdapter(ListaPresupuestosActivity.this, 0, presupuestos);
        listView = findViewById(R.id.presupuestoListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> presenter.onPresupuestoPulsado(position));
    }

    @Override
    public void onCargaSatisfactoria(int presupuestosCargadas) {
        String texto = String.format("Se han cargado %d presupuestos", presupuestosCargadas);
        msgToast = Toast.makeText(this, texto, Toast.LENGTH_SHORT);
        msgToast.show();
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
}