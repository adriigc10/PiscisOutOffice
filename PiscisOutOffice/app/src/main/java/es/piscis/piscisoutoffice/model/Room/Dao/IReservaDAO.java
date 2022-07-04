package es.piscis.piscisoutoffice.model.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;

@Dao
public interface IReservaDAO {

    @Query("SELECT * FROM Reserva")
    List<Reserva> getAllReservas();

    @Query("SELECT * FROM Reserva WHERE id = :id")
    Reserva getReserva(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarReserva(Reserva r);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarListaReservas(List<Reserva> r);

    @Delete
    void eliminarReserva(Reserva r);

    @Delete
    void eliminarListaReservas(List<Reserva> r);
}
