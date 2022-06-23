package es.piscis.piscisoutoffice.view.cliente;


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
import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;


public class ClienteArrayAdapter extends ArrayAdapter<Cliente> {

    private final List<Cliente> clientes;

    public ClienteArrayAdapter(@NonNull ListaClientesActivity activity, int resource, @NonNull List<Cliente> clientes) {
        super(activity, resource,clientes);
        this.clientes = clientes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Cliente cliente = clientes.get(position);

        // Creamos la vista de un cliente
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.cliente_listview_item, null);

        // Links de subvistas
//        TextView codigoCliente = view.findViewById(R.id.tv_codigoCliente);
        TextView nombreCliente = view.findViewById(R.id.tv_nombreCliente);
        TextView nombreComercialCliente = view.findViewById(R.id.tv_NComercialCliente);
        TextView direccionCliente = view.findViewById(R.id.tv_direccionCliente);
        TextView localidadCliente = view.findViewById(R.id.tv_localidadCliente);
        TextView telefonoCliente = view.findViewById(R.id.tv_telefonocliente);

//        codigoCliente.setText(String.valueOf(cliente.getCodigoCliente()));
        nombreCliente.setText(cliente.getNombre());
        nombreComercialCliente.setText(cliente.getNombreComercial());
        direccionCliente.setText(cliente.getDirección());
        localidadCliente.setText(cliente.getLocalidad());
        telefonoCliente.setText(cliente.getTelefono());

        return view;
    }
}