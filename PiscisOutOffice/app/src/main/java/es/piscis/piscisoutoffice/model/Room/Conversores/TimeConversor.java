package es.piscis.piscisoutoffice.model.Room.Conversores;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.sql.Date;
import java.sql.Time;

public class TimeConversor {

    @TypeConverter
    public static Long TimeToLong(Time time) {
        return time == null ? null : time.getTime();
    }

    @TypeConverter
    public static Time fromLong(Long value) {
        return value == null ? null : new Time(value);
    }
}
