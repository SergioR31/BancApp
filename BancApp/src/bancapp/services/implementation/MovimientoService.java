package bancapp.services.implementation;

import bancapp.daos.interfaces.IChequeraDAO;
import bancapp.daos.interfaces.IMovimientoDAO;
import bancapp.models.Chequera;
import bancapp.models.Movimiento;
import bancapp.services.interfaces.IChequeraService;
import bancapp.services.interfaces.IMovimientoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementacion del Service de Chequera.
 * @author SergioRamos
 *
 */
@Service
public class MovimientoService implements IMovimientoService {
  
  @Autowired
  private IMovimientoDAO movimientoDAO;

  /* (non-Javadoc)
   * @see bancapp.services.interfaces.IChequeraService#insertarChequera(bancapp.models.Chequera)
   */
  
  @Override
  public String hacerRetiro(Movimiento movimiento) throws Exception {
    
    String respuesta = "";
    
    try {
      respuesta = movimientoDAO.hacerRetiro(movimiento);
    } catch (Exception e) {
      System.out.println("Error en hacerRetiro MovimientoService: " + e);
    }
    
    return respuesta;
  }  

}
