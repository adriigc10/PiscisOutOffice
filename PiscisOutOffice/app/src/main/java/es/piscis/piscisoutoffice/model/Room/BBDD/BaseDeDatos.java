package es.piscis.piscisoutoffice.model.Room.BBDD;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.piscis.piscisoutoffice.model.Room.Conversores.DateConversor;
import es.piscis.piscisoutoffice.model.Room.Conversores.TimeConversor;
import es.piscis.piscisoutoffice.model.Room.Dao.IArticulosDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IClienteDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IComercialDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IFacturasDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IPresupuestosDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IRepartidorDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IReservaDAO;
import es.piscis.piscisoutoffice.model.Room.Dao.IVisitasDAO;
import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;
import es.piscis.piscisoutoffice.model.Room.Entidades.Comercial;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;
import es.piscis.piscisoutoffice.model.Room.Entidades.Presupuesto;
import es.piscis.piscisoutoffice.model.Room.Entidades.Repartidor;
import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;
import es.piscis.piscisoutoffice.model.Room.Entidades.Visita;

@Database(entities = {Articulo.class, Cliente.class, Comercial.class, Factura.class,
        Presupuesto.class, Repartidor.class, Reserva.class, Visita.class}, version = 1)

@TypeConverters({DateConversor.class, TimeConversor.class})
public abstract class BaseDeDatos extends RoomDatabase {

    // INSTANCIA

    private static BaseDeDatos INSTANCIA;

    // ATRIBUTOS

    private static final String NOMBRE_BBDD  = "Piscis_DDBB";

    // DAOs

    public abstract IArticulosDAO articulosDAO();
    public abstract IClienteDAO clienteDAO();
    public abstract IComercialDAO comercialDAO();
    public abstract IFacturasDAO facturasDAO();
    public abstract IPresupuestosDAO presupuestosDAO();
    public abstract IRepartidorDAO repartidorDAO();
    public abstract IReservaDAO reservasDAO();
    public abstract IVisitasDAO visitasDAO();

    // GETTERS

    public static BaseDeDatos getInstancia(final Context context) {
        if (INSTANCIA == null) {
            synchronized (BaseDeDatos.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context.getApplicationContext(),
                                 BaseDeDatos.class, NOMBRE_BBDD)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCIA;
    }
}
