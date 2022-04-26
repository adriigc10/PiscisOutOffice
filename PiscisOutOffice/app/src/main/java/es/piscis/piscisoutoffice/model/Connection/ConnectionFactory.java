package es.piscis.piscisoutoffice.model.Connection;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connectionInstance = null;

    public static Connection getLocalConnection() {
        Log.d("DEBUG", "CONNECTING DATABASE...");

        String url = "jdbc:jtds:sqlserver://192.168.0.126;databaseName=PiscisR";
        String username = "sa";
        String password = "1234";

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
                    connectionInstance = DriverManager.getConnection(url, username, password);
                    Log.d("DEBUG", "Database connected!");
                } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    Log.d("DEBUG", "Cannot connect to the database!");
                    throw new IllegalStateException("Cannot connect the database!", e);
                }
            }
        });

        thread.start();
        return connectionInstance;
    }

    public static Connection getRemoteConnection() {
        Log.d("DEBUG", "CONNECTING DATABASE...");

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
                    connectionInstance = DriverManager.getConnection(url, username, password);
                    Log.d("DEBUG", "CONECTION SUCSESSFUL!");
                } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    Log.d("DEBUG", "CANNOT CONNECT TO THE DATABASE!");
                    throw new IllegalStateException("CANNOT CONNECT TO THE DATABASE!", e);
                }
            }
        });

        thread.start();
        return connectionInstance;
    }
}
