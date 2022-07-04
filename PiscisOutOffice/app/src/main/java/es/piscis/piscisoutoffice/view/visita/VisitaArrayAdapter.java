package es.piscis.piscisoutoffice.view.visita;

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
import es.piscis.piscisoutoffice.model.Room.Entidades.Visita;

public class VisitaArrayAdapter extends ArrayAdapter<Visita> {

    private final List<Visita> visitas;

    public VisitaArrayAdapter(@NonNull ListaVisitasActivity activity, int resource, @NonNull List<Visita> visitas) {
        super(activity, resource, visitas);
        this.visitas = visitas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Visita visita = visitas.get(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.visita_listview_item, null);

        TextView diaAccion = view.findViewById(R.id.tv_diaAccion);
        TextView nombreCliente = view.findViewById(R.id.tv_nombreClienteVisita);
        TextView conceptoAccion = view.findViewById(R.id.tv_conceptoAccion);

        diaAccion.setText(visita.getDiaAccion().substring(0, 16));
        nombreCliente.setText(visita.getNombreCliente());
        conceptoAccion.setText(visita.getConceptoAccion());

        return view;

    }
}
