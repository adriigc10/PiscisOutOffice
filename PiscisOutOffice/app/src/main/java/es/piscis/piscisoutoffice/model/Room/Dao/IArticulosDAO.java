package es.piscis.piscisoutoffice.model.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;


@Dao
public interface IArticulosDAO {

    @Query("SELECT * FROM Articulo")
    List<Articulo> getAllArticulos();

    @Query("SELECT * FROM Articulo WHERE codArt = :codArt")
    Articulo getArticulo(Integer codArt);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarArticulo(Articulo a);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarListaArticulos(List<Articulo> a);

    @Delete
    void eliminarArticulo(Articulo a);

    @Delete
    void eliminarListaArticulos(List<Articulo> a);
}
