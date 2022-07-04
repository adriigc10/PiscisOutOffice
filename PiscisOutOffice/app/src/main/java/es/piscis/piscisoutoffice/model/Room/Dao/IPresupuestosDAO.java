package es.piscis.piscisoutoffice.model.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Presupuesto;

@Dao
public interface IPresupuestosDAO {
    
    @Query("SELECT * FROM Presupuesto order by fechaPresupuesto DESC")
    List<Presupuesto> getAllPresupuesto();

    @Query("SELECT * FROM Presupuesto WHERE idPresupuesto = :id")
    Presupuesto getPresupuesto(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarPresupuesto(Presupuesto p);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarListaPresupuestos(List<Presupuesto> p);

    @Delete
    void eliminarPresupuesto(Presupuesto p);

    @Delete
    void eliminarListaPresupuestos(List<Presupuesto> p);
}
