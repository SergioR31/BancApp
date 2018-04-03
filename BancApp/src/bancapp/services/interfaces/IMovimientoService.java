package bancapp.services.interfaces;

import bancapp.models.Chequera;
import bancapp.models.Movimiento;

import java.util.List;

/**
 * Interfaz del Service de Chequera.
 * @author SergioRamos
 *
 */
public interface IMovimientoService {
  
//  public List<Chequera> listarChequeras() throws Exception;
  
  public String hacerRetiro(Movimiento movimiento) throws Exception;
  
//  public Chequera consultarChequera(int idChequera) throws Exception;
  
//  public String modificarChequera(Chequera chequera) throws Exception;
  
//  public String eliminarChequera(long idChequera) throws Exception;
}
