package es.piscis.piscisoutoffice.view.autenticacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.presenter.AutenticacionPresenter;

public class MenuAutenticacionActivity extends AppCompatActivity implements IContratoAutenticacion.View, View.OnClickListener{

    // Presenter
    IContratoAutenticacion.Presenter presenter;

    //ATRIBUTOS
    private EditText et_usuario;
    private EditText et_password;

    private Button btn_login;
    private Toast msgToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_autenticacion);

        presenter = new AutenticacionPresenter(this);

        // Necesario para poder establecer una conexion
//       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//       StrictMode.setThreadPolicy(policy);

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
//        Intent intent = new Intent(this, ComercialPrincipalActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onRepartidorAutenticado() {
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
}