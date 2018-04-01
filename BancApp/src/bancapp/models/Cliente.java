/**
 * 
 */

package bancapp.models;

import java.sql.Date;

/**
 * Modelo de datos de Cliente.
 * @author SergioRamos
 *
 */
public class Cliente {
  
  private int idCliente;
  private String nombre;
  private String apellidoPaterno;
  private String apellidoMaterno;
  private String direccion;
  private String estado;
  private int codigoPostal;
  private long telefono;
  private String correo;
  private Date fechaNacimiento;
  private String rfc;
  private String status;
  
  /**
   * @return the idCliente
   */
  public int getIdCliente() {
    return idCliente;
  }
  
  /**
   * @param idCliente the idCliente to set
   */
  public void setIdCliente(int idCliente) {
    this.idCliente = idCliente;
  }
  
  /**
   * @return the nombre
   */
  public String getNombre() {
    return nombre;
  }
  
  /**
   * @param nombre the nombre to set
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  /**
   * @return the apellidoPaterno
   */
  public String getApellidoPaterno() {
    return apellidoPaterno;
  }
  
  /**
   * @param apellidoPaterno the apellidoPaterno to set
   */
  public void setApellidoPaterno(String apellidoPaterno) {
    this.apellidoPaterno = apellidoPaterno;
  }
  
  /**
   * @return the apellidoMaterno
   */
  public String getApellidoMaterno() {
    return apellidoMaterno;
  }
  
  /**
   * @param apellidoMaterno the apellidoMaterno to set
   */
  public void setApellidoMaterno(String apellidoMaterno) {
    this.apellidoMaterno = apellidoMaterno;
  }
  
  /**
   * @return the direccion
   */
  public String getDireccion() {
    return direccion;
  }
  
  /**
   * @param direccion the direccion to set
   */
  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }
  
  /**
   * @return the estado
   */
  public String getEstado() {
    return estado;
  }
  
  /**
   * @param estado the estado to set
   */
  public void setEstado(String estado) {
    this.estado = estado;
  }
  
  /**
   * @return the codigoPostal
   */
  public int getCodigoPostal() {
    return codigoPostal;
  }
  
  /**
   * @param codigoPostal the codigoPostal to set
   */
  public void setCodigoPostal(int codigoPostal) {
    this.codigoPostal = codigoPostal;
  }
  
  /**
   * @return the telefono
   */
  public long getTelefono() {
    return telefono;
  }
  
  /**
   * @param telefono the telefono to set
   */
  public void setTelefono(long telefono) {
    this.telefono = telefono;
  }
  
  /**
   * @return the correo
   */
  public String getCorreo() {
    return correo;
  }
  
  /**
   * @param correo the correo to set
   */
  public void setCorreo(String correo) {
    this.correo = correo;
  }
  
  /**
   * @return the fechaNacimiento
   */
  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }
  
  /**
   * @param fechaNacimiento the fechaNacimiento to set
   */
  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }
  
  /**
   * @return the rfc
   */
  public String getRfc() {
    return rfc;
  }
  
  /**
   * @param rfc the rfc to set
   */
  public void setRfc(String rfc) {
    this.rfc = rfc;
  }
  
  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }
  
  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }
  
}
