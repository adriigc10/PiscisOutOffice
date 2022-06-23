package es.piscis.piscisoutoffice.view.reserva;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.List;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.Room.Entidades.Reserva;

public class ReservaArrayAdapter extends ArrayAdapter<Reserva> {

    private final List<Reserva> reservas;

    public ReservaArrayAdapter(@NonNull ListaReservasActivity activity, int resource, @NonNull List<Reserva> reservas) {
        super(activity, resource, reservas);
        this.reservas = reservas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Reserva reserva = reservas.get(position);

        // Creamos la vista de una reserva
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.reserva_listview_item, null);

        TextView nombreArticulo = view.findViewById(R.id.tv_nombreArticulo);
        TextView unidadesArticulo = view.findViewById(R.id.tv_unidadesArticulo);
        TextView comentarioReserva = view.findViewById(R.id.tv_comentario);
        ImageView fotoArticulo = view.findViewById(R.id.iv_fotoArticulo);

        nombreArticulo.setText(reserva.getNombreArticulo());
        unidadesArticulo.setText(String.valueOf(reserva.getUnidades()));
        comentarioReserva.setText(reserva.getComentario());

        Picasso.get().load(reserva.getUrlDescarga()).into(fotoArticulo);

        return view;
    }
}
