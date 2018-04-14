/**
 * 
 */
package bancapp.models;

import java.sql.Date;

/**
 * @author SergioRamos
 *
 */
public class Estadisticas {
  
  private int numeroBancos;
  private int numeroClientes;
  private int numeroChequeras;
  /**
   * @return the numeroBancos
   */
  public int getNumeroBancos() {
    return numeroBancos;
  }
  /**
   * @param numeroBancos the numeroBancos to set
   */
  public void setNumeroBancos(int numeroBancos) {
    this.numeroBancos = numeroBancos;
  }
  /**
   * @return the numeroClientes
   */
  public int getNumeroClientes() {
    return numeroClientes;
  }
  /**
   * @param numeroClientes the numeroClientes to set
   */
  public void setNumeroClientes(int numeroClientes) {
    this.numeroClientes = numeroClientes;
  }
  /**
   * @return the numeroChequeras
   */
  public int getNumeroChequeras() {
    return numeroChequeras;
  }
  /**
   * @param numeroChequeras the numeroChequeras to set
   */
  public void setNumeroChequeras(int numeroChequeras) {
    this.numeroChequeras = numeroChequeras;
  }
  
  

}
