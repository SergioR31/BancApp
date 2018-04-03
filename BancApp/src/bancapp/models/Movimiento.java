/**
 * 
 */
package bancapp.models;

import java.sql.Date;

/**
 * @author SergioRamos
 *
 */
public class Movimiento {
  
  private int idMovimiento;
  private String concepto;
  private double monto;
  private Date fecha;
  private String status;
  private int tipo;
  private int idTipo;
  private long idChequera;
  /**
   * @return the idMovimiento
   */
  public int getIdMovimiento() {
    return idMovimiento;
  }
  /**
   * @param idMovimiento the idMovimiento to set
   */
  public void setIdMovimiento(int idMovimiento) {
    this.idMovimiento = idMovimiento;
  }
  /**
   * @return the concepto
   */
  public String getConcepto() {
    return concepto;
  }
  /**
   * @param concepto the concepto to set
   */
  public void setConcepto(String concepto) {
    this.concepto = concepto;
  }
  /**
   * @return the monto
   */
  public double getMonto() {
    return monto;
  }
  /**
   * @param monto the monto to set
   */
  public void setMonto(double monto) {
    this.monto = monto;
  }
  /**
   * @return the fecha
   */
  public Date getFecha() {
    return fecha;
  }
  /**
   * @param fecha the fecha to set
   */
  public void setFecha(Date fecha) {
    this.fecha = fecha;
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
  /**
   * @return the tipo
   */
  public int getTipo() {
    return tipo;
  }
  /**
   * @param tipo the tipo to set
   */
  public void setTipo(int tipo) {
    this.tipo = tipo;
  }
  /**
   * @return the idTipo
   */
  public int getIdTipo() {
    return idTipo;
  }
  /**
   * @param idTipo the idTipo to set
   */
  public void setIdTipo(int idTipo) {
    this.idTipo = idTipo;
  }
  /**
   * @return the idChequera
   */
  public long getIdChequera() {
    return idChequera;
  }
  /**
   * @param idChequera the idChequera to set
   */
  public void setIdChequera(long idChequera) {
    this.idChequera = idChequera;
  }
  
  
  
}
