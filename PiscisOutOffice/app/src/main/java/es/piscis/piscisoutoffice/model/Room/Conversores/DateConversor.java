package es.piscis.piscisoutoffice.model.Room.Conversores;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.sql.Date;

public class DateConversor {

    @TypeConverter
    public static Long DateToLong(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Date fromLong(Long value) {
        return value == null ? null : new Date(value);
    }

}
