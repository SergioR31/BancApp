/**
 * 
 */
package bancapp.services.implementation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bancapp.daos.interfaces.IChequeraDAO;
import bancapp.daos.interfaces.IConsultaDAO;
import bancapp.models.Movimiento;
import bancapp.services.interfaces.IConsultaService;

/**
 * @author SergioRamos
 *
 */
@Service
public class ConsultaService implements IConsultaService {
  
  @Autowired
  private IConsultaDAO consultaDAO;

  @Override
  public ArrayList<Movimiento> consultarDepositos(long idChequera, String periodo, int anio, int mes)
      throws Exception {
    // TODO Auto-generated method stub
    
    ArrayList<Movimiento> depositos = new ArrayList<>();
    
    try {
      
      depositos = consultaDAO.consultarDepositos(idChequera, periodo, anio, mes);
      
    } catch (Exception e) {
      
    }
    return depositos;
  }
  
  

}
