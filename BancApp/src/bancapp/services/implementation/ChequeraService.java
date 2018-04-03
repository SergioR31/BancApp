package bancapp.services.implementation;

import bancapp.daos.interfaces.IChequeraDAO;
import bancapp.models.Chequera;
import bancapp.services.interfaces.IChequeraService;

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
public class ChequeraService implements IChequeraService {
  
  @Autowired
  private IChequeraDAO chequeraDAO;

  /* (non-Javadoc)
   * @see bancapp.services.interfaces.IChequeraService#insertarChequera(bancapp.models.Chequera)
   */
  
  @Override
  public List<Chequera> listarChequeras() throws Exception {
    
    ArrayList<Chequera> chequeras = new ArrayList<Chequera>();
    
    try {
      chequeras = (ArrayList<Chequera>) chequeraDAO.listarChequeras();
    } catch (Exception e) {
      System.out.println("Error en listarChequeras en ChequeraService: " + e);
    }
    return chequeras;
  }
  
  @Override
  public Chequera consultarChequera(int idChequera) throws Exception {
    
    Chequera chequera = new Chequera();
    
    try {
      chequera = chequeraDAO.consultarChequera(idChequera);
    } catch (Exception e) {
      System.out.println("Error en consultarChequera en Chequera Service: " + e);
    }
    return chequera;
  }
  
  @Override
  public String insertarChequera(Chequera chequera) throws Exception {
    
    String respuesta = "";
    
    try {
      respuesta = chequeraDAO.insertarChequera(chequera);
    } catch (Exception e) {
      System.out.println("Error en insertarChequera ChequeraService: " + e);
    }
    
    return respuesta;
  }

//  @Override
//  public String modificarChequera(Chequera chequera) throws Exception {
//    
//    String respuesta = "";
//    
//    try {
//      respuesta = chequeraDAO.modificarChequera(chequera);
//    } catch (Exception e) {
//      System.out.println("Error en modificarChequera en ChequeraService");
//    }
//    return respuesta;
//  }

  @Override
  public String eliminarChequera(long idChequera) throws Exception {
    String respuesta = "";
    
    try {
      respuesta = chequeraDAO.eliminarChequera(idChequera);
    } catch (Exception e) {
      System.out.println("Error en eliminarChequera en ChequeraService");
    }
    return respuesta;
  }

  

}
