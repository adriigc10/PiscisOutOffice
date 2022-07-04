package es.piscis.piscisoutoffice.model.Room.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;

@Dao
public interface IClienteDAO {

    @Query("SELECT * FROM Cliente ORDER BY nombreComercial")
    List<Cliente> getAllClientes();

    @Query("SELECT * FROM Cliente WHERE codigoCliente = :codCli")
    Cliente getCliente(Integer codCli);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarCliente(Cliente c);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertarListaClientes(List<Cliente> c);

    @Delete
    void eliminarCliente(Cliente c);

    @Delete()
    void eliminarListaClientes(List<Cliente> c);

}
