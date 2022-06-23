package es.piscis.piscisoutoffice.view.factura;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import es.piscis.piscisoutoffice.R;
import es.piscis.piscisoutoffice.model.Room.Entidades.Factura;

public class FacturaArrayAdapter extends ArrayAdapter<Factura> {

    private final List<Factura> facturas;

    public FacturaArrayAdapter(@NonNull ListaFacturasActivity activity, int resource, @NonNull List<Factura> facturas) {
        super(activity, resource, facturas);
        this.facturas = facturas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Factura factura = facturas.get(position);

        // Creamos la vista de una reserva
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.factura_listview_item, null);

        TextView campoFechaFactura = view.findViewById(R.id.tv_campoFechaFactura);
        TextView fechaFactura = view.findViewById(R.id.tv_fechaFactura);
        TextView campoTotalFactura = view.findViewById(R.id.tv_campoTotalFactura);
        TextView totalFactura = view.findViewById(R.id.tv_totalFactura);
        TextView campoNumeroFra = view.findViewById(R.id.tv_campoNumeroFra);
        TextView numeroFra = view.findViewById(R.id.tv_numeroFra);

//        Button descargar = view.findViewById(R.id.btn_descargarFactura);


        campoFechaFactura.setText("Fecha factura: ");
        fechaFactura.setText(factura.getFechaDocumento());
        campoTotalFactura.setText("Total factura: ");
        totalFactura.setText(String.valueOf(factura.getTotalFactura()));
        campoNumeroFra.setText("Numerofra: ");
        numeroFra.setText(String.valueOf(factura.getNumeroFra()));

//        descargar.setOnClickListener(this);

        return view;
    }
}
