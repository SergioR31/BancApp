/**
 * 
 */
package bancapp.models;

import java.util.ArrayList;

/**
 * @author SergioRamos
 *
 */
public class EstadoCuenta {
  
  private Cliente cliente;
  private Chequera chequera;
  private Banco banco;
  private ArrayList<Movimiento> movimientos;
  private int anio;
  private String mes;
  private double totalRetiros;
  private double totalDepositos;
  private int numRetiros;
  private int numDepositos;
  
  /**
   * @return the cliente
   */
  public Cliente getCliente() {
    return cliente;
  }
  /**
   * @param cliente the cliente to set
   */
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
  /**
   * @return the chequera
   */
  public Chequera getChequera() {
    return chequera;
  }
  /**
   * @param chequera the chequera to set
   */
  public void setChequera(Chequera chequera) {
    this.chequera = chequera;
  }
  /**
   * @return the banco
   */
  public Banco getBanco() {
    return banco;
  }
  /**
   * @param banco the banco to set
   */
  public void setBanco(Banco banco) {
    this.banco = banco;
  }
  /**
   * @return the movimientos
   */
  public ArrayList<Movimiento> getMovimientos() {
    return movimientos;
  }
  /**
   * @param movimientos the movimientos to set
   */
  public void setMovimientos(ArrayList<Movimiento> movimientos) {
    this.movimientos = movimientos;
  }
  /**
   * @return the anio
   */
  public int getAnio() {
    return anio;
  }
  /**
   * @param anio the anio to set
   */
  public void setAnio(int anio) {
    this.anio = anio;
  }
  /**
   * @return the mes
   */
  public String getMes() {
    return mes;
  }
  /**
   * @param mes the mes to set
   */
  public void setMes(String mes) {
    this.mes = mes;
  }
  /**
   * @return the totalRetiros
   */
  public double getTotalRetiros() {
    return totalRetiros;
  }
  /**
   * @param totalRetiros the totalRetiros to set
   */
  public void setTotalRetiros(double totalRetiros) {
    this.totalRetiros = totalRetiros;
  }
  /**
   * @return the totalDepositos
   */
  public double getTotalDepositos() {
    return totalDepositos;
  }
  /**
   * @param totalDepositos the totalDepositos to set
   */
  public void setTotalDepositos(double totalDepositos) {
    this.totalDepositos = totalDepositos;
  }
  /**
   * @return the numRetiros
   */
  public int getNumRetiros() {
    return numRetiros;
  }
  /**
   * @param numRetiros the numRetiros to set
   */
  public void setNumRetiros(int numRetiros) {
    this.numRetiros = numRetiros;
  }
  /**
   * @return the numDepositos
   */
  public int getNumDepositos() {
    return numDepositos;
  }
  /**
   * @param numDepositos the numDepositos to set
   */
  public void setNumDepositos(int numDepositos) {
    this.numDepositos = numDepositos;
  }
  
}
