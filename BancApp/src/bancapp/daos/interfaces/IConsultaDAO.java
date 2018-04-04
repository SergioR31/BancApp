/**
 * 
 */
package bancapp.daos.interfaces;

import java.util.ArrayList;

import bancapp.models.Movimiento;

/**
 * @author SergioRamos
 *
 */
public interface IConsultaDAO {
  
  public ArrayList<Movimiento> consultarDepositos(long idChequera, String periodo, int anio, int mes) throws Exception;
  
}
