package bancapp.services.implementation;

import bancapp.daos.interfaces.IchequeraDao;
import bancapp.daos.interfaces.ImovimientoDao;
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
  private ImovimientoDao movimientoDAO;
  
  @Autowired
  private IchequeraDao chequeraDAO;

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

  @Override
  public String hacerTransferencia(Movimiento movimiento, long clabe) throws Exception {
    // TODO Auto-generated method stub
    
    String respuesta = "";
    
    boolean clabeExiste = false;;
    
    Chequera chequera = new Chequera();
    
    List<Chequera> chequeras = new ArrayList<>();
    

    try {
      
      chequeras = chequeraDAO.listarChequeras();
      
      for(Chequera ch: chequeras) {
        if (ch.getClabe() == clabe) {
          clabeExiste = true;
          chequera = ch;
        }
      }
      
      if (clabeExiste) {
      
        if (chequera.getSaldo() < movimiento.getMonto()) {
          respuesta = "Saldo insuficiente!!";
          
        } else {
          
          respuesta = movimientoDAO.hacerTransferencia(movimiento, clabe);
          
        }
        
      } else {
        respuesta = "La CLABE no esta asociada a ninguna chequera!!";
      }
      
    } catch (Exception e) {
      System.out.println("Error en hacerTransferencia MovimientoService: " + e);
    }
    
    return respuesta;
    
  }  

}
