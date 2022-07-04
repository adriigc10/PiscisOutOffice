package es.piscis.piscisoutoffice.view.factura;


import android.content.Context;
import android.util.Log;
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
        final Factura factura = facturas.get(position);

        // Creamos la vista de una reserva
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.factura_listview_item, null);

        TextView campoFechaFactura = view.findViewById(R.id.tv_campoRefPresupuesto);
        TextView fechaFactura = view.findViewById(R.id.tv_referenciaPresupuesto);
        TextView campoTotalFactura = view.findViewById(R.id.tv_campoFechaPresupuesto);
        TextView totalFactura = view.findViewById(R.id.tv_fechaPresupuesto);
        TextView campoNumeroFra = view.findViewById(R.id.tv_campoTotalPresupuesto);
        TextView numeroFra = view.findViewById(R.id.tv_totalPresupuesto);

//        Button descargar = view.findViewById(R.id.btn_descargarFactura);


        campoFechaFactura.setText("Fecha factura: ");
        fechaFactura.setText(factura.getFechaDocumento().split(" ")[0]);
        campoTotalFactura.setText("Total factura: ");
        totalFactura.setText(String.valueOf(factura.getTotalFactura()));
        campoNumeroFra.setText("Numerofra: ");
        numeroFra.setText(String.valueOf(factura.getNumeroFra()));


/*
        descargar.setOnClickListener(v -> {

        });
*/


        return view;
    }
}
