package es.piscis.piscisoutoffice.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.Connection.ConnectionFactory;

public class MenuLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_login_comerciales);
        ConnectionFactory.getLocalConnection();
    }
}