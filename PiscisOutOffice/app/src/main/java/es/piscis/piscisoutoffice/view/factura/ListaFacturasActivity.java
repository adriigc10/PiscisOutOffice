package es.piscis.piscisoutoffice.view.factura;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.presenter.factura.FacturaPresenter;

public class ListaFacturasActivity extends AppCompatActivity implements IContratoFactura.View{

    private static final int CODIGO_PERMISO_DESCARGA = 1000;

    private IContratoFactura.Presenter presenter;
    private String url;

    ListView listView;
    FacturaArrayAdapter adapter;

    Toast msgToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_facturas);

        presenter = new FacturaPresenter(this);
    }

    @Override
    public void onFacturasCargadas(List<Factura> facturas) {
        TextView campoClienteSeleccionado = findViewById(R.id.tv_campoClienteSeleccionado3);
        TextView clientePulsado = findViewById(R.id.tv_nombreClientePulsado3);

        campoClienteSeleccionado.setText("Nombre Cliente: ");
        clientePulsado.setText(DatosComercial.getClienteSeleccionado().getNombre());

        adapter = new FacturaArrayAdapter(ListaFacturasActivity.this, 0, facturas);
        listView = findViewById(R.id.facturasListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> presenter.onFacturaPulsado(position));
    }

    @Override
    public void onCargaSatisfactoria(int facturasCargadas) {
        String texto = String.format("Se han cargado %d facturas", facturasCargadas);
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

    public void descargarFactura(String url) {
        this.url = url;
        Log.d("DEBUG", url);
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_DENIED) {
            // Permiso denegado, hay que solicitarlo
            String[] permisos = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            // Mostramos un pop-up para solicitar el permiso
            requestPermissions(permisos, CODIGO_PERMISO_DESCARGA);

        } else {
            // Tenemos permiso, descargamos el pdf
            empezarDescarga(url);
        }
    }

    private void empezarDescarga(String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse("https://www.almacenespiscis.com/images/articulos/8435462165585.jpg"));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("Descarga");
        request.setDescription("Descargando...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "facturas");

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CODIGO_PERMISO_DESCARGA:
                if (grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    // Permiso garantizado desde el pop-up. descargamos el pdf
                    empezarDescarga(url);
                } else {
                    // Permiso denegado desde Pop-up
                    msgToast = Toast.makeText(this, "Has denegado el permiso de la descarga", Toast.LENGTH_SHORT);
                    msgToast.show();
                }
        }
    }
}