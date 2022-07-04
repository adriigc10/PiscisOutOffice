package es.piscis.piscisoutoffice.model.Comparadores;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Date;
import java.sql.Time;
import java.util.Comparator;

import es.piscis.piscisoutoffice.model.Room.Entidades.Visita;

public class ComparadorVisitasPorFecha implements Comparator<Visita> {

    @Override
    public int compare(Visita v1, Visita v2) {
        String diaAccion1 = v1.getDiaAccion().split(" ")[0];
        String horaAccion1 = v1.getDiaAccion().split(" ")[1];
        String diaAccion2 = v2.getDiaAccion().split(" ")[0];
        String horaAccion2 = v2.getDiaAccion().split(" ")[1];

        if (Date.valueOf(diaAccion1).compareTo(Date.valueOf(diaAccion2)) == 0){
            return Time.valueOf(horaAccion1).compareTo(Time.valueOf(horaAccion2));
        } else{
            return Date.valueOf(diaAccion1).compareTo(Date.valueOf(diaAccion2));
        }
    }
}
