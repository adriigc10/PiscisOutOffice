package es.piscis.piscisoutoffice.model.Room.Entidades;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Visita {

    // ATRIBUTOS

    @PrimaryKey
    private Integer idVisita;
    private String fechaAnotacion;
    private String fechaVisita;
    private String horaVisita;
    private Integer numeroComercial;
    private String nombreComercial;
    private Integer numeroAccion;
    private String conceptoAccion;
    private Integer numeroCliente;
    private String nombreCliente;
    private String direccion;
    private String localidad;
    private Integer codigoPostal;
    private String telefono;
    private String diaAccion;
    private String comentario;
    private byte meRecibio;
    private byte produjoVenta;
    private byte activo;

    // CONSTRUCTORES

    public Visita() { }

    public Visita(Integer idVisita, String fechaAnotacion, String fechaVisita, String horaVisita,
                  Integer numeroComercial, String nombreComercial, Integer numeroAccion,
                  String conceptoAccion, Integer numeroCliente, String nombreCliente,
                  String direccion, String localidad, Integer codigoPostal, String telefono,
                  String diaAccion, String comentario, byte meRecibio, byte produjoVenta, byte activo) {
        this.idVisita = idVisita;
        this.fechaAnotacion = fechaAnotacion;
        this.fechaVisita = fechaVisita;
        this.horaVisita = horaVisita;
        this.numeroComercial = numeroComercial;
        this.nombreComercial = nombreComercial;
        this.numeroAccion = numeroAccion;
        this.conceptoAccion = conceptoAccion;
        this.numeroCliente = numeroCliente;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.diaAccion = diaAccion;
        this.comentario = comentario;
        this.meRecibio = meRecibio;
        this.produjoVenta = produjoVenta;
        this.activo = activo;
    }

    // GETTERS

    public Integer getIdVisita() {
        return idVisita;
    }

    public String getFechaAnotacion() {
        return fechaAnotacion;
    }

    public String getFechaVisita() {
        return fechaVisita;
    }

    public String getHoraVisita() {
        return horaVisita;
    }

    public Integer getNumeroComercial() {
        return numeroComercial;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public Integer getNumeroAccion() {
        return numeroAccion;
    }

    public String getConceptoAccion() {
        return conceptoAccion;
    }

    public Integer getNumeroCliente() {
        return numeroCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDiaAccion() {
        return diaAccion;
    }

    public String getComentario() {
        return comentario;
    }

    public byte getMeRecibio() {
        return meRecibio;
    }

    public byte getProdujoVenta() {
        return produjoVenta;
    }

    public byte getActivo() {
        return activo;
    }

    // SETTERS

    public void setIdVisita(Integer idVisita) {
        this.idVisita = idVisita;
    }

    public void setFechaAnotacion(String fechaAnotacion) {
        this.fechaAnotacion = fechaAnotacion;
    }

    public void setFechaVisita(String fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public void setHoraVisita(String horaVisita) {
        this.horaVisita = horaVisita;
    }

    public void setNumeroComercial(Integer numeroComercial) {
        this.numeroComercial = numeroComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public void setNumeroAccion(Integer numeroAccion) {
        this.numeroAccion = numeroAccion;
    }

    public void setConceptoAccion(String conceptoAccion) {
        this.conceptoAccion = conceptoAccion;
    }

    public void setNumeroCliente(Integer numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDiaAccion(String diaAccion) {
        this.diaAccion = diaAccion;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setMeRecibio(byte meRecibio) {
        this.meRecibio = meRecibio;
    }

    public void setProdujoVenta(byte produjoVenta) {
        this.produjoVenta = produjoVenta;
    }

    public void setActivo(byte activo) {
        this.activo = activo;
    }


}
