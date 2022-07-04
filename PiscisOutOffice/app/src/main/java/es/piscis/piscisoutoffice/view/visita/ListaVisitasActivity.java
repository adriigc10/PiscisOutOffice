package es.piscis.piscisoutoffice.view.visita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Entidades.Visita;
import es.piscis.piscisoutoffice.presenter.visita.VisitaPresenter;
import es.piscis.piscisoutoffice.view.crearVisita.CrearVisitaActivity;
import es.piscis.piscisoutoffice.view.detalleVisita.DetalleVisitaActivity;

public class ListaVisitasActivity extends AppCompatActivity implements IContratoVisita.View, View.OnClickListener {

    private IContratoVisita.Presenter presenter;

    private BaseDeDatos db;

    private ListView listView;
    private VisitaArrayAdapter adapter;
    private Button btn_crearVisita;

    private Toast msgToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_visitas);
        btn_crearVisita = findViewById(R.id.btn_crearVisita);

//        btn_crearVisita.setOnClickListener(this);

        // Necesario para poder establecer una conexion
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // INSTANCIAMOS ROOM
        db = BaseDeDatos.getInstancia(this.getApplicationContext());

        // Instanciamos el presenter
        presenter = new VisitaPresenter(this, db);
    }

    @Override
    public void onVisitasCargadas(List<Visita> visitas) {
        adapter = new VisitaArrayAdapter(ListaVisitasActivity.this, 0, visitas);
        listView = findViewById(R.id.visitasListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> presenter.onVisitaPulsada(position));
    }

    @Override
    public void onCargaSatisfactoria(int visitasCargadas) {
        String texto = String.format("Se han cargado %d visitas", visitasCargadas);
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

    @Override
    public void abrirDetalleVisita() {
//        Intent intent = new Intent(this, DetalleVisitaActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        msgToast = Toast.makeText(this, "He pulsado el boton", Toast.LENGTH_SHORT);
        msgToast.show();
        Intent intent = new Intent(this, CrearVisitaActivity.class);
        startActivity(intent);
    }
}