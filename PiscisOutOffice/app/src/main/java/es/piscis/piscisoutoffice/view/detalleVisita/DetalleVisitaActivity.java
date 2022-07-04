package es.piscis.piscisoutoffice.view.detalleVisita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.Datos.DatosTrabajador;
import es.piscis.piscisoutoffice.model.Room.BBDD.BaseDeDatos;
import es.piscis.piscisoutoffice.model.Room.Dao.IVisitasDAO;
import es.piscis.piscisoutoffice.model.Room.Entidades.Visita;

public class DetalleVisitaActivity extends AppCompatActivity implements IContratoDetalleVisita.View {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_visita);

        BaseDeDatos bd = BaseDeDatos.getInstancia(this.getApplicationContext());
        IVisitasDAO visitasDAO = bd.visitasDAO();
        Visita visita = visitasDAO.getVisita(DatosTrabajador.getCodigoVisitaSeleccionada());

        TextView diaAccion =  findViewById(R.id.tv_diaAccion_detalleVisita);
        TextView nombreCliente =  findViewById(R.id.tv_nombreCliente_detalleVisita);
        TextView conceptoAccion =  findViewById(R.id.tv_conceptoAccion_detalleVisita);
        TextView direccion =  findViewById(R.id.tv_direccion_detalleVisita);
        TextView localidad =  findViewById(R.id.tv_localidad_detalleVisita);
        TextView codigoPostal =  findViewById(R.id.tv_codigoPostal_detalleVisita);
        TextView telefono =  findViewById(R.id.tv_telefono_detalleVisita);
        TextView comentario =  findViewById(R.id.tv_comentario_detalleVista);

        Switch meRecibio = findViewById(R.id.sw_meRecibio);
        Switch produjoVenta = findViewById(R.id.sw_produjoVenta);
        Switch activo = findViewById(R.id.sw_activo);

        diaAccion.setText(visita.getDiaAccion());
        nombreCliente.setText(visita.getNombreCliente());
        conceptoAccion.setText(visita.getConceptoAccion());
        direccion.setText(visita.getDireccion());
        localidad.setText(visita.getLocalidad());
        codigoPostal.setText(String.valueOf(visita.getCodigoPostal()));
        telefono.setText(visita.getTelefono());
        comentario.setText(visita.getComentario());


        telefono.setOnClickListener( v -> {

        });

        meRecibio.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                visita.setMeRecibio((byte)1);
                Toast.makeText(DetalleVisitaActivity.this, "He cambiado", Toast.LENGTH_SHORT).show();
            }
        });


    }
}