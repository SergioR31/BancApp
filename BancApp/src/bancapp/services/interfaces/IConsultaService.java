package bancapp.services.interfaces;

import bancapp.models.Chequera;
import bancapp.models.Movimiento;

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
  
  public ArrayList<Movimiento> consultarRetiros(
      long idChequera, String periodo, int anio, int mes) throws Exception;
  
  public ArrayList<Movimiento> consultarTodos(
      long idChequera, String periodo, int anio, int mes) throws Exception;
}
