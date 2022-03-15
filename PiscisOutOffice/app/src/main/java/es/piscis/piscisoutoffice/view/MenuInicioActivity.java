package es.piscis.piscisoutoffice.view;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import es.piscis.piscisoutoffice.R;

public class MenuInicioActivity extends AppCompatActivity implements View.OnClickListener {

    //Atributos

    //Botones
    private Button btnComerciales;
    private Button btnRepartidores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Llamada al metodo de la clase padre para inicializar correctamente
        super.onCreate(savedInstanceState);
        //Instancia la intefaz definida en el layout de activity_menu_inicio
        setContentView(R.layout.activity_menu_inicio);

        //Enlazamos con los widgets del layout
        Button btnComerciales = findViewById(R.id.btn_comerciales);
        Button btnRepartidores = findViewById(R.id.btn_repartidores);

        //Asigna los listenerspara los botones
        btnComerciales.setOnClickListener(this);
        btnRepartidores.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_comerciales) {
            Intent intent = new Intent(this, MenuPasswordComercialesActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MenuPasswordRepartidoresActivity.class);
            startActivity(intent);
        }
    }
}