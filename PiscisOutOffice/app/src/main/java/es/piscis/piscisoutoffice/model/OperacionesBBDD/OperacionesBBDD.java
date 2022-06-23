package es.piscis.piscisoutoffice.model.OperacionesBBDD;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.piscis.piscisoutoffice.model.Connection.FactoriaDeConexiones;
import es.piscis.piscisoutoffice.model.Datos.DatosComercial;
import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;


public class OperacionesBBDD implements IOperacionesBBDD{

    // Atributos

    View contexto;

    String query;
    PreparedStatement ps;
    ResultSet rs;

    // CONSTRUCTOR



    // METODOS

/*    private boolean hayConexionAIntenet(){
        NetworkInfo hayConexion = ((ConnectivityManager)
                contexto.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        return hayConexion != null;
    }
*/
    @Override
    public boolean comprobarConexion() {
//        if (FactoriaDeConexiones.obtenerConexion() == null) {
            //FactoriaDeConexiones.obtenerConexionLocal();
//            if (FactoriaDeConexiones.obtenerConexion() == null) {
//                return false;
//           } else {
//                return true;
//            }
//        } else {
//            return true;
//        }
        return true;
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
                
                DatosComercial.setNombreTrabajador(nombreEmpleado + " " + apellidosEmpleado.split(" ")[0]);

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
            ps.setString(1, DatosComercial.getNombreTrabajador());
            rs = ps.executeQuery();
            rs.next();
            DatosComercial.setCodigoComercial(rs.getInt(1));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "comercial";
    }

    @Override
    public List<Cliente> buscarAgendaClientesComercial(){
        List<Cliente> clientes = new ArrayList<>();

        if(!comprobarConexion()){
            Log.d("DEBUG", "No hay conexion con la BBDD");
        } else {
            try {
                query = "SELECT CCliente, nombre, nombreComercial, nTelf, direccion, ciudad" +
                        " FROM dbo.clientes" +
                        " WHERE comercialAsignado=" + DatosComercial.getCodigoComercial();

                ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
                rs = ps.executeQuery();

                while(rs.next()){
                    Cliente c = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6));

                    clientes.add(c);
                }
            } catch (SQLException sqlException) {
                Log.d("DEBUG", "Ha entrado en el catch de buscarAgendaClientesComercial");
                sqlException.printStackTrace();
            }
        }
        return clientes;
    }

    @Override
    public List<Cliente> buscarCliente(String busqueda){
        List<Cliente> clientes = new ArrayList<>();

        if (!comprobarConexion()){
            Log.d("DEBUG", "CONEXION NULA");
        }
        try {
            query = "Select Ccliente, nombre, nombreComercial, Ntelf, direccion, ciudad" +
                    " from dbo.clientes " +
                    "where nombre like '%" + busqueda + "%' and ComercialAsignado=" +
                    DatosComercial.getCodigoComercial();
            ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                Cliente c = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));

                clientes.add(c);
            }

        } catch (SQLException throwables) {
            Log.d("DEBUG", "Ha entrado en el catch");
            throwables.printStackTrace();
        }

        return clientes;
    }

    @Override
    public List<Articulo> buscarArticulo(String busqueda) {
        List<Articulo> articulos = new ArrayList<>();

/*        if (!comprobarConexion()){
            Log.d("DEBUG", "CONEXION NULA");
        }
        try {
            query = "Select Ccliente, nombre, nombreComercial, Ntelf, direccion" +
                    " from dbo.clientes " +
                    "where nombre like '%" + busqueda + "%'";
            ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
            ps.setString(1, busqueda);
            ps.setString(2, busqueda);
            rs = ps.executeQuery();

            while(rs.next()){
                Articulo a = new Articulo();

                articulos.add(a);
                Log.d("DEBUG", "Nombre: " + rs.getString(2));
            }

        } catch (SQLException throwables) {
            Log.d("DEBUG", "Ha entrado en el catch");
            throwables.printStackTrace();
        }
*/
        return articulos;
    }

    @Override
    public List<Reserva> buscarReservasCliente() {
        List<Reserva> reservas = new ArrayList<>();
        Integer codigoCliente = DatosComercial.getClienteSeleccionado().getCodigoCliente();

        if (!comprobarConexion()){
            Log.d("DEBUG", "CONEXION NULA");
        } else {
            try {

                query = "SELECT DISTINCT rc.codArt, cb.codigoBa, rc.nombreArt, rc.unidades, rc.comentario" +
                        " FROM dbo.reservasClientes rc, dbo.codBar cb" +
                        " WHERE rc.Numcli =" + codigoCliente + " AND rc.codArt = cb.codArt";
                ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
                rs = ps.executeQuery();

                while(rs.next()) {
                    Reserva r = new Reserva(rs.getInt(1), rs.getString(2).trim(), rs.getString(3), rs.getInt(4), rs.getString(5));
                    reservas.add(r);
                    Log.d("DEBUG", r.getUrlDescarga());
                }
            } catch (SQLException  e) {
                Log.d("DEBUG", "Error en la el metodo buscarReservasCliente");
                throw new IllegalStateException("Error en el catch del metodo buscarReservasCliente", e);
            }
        }

        return reservas;
    }

    public List<Factura> buscarFacturasCliente() {
        List<Factura> facturas = new ArrayList<>();
        Integer codigoCliente = DatosComercial.getClienteSeleccionado().getCodigoCliente();

        if (!comprobarConexion()){
            Log.d("DEBUG", "CONEXION NULA");
        } else {
            try {

                query = "SELECT fechaDoc, totalFactura, NumeroFra" +
                        " FROM dbo.facturas" +
                        " WHERE codCli =" + codigoCliente;
                ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
                rs = ps.executeQuery();

                while(rs.next()) {
                    String fechaFactura = rs.getDate(1).toString();
                    fechaFactura = fechaFactura.split(" ")[0];
                    String anhoFactura = fechaFactura.split("-")[2];
                    Integer numeroFra = (int) rs.getFloat(3);
                    String url = "https://www.almacenespiscis.com/facturas/" + codigoCliente
                            + anhoFactura + numeroFra + ".pdf";
                    Factura f = new Factura(fechaFactura, rs.getFloat(2), numeroFra, url);
                    facturas.add(f);
                }
            } catch (SQLException  e) {
                Log.d("DEBUG", "Error en la el metodo buscarReservasCliente");
                throw new IllegalStateException("Error en el catch del metodo buscarReservasCliente", e);
            }
        }

        return facturas;
    }
}
