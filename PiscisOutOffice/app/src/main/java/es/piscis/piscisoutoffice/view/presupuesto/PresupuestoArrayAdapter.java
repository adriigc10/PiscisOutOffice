package es.piscis.piscisoutoffice.view.presupuesto;

import android.app.ActivityManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.Room.Entidades.Presupuesto;

public class PresupuestoArrayAdapter extends ArrayAdapter<Presupuesto> {

    private final List<Presupuesto> presupuestos;

    public PresupuestoArrayAdapter(@NonNull ListaPresupuestosActivity activity, int resource, @NonNull List<Presupuesto> presupuestos) {
        super(activity, resource, presupuestos);
        this.presupuestos = presupuestos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Presupuesto presupuesto = presupuestos.get(position);

        // Creamos la vista de una reserva
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.presupuesto_listview_item, null);

        TextView refPresupuesto = view.findViewById(R.id.tv_referenciaPresupuesto);
        TextView fechaPresupuesto = view.findViewById(R.id.tv_fechaPresupuesto);
        TextView totalPresupuesto = view.findViewById(R.id.tv_totalPresupuesto);
        TextView estadoPresupuesto = view.findViewById(R.id.tv_estadoPresupuesto);

        refPresupuesto.setText(presupuesto.getReferenciaPresupuesto());
        fechaPresupuesto.setText(presupuesto.getFechaPresupuesto().split(" ")[0]);
        totalPresupuesto.setText(String.valueOf(presupuesto.getTotal()));
        estadoPresupuesto.setText(presupuesto.getEstado());

        return view;
    }
}
