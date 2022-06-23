package es.piscis.piscisoutoffice.view.reserva;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.Datos.DatosComercial;

import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;
import es.piscis.piscisoutoffice.presenter.reserva.ReservaPresenter;

public class ListaReservasActivity extends AppCompatActivity implements IContratoReserva.View {

    private IContratoReserva.Presenter presenter;

    ListView listView;
    ReservaArrayAdapter adapter;

    Toast msgToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reservas);

        presenter = new ReservaPresenter(this);

    }

    @Override
    public void onReservasCargadas(List<Reserva> reservas) {

        TextView campoClienteSeleccionado = findViewById(R.id.tv_campoClienteSeleccionado2);
        TextView clientePulsado = findViewById(R.id.tv_nombreClientePulsado2);

        campoClienteSeleccionado.setText("Nombre Cliente: ");
        clientePulsado.setText(DatosComercial.getClienteSeleccionado().getNombre());

        adapter = new ReservaArrayAdapter(ListaReservasActivity.this, 0, reservas);
        listView = findViewById(R.id.reservasListView);
        listView.setAdapter(adapter);
    }

    @Override
    public void onCargaSatisfactoria(int reservasCargadas) {
        String texto = String.format("Se han cargado %d reservas", reservasCargadas);
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