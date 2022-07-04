package es.piscis.piscisoutoffice.model.Room.Entidades;

import androidx.room.Entity;
@Entity( primaryKeys = {"codigoArticulo", "codigoTarifa"})
public class ArticulosConTarifa {
    public Long codigoArticulo;
    public Integer codigoTarifa;
}
