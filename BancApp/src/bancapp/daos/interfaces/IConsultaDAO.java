/**
 * 
 */
package bancapp.daos.interfaces;

import java.sql.Timestamp;
import java.util.ArrayList;

import bancapp.models.Movimiento;

/**
 * @author SergioRamos
 *
 */
public interface IConsultaDAO {
  
  public ArrayList<Movimiento> consultarDepositos(
      long idChequera, String periodo, int anio, int mes) throws Exception;
  
  public ArrayList<Movimiento> consultarDepositosFecha(
      long idChequera, String desde, String hasta) throws Exception;
  
  public ArrayList<Movimiento> consultarRetiros(
      long idChequera, String periodo, int anio, int mes) throws Exception;
  
  public ArrayList<Movimiento> consultarRetirosFecha(
      long idChequera, String desde, String hasta) throws Exception;
  
  public ArrayList<Movimiento> consultarTodos(
      long idChequera, String periodo, int anio, int mes) throws Exception;
  
  public ArrayList<Movimiento> consultarTodosFecha(
      long idChequera, String desde, String hasta) throws Exception;

}
