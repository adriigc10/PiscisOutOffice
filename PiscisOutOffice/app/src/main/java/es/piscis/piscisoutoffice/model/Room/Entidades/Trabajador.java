package es.piscis.piscisoutoffice.model.Room.Entidades;

public class Trabajador {

    // ATRIBUTOS

    private String dni;
    private String nombre;
    private String apellidos;
    private String contrasenha;

    // CONSTRUCTOR

    public Trabajador() {}

    public Trabajador(String dni, String nombre, String apellidos,
                      String contrasenha) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasenha = contrasenha;
    }

    // GETTERS

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    // SETTERS


    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }
}
