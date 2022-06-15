package es.piscis.piscisoutoffice.model.OperacionesBBDD;

public interface IOperacionesBBDD {

    boolean comprobarConexion();

    boolean autenticarseBBDD(String dni, String contrasena);

    String conocerRol();
}
