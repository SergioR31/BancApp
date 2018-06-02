package bancapp.services.interfaces;

import bancapp.models.Chequera;
import bancapp.models.Estadisticas;
import bancapp.models.Movimiento;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz del Service de Chequera.
 * @author SergioRamos
 *
 */
public interface IConsultaService {
  
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
  
  public ArrayList<Movimiento> consultarAnteriores(
      long idChequera, String periodo, int anio, int mes) throws Exception;
  
  public ArrayList<Movimiento> consultarTodosFecha(
      long idChequera, String desde, String hasta) throws Exception;
  
  public Estadisticas consultarEstadisticas() throws Exception;
}
