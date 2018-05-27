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
  
}
