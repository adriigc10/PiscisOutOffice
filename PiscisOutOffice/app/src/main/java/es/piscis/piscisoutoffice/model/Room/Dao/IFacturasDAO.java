package es.piscis.piscisoutoffice.model.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;

@Dao
public interface IFacturasDAO {

    @Query("SELECT * FROM Factura")
    List<Factura> getAllFacturas();

    @Query("SELECT * FROM Factura WHERE id = :id")
    Factura getFactura(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarFactura(Factura f);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarListaFacturas(List<Factura> f);

    @Delete
    void eliminarFactura(Factura f);

    @Delete
    void eliminarListaFacturas(List<Factura> f);
}
