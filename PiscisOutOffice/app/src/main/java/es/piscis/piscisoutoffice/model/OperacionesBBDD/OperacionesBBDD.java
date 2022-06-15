package es.piscis.piscisoutoffice.model.OperacionesBBDD;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.piscis.piscisoutoffice.model.Connection.FactoriaDeConexiones;

public class OperacionesBBDD implements IOperacionesBBDD{

    // Atributos
    String query;
    PreparedStatement ps;
    ResultSet rs;
    Toast msgToast;

    // Atributos globales
    String nombreTrabajador;
    public static Integer codigoComercial;

    @Override
    public boolean comprobarConexion(){
        if (FactoriaDeConexiones.obtenerConexion() == null) {
            return false;
        } else{
            return true;
        }
    }

    @Override
    public boolean autenticarseBBDD(String dni, String contrasena) {

        query = "SELECT nombre, apellidos FROM dbo.empleados1 WHERE dni=? AND contrasena=?";
        if (!comprobarConexion()){
            Log.d("DEBUG", "CONEXION NULA");
            return false;
        } else {
            try {
                ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
                // DNI = Primer campo de texto
                ps.setString(1,dni);
                // PASSWORD  = Segundo campo de texto
                ps.setString(2, contrasena);
                // Ejecutamos la query
                rs = ps.executeQuery();
                // Esto siempre hay que hacerlo para situarnos en la primera fila de resultados
                rs.next();

                String nombreEmpleado = rs.getString(1);
                String apellidosEmpleado = rs.getString(2);
                
                nombreTrabajador = nombreEmpleado + " " + apellidosEmpleado.split(" ")[0];

                Log.d("DEBUG", "USUARIO CORRECTO, BIENVENIDO " + nombreEmpleado);

                return true;
            } catch (SQLException e) {
                // Usuario o contraseña incorrectas
                Log.d("DEBUG", "LA QUERY ES INCORRECTA");
                return false;
            }
        }
    }

    @Override
    public String conocerRol() {
        query = "select numeroCo from dbo.comerciales where Nombre=?";
        try {
            ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
            ps.setString(1, nombreTrabajador);
            Log.d("DEBUG", "Esta a punto de hacer la query: " + nombreTrabajador);
            rs = ps.executeQuery();
            rs.next();
            codigoComercial = rs.getInt(1);
            Log.d("DEBUG", "Codigo Comercial: " + codigoComercial);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "comercial";
    }
}
