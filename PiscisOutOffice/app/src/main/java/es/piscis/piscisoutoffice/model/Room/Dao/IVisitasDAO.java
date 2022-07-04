package es.piscis.piscisoutoffice.model.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Visita;

@Dao
public interface IVisitasDAO {

    @Query("SELECT * FROM Visita where idVisita = :id")
    Visita getVisita(Integer id);

    @Query("SELECT * FROM Visita")
    List<Visita> getAllVisitas();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarVisita(Visita v);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarListaVisitas(List<Visita> v);

    @Delete
    void eliminarVisita(Visita v);

    @Delete
    void eliminarVisitas(List<Visita> v);
}
