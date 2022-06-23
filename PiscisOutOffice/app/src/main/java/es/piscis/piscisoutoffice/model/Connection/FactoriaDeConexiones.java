package es.piscis.piscisoutoffice.model.Connection;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoriaDeConexiones {
    private static Connection instanciaConexion = null;

    public static void obtenerConexionLocal() {
        Log.d("DEBUG",  "CONECTANDO A LA BASE DE DATOS...");

        String url = "jdbc:jtds:sqlserver://192.168.1.100;databaseName=PiscisR";
        String username = "sa";
        String password = "1234";

        Thread thread = new Thread(new Runnable() {

            // TODO: COMPROBAR FALLO AL NO TENER INTERNET Y ABRIR APLICACION

            @Override
            public void run() {
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
                    instanciaConexion = DriverManager.getConnection(url, username, password);
                    Log.d("DEBUG", "CONEXION SATISFACTORIA");
                } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    Log.d("DEBUG", "NO SE HA PODIDO CONECTAR A LA BASE DE DATOS");
                    throw new IllegalStateException("NO SE HA PODIDO CONECTAR A LA BASE DE DATOS", e);
                }
            }
        });

        thread.start();
    }

    public static void obtenerConexionRemota() {
        Log.d("DEBUG", "CONECTANDO A LA BASE DE DATOS");

        String url = "jdbc:jtds:sqlserver://sql244.sql.dinaserver.com:1433;databaseName=PiscisR";
        String username = "saR";
        String password = "A326mxP4j";

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
                    instanciaConexion = DriverManager.getConnection(url, username, password);
                    Log.d("DEBUG", "CONEXION SATISFACTORIA");
                } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    Log.d("DEBUG", "NO SE HA PODIDO CONECTAR A LA BASE DE DATOS");
                    throw new IllegalStateException("NO SE HA PODIDO CONECTAR A LA BASE DE DATOS", e);
                }
            }
        });

        thread.start();
    }

    public static Connection obtenerConexion(){ return instanciaConexion; }
}
