package es.piscis.piscisoutoffice.view.articulo;

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
import es.piscis.piscisoutoffice.model.Room.Entidades.Articulo;

public class ArticuloArrayAdapter extends ArrayAdapter<Articulo> {

    List<Articulo> articulos;

    public ArticuloArrayAdapter(@NonNull ListaArticulosActivity activity, int resource, @NonNull List<Articulo> articulos) {
        super(activity, resource, articulos);
        this.articulos = articulos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Articulo articulo = articulos.get(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.articulo_listview_item, null);

        TextView codigoArticulo = view.findViewById(R.id.tv_codigoArticulo);
        TextView descripcionArticulo = view.findViewById(R.id.tv_descripcionArticulo);
        TextView pvpArticulo = view.findViewById(R.id.tv_pvpArticulo);

        codigoArticulo.setText(String.valueOf(articulo.getCodArt()));
        descripcionArticulo.setText(articulo.getDescripcion());
        pvpArticulo.setText(String.valueOf(articulo.getPvp()));

        return view;
    }
}
