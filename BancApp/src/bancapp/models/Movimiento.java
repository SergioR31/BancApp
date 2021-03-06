/**
 * 
 */
package bancapp.models;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author SergioRamos
 *
 */
public class Movimiento {
  
  private int idMovimiento;
  private String concepto;
  private double monto;
  private Timestamp fecha;
  private String status;
  private String operacion;
  private int idTipo;
  private long idChequera;
  private double saldo;
  
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
  public Timestamp getFecha() {
    return fecha;
  }
  /**
   * @param fecha the fecha to set
   */
  public void setFecha(Timestamp fecha) {
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
   * @return the operacion
   */
  public String getOperacion() {
    return operacion;
  }
  /**
   * @param operacion the operacion to set
   */
  public void setOperacion(String operacion) {
    this.operacion = operacion;
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
  /**
   * @return the saldo
   */
  public double getSaldo() {
    return saldo;
  }
  /**
   * @param saldo the saldo to set
   */
  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }
  
  
  
}
