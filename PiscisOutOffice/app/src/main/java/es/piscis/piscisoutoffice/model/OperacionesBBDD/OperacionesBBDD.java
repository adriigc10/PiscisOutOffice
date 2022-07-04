package es.piscis.piscisoutoffice.model.OperacionesBBDD;

import android.util.Log;
import android.widget.Toast;

import net.sourceforge.jtds.jdbc.DateTime;

import java.nio.channels.ReadPendingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import es.piscis.piscisoutoffice.model.Connection.FactoriaDeConexiones;
import es.piscis.piscisoutoffice.model.Datos.DatosTrabajador;
import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;
import es.piscis.piscisoutoffice.model.Room.Entidades.Comercial;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.model.Room.Entidades.Presupuesto;
import es.piscis.piscisoutoffice.model.Room.Entidades.Repartidor;
import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;
import es.piscis.piscisoutoffice.model.Room.Entidades.Tarifa;
import es.piscis.piscisoutoffice.model.Room.Entidades.Trabajador;
import es.piscis.piscisoutoffice.model.Room.Entidades.Visita;


public class OperacionesBBDD implements IOperacionesBBDD{

    // Atributos

    private String query;
    private PreparedStatement ps;
    private ResultSet rs;

    Integer codigoCliente;

    // CONSTRUCTOR

    public OperacionesBBDD() { }

    // METODOS

    public boolean hayConexionBBDD (){
        if (FactoriaDeConexiones.obtenerConexion() == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Trabajador buscarTrabajador(String dni, String contrasena) {

        query = "SELECT nombre, apellidos, EsComercial, esRepartidoe" +
                " FROM dbo.empleados1" +
                " WHERE dni=? AND contrasena=?";

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
            int esComercial = rs.getInt(3);
            int esRepartidor = rs.getInt(4);
                
            DatosTrabajador.setNombreTrabajador(nombreEmpleado + " " + apellidosEmpleado.split(" ")[0]);

            if (esComercial == -1) {
                query = "select numeroCo from dbo.comerciales where Nombre=?";
                ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
                ps.setString(1, nombreEmpleado + " " + apellidosEmpleado.split(" ")[0]);
                rs = ps.executeQuery();
                rs.next();
                int codigoComercial = rs.getInt(1);

                Comercial comercial = new Comercial(dni, nombreEmpleado, apellidosEmpleado, contrasena, codigoComercial);
                DatosTrabajador.codigoComercial = codigoComercial;
                DatosTrabajador.esComercial = true;

                return comercial;
            } else if (esRepartidor == -1) {

                Repartidor repartidor = new Repartidor(dni, nombreEmpleado, apellidosEmpleado, contrasena);

                return repartidor;
            } else {
                return null;
            }

        } catch (SQLException e) {
            // Usuario o contraseña incorrectas
            Log.d("DEBUG", "LA QUERY ES INCORRECTA");
            return null;
        }
    }

    @Override
    public List<Cliente> buscarAgendaClientesComercial(){
        List<Cliente> clientes = new ArrayList<>();

        try {
            query = "SELECT CCliente, nombre, nombreComercial, nTelf, direccion, ciudad" +
                    " FROM dbo.clientes" +
                    " WHERE comercialAsignado=" + DatosTrabajador.getCodigoComercial();

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
        return clientes;
    }

    @Override
    public List<Articulo> buscarArticulo() {
        List<Articulo> articulos = new ArrayList<>();
        Long codArt;
        String descripcion;
        Float pvp;

        try {
            query = "SELECT a.CodArt, a.descripcion, a.pvp, a.inactivo" +
                    " FROM dbo.articulos a";

            ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                if (rs.getInt(4) == 0) {
                    Articulo a = new Articulo(rs.getLong(1), rs.getString(2), rs.getFloat(3));
                    articulos.add(a);
                }
            }

        } catch (SQLException throwables) {
            Log.d("DEBUG", "Ha entrado en el catch");
            throwables.printStackTrace();
        }

        return articulos;
    }

    @Override
    public Articulo buscarDetallesArticulo(Integer codArt) {
        Articulo articulo;

        query = "SELECT a.CodArt, a.descripcion, a.pvp, a.inactivo, s.stock, lt.codigoTarifa," +
                " lt.nombreTarifa, t.precio" +
                " FROM dbo.articulos a, dbo.tarifas t, dbo.ltarifas lt, stocks s" +
                " WHERE a.codArt = t.codArt" +
                " AND t.codigoTarifa = lt.codigoTarifa and s.codArt = a.codArt";

        return null;
    }

    @Override
    public List<Reserva> buscarReservasCliente() {
        List<Reserva> reservas = new ArrayList<>();
        codigoCliente = DatosTrabajador.getCodigoClienteSeleccionado();

        try {

            query = "SELECT DISTINCT rc.codArt, cb.codigoBa, rc.nombreArt, rc.unidades, rc.comentario, rc.activo" +
                    " FROM dbo.reservasClientes rc, dbo.codBar cb" +
                    " WHERE rc.Numcli =" + codigoCliente + " AND rc.codArt = cb.codArt";
            ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()) {
                if (rs.getInt(6) == -1){ // Si la reserva no est activa no la anhadimos
                    Reserva r = new Reserva(rs.getInt(1), rs.getString(2).trim(), rs.getString(3), rs.getInt(4), rs.getString(5));
                    reservas.add(r);
                    Log.d("DEBUG", r.getUrlDescarga());
                }
            }
        } catch (SQLException  e) {
            Log.d("DEBUG", "Error en la el metodo buscarReservasCliente");
            throw new IllegalStateException("Error en el catch del metodo buscarReservasCliente", e);
        }

        return reservas;
    }

    public List<Factura> buscarFacturasCliente() {
        List<Factura> facturas = new ArrayList<>();
        codigoCliente = DatosTrabajador.getCodigoClienteSeleccionado();

        try {
            query = "SELECT fechaDoc, totalFactura, NumeroFra" +
                    " FROM dbo.facturas" +
                    " WHERE codCli =" + codigoCliente;
            ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()) {
                String fechaFactura = rs.getDate(1).toString();
                fechaFactura = fechaFactura.split(" ")[0];
//                String anhoFactura = fechaFactura.split("-")[0].substring(2);
                String anhoFactura = fechaFactura.substring(2,4);
                Log.d("DEBUG", "Anhoi obtenido: " + anhoFactura);
                Integer numeroFra = (int) rs.getFloat(3);
                String url = "https://www.almacenespiscis.com/facturas/" + codigoCliente
                        + anhoFactura + numeroFra + ".pdf";
                Factura f = new Factura(fechaFactura, rs.getFloat(2) ,numeroFra, url);
                facturas.add(f);
            }
        } catch (SQLException  e) {
            Log.d("DEBUG", "Error en la el metodo buscarReservasCliente");
            throw new IllegalStateException("Error en el catch del metodo buscarReservasCliente", e);
        }

        return facturas;
    }

    @Override
    public List<Presupuesto> buscarPresupuestosCliente() {
        List<Presupuesto> presupuestos = new ArrayList<>();
        codigoCliente = DatosTrabajador.getCodigoClienteSeleccionado();

        try {
            query = "SELECT Id, ReferenciaPres, fecha, TotaL, estado" +
                    " FROM dbo.presupuestos" +
                    " WHERE numCliente =" + codigoCliente;
            ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()) {
                Integer idPresupuesto = rs.getInt(1);
                String referenciaPresupuesto = rs.getString(2);
                String fechaEmision = rs.getString(3);
                Float total = rs.getFloat(4);
                String estado = rs.getString(5);
                Presupuesto p = new Presupuesto(idPresupuesto, referenciaPresupuesto, fechaEmision,
                        total,estado);
                presupuestos.add(p);
            }
        } catch (SQLException  e) {
            Log.d("DEBUG", "Error en la el metodo buscarReservasCliente");
            throw new IllegalStateException("Error en el catch del metodo buscarReservasCliente", e);
        }

        return presupuestos;
    }

    @Override
    public List<Visita> buscarAgendaVisitasComercial() {
        List<Visita> visitas = new ArrayList<>();

        try {
            query = "SELECT idNota, fechaVisita, horaVisita, conceptoaccion," +
                    " numeroCliente, nombreCliente, direccion, localidad," +
                    " codigoPostal, telefono, diaAccion, comentario, activo" +
                    " FROM dbo.agendaComerciales" +
                    " WHERE numeroDeComercial =" + DatosTrabajador.getCodigoComercial();
            ps = FactoriaDeConexiones.obtenerConexion().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()) {
               byte activo = rs.getByte(13);
                if (activo == 1) {
                    Visita v = new Visita();

                    v.setIdVisita(rs.getInt(1));
                    v.setFechaVisita(rs.getString(2));
                    v.setHoraVisita(rs.getString(3));
                    v.setConceptoAccion(rs.getString(4));
                    v.setNumeroCliente(rs.getInt(5));
                    v.setNombreCliente(rs.getString(6));
                    v.setDireccion(rs.getString(7));
                    v.setLocalidad(rs.getString(8));
                    v.setCodigoPostal(rs.getInt(9));
                    v.setTelefono(rs.getString(10));
                    v.setDiaAccion((rs.getString(11)));
                    v.setComentario(rs.getString(12));

                    visitas.add(v);
                }
            }
        } catch (SQLException  e) {
            Log.d("DEBUG", "Error en la el metodo buscarAgendaVisitasComercial");
            throw new IllegalStateException("Error en el catch del metodo buscarReservasCliente", e);
        }

        return visitas;
    }
}
