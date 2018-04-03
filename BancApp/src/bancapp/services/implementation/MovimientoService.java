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
 * Implementacion del Service de Movimiento.
 * @author SergioRamos
 *
 */
@Service
public class MovimientoService implements IMovimientoService {
  
  @Autowired
  private IMovimientoDAO movimientoDAO;
  
  @Autowired
  private IChequeraDAO chequeraDAO;

  /* (non-Javadoc)
   * @see bancapp.services.interfaces.IChequeraService#insertarChequera(bancapp.models.Chequera)
   */
  
  @Override
  public String hacerRetiro(Movimiento movimiento) throws Exception {
    
    String respuesta = "";
    
    Chequera chequera = new Chequera();
    
    try {
      
      chequera = chequeraDAO.consultarChequera(movimiento.getIdChequera());
      
      if (chequera.getSaldo() < movimiento.getMonto()) {
        respuesta = "Saldo insuficiente!!";
      } else {
        respuesta = movimientoDAO.hacerRetiro(movimiento);
      }
      
    } catch (Exception e) {
      System.out.println("Error en hacerRetiro MovimientoService: " + e);
    }
    
    return respuesta;
  }

  @Override
  public String hacerDeposito(Movimiento movimiento) throws Exception {
    // TODO Auto-generated method stub
    String respuesta = "";
    
    try {
      respuesta = movimientoDAO.hacerDeposito(movimiento);
    } catch (Exception e) {
      System.out.println("Error en hacerRetiro MovimientoService: " + e);
    }
    
    return respuesta;
  }  

}
