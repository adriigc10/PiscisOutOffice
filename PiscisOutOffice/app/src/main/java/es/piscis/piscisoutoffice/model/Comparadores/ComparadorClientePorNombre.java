package es.piscis.piscisoutoffice.model.Comparadores;

import java.util.Comparator;

import es.piscis.piscisoutoffice.model.Room.Entidades.Cliente;

public class ComparadorClientePorNombre implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {
        return (c1.getNombre().compareToIgnoreCase(c2.getNombre()));
    }
}
