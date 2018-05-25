package bancapp.services.implementation;

import bancapp.daos.interfaces.IchequeraDao;
import bancapp.daos.interfaces.ImovimientoDao;
import bancapp.models.Chequera;
import bancapp.models.Movimiento;
import bancapp.services.interfaces.IMovimientoService;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
  private ImovimientoDao movimientoDao;
  
  @Autowired
  private IchequeraDao chequeraDao;

  /* (non-Javadoc)
   * @see bancapp.services.interfaces.IChequeraService#insertarChequera(bancapp.models.Chequera)
   */
  
  @Override
  public String hacerRetiro(Movimiento movimiento) throws Exception {
    
    String respuesta = "";
    
    Chequera chequera = new Chequera();
    
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    
    try {
      
      chequera = chequeraDao.consultarChequera(movimiento.getIdChequera());
      
      if (chequera.getSaldo() < movimiento.getMonto()) {
        respuesta = "Saldo insuficiente!!";
      } else {

        String anio = movimiento.getFecha().toString().substring(0, 4);
        String mes = movimiento.getFecha().toString().substring(5, 7);
        String dia = movimiento.getFecha().toString().substring(8, 10);
        
        String fecha = anio + "-" + mes + "-" + dia;

        Date fechaMovimiento = Date.valueOf(fecha);
        
        if (fechaMovimiento.compareTo(chequera.getFechaApertura()) < 0) {
          respuesta = "Fecha de movimiento antes de la fecha de "
              + "creacion de la chequera. Retiro no realizado.";
        } else if (movimiento.getFecha().compareTo(timestamp) > 0) {
          respuesta = "Fecha de movimiento despues de fecha actual";
        } else {
          respuesta = movimientoDao.hacerRetiro(movimiento);
        }
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
    
    Chequera chequera = new Chequera();
    
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    
    try {
      
      chequera = chequeraDao.consultarChequera(movimiento.getIdChequera());
      
      String anio = movimiento.getFecha().toString().substring(0, 4);
      String mes = movimiento.getFecha().toString().substring(5, 7);
      String dia = movimiento.getFecha().toString().substring(8, 10);
      
      String fecha = anio + "-" + mes + "-" + dia;

      Date fechaMovimiento = Date.valueOf(fecha);
      
      if (fechaMovimiento.compareTo(chequera.getFechaApertura()) < 0) {
        respuesta = "Fecha de movimiento antes de la fecha de "
            + "creacion de la chequera. Deposito no realizado.";
      } else if (movimiento.getFecha().compareTo(timestamp) > 0) {
        respuesta = "Fecha de movimiento despues de fecha actual";
      } else {
        respuesta = movimientoDao.hacerDeposito(movimiento);
      }
      
    } catch (Exception e) {
      System.out.println("Error en hacerDeposito MovimientoService: " + e);
    }
    
    return respuesta;
  }

  @Override
  public String hacerTransferencia(Movimiento movimiento, long clabe) throws Exception {
    // TODO Auto-generated method stub
    
    String respuesta = "";
    
    boolean clabeExiste = false;
    
    Chequera chequera = new Chequera();
    
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    
    List<Chequera> chequeras = new ArrayList<>();

    try {
      
      chequeras = chequeraDao.listarChequeras();
      
      for (Chequera ch: chequeras) {
        if (ch.getClabe() == clabe) {
          clabeExiste = true;
          chequera = ch;
        }
      }
      
      if (clabeExiste) {
      
        if (chequera.getSaldo() < movimiento.getMonto()) {
          respuesta = "Saldo insuficiente!!";
          
        } else {
          
          String anio = movimiento.getFecha().toString().substring(0, 4);
          String mes = movimiento.getFecha().toString().substring(5, 7);
          String dia = movimiento.getFecha().toString().substring(8, 10);
          
          String fecha = anio + "-" + mes + "-" + dia;

          Date fechaMovimiento = Date.valueOf(fecha);
          
          if (fechaMovimiento.compareTo(chequera.getFechaApertura()) < 0) {
            respuesta = "Fecha de movimiento antes de la fecha de "
                + "creacion de la chequera. Retiro no realizado.";
          } else if (movimiento.getFecha().compareTo(timestamp) > 0) {
            respuesta = "Fecha de movimiento despues de fecha actual";
          } else {
            respuesta = movimientoDao.hacerTransferencia(movimiento, clabe);
          }
          
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
