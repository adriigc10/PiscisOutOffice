package es.piscis.piscisoutoffice.model.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import es.piscis.piscisoutoffice.model.Room.Entidades.Comercial;
import es.piscis.piscisoutoffice.model.Room.Entidades.Repartidor;

@Dao
public interface IRepartidorDAO {

    @Query("SELECT * from Repartidor")
    Comercial getRepartidor();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarRepartidor(Repartidor r);

    @Delete
    void eliminarRepartidor(Repartidor r);
}
