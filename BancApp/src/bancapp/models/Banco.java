package bancapp.models;

/**
 * Modelo de datos de Banco.
 * @author SergioRamos
 *
 */
public class Banco {

  private int idBanco;
  private String entidad;
  private String sucursal;
  private String direccion;
  private String status;
  
  /**
   * Funcion que retorna el valor de idBanco.
   * @return the idBanco
   */
  public int getIdBanco() {
    return idBanco;
  }
  
  /**
   * Funcion que determina el valor de idBanco.
   * @param idBanco the idBanco to set
   */
  public void setIdBanco(int idBanco) {
    this.idBanco = idBanco;
  }
  
  /**
   * Funcion que retorna el valor de entidad.
   * @return the entidad
   */
  public String getEntidad() {
    return entidad;
  }
  
  /**
   * Funcion que determina el valor de Entidad.
   * @param entidad the entidad to set
   */
  public void setEntidad(String entidad) {
    this.entidad = entidad;
  }
  
  /**
   * Funcion que retorna el valor de Sucursal.
   * @return the sucursal
   */
  public String getSucursal() {
    return sucursal;
  }
  
  /**
   * Funcion que determina el valor de Sucursal.
   * @param sucursal the sucursal to set
   */
  public void setSucursal(String sucursal) {
    this.sucursal = sucursal;
  }
  
  /**
   * Funcion que retorna el valor de direccion.
   * @return the direccion
   */
  public String getDireccion() {
    return direccion;
  }
  
  /**
   * Funcion que determina el valor de Direccion.
   * @param direccion the direccion to set
   */
  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }
  
  /**
   * Funcion que obtiene el valor de Status.
   * @return the status
   */
  public String getStatus() {
    return status;
  }
  
  /**
   * Funcion que determina el valor de Status.
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }
  
}
