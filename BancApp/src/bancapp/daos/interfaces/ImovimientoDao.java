package bancapp.daos.interfaces;

import bancapp.models.Movimiento;

/**
 * Interfaz del Service de Chequera.
 * @author SergioRamos
 *
 */
public interface ImovimientoDao {
  
  
  public String hacerRetiro(Movimiento movimiento) throws Exception;
  
  public String hacerDeposito(Movimiento movimiento) throws Exception;
  
  public String hacerTransferencia(Movimiento movimiento, long clabe) throws Exception;

}
