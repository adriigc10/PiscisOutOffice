package es.piscis.piscisoutoffice.model.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import es.piscis.piscisoutoffice.model.Room.Entidades.Comercial;

@Dao
public interface IComercialDAO {

    @Query("SELECT * from Comercial")
    Comercial getComercial();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarComercial(Comercial c);

    @Delete
    void eliminarComercial(Comercial c);

}
