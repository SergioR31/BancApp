/**
 * 
 */
package bancapp.services.implementation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bancapp.daos.interfaces.IchequeraDao;
import bancapp.daos.interfaces.IconsultaDao;
import bancapp.models.Chequera;
import bancapp.models.Estadisticas;
import bancapp.models.Movimiento;
import bancapp.services.interfaces.IConsultaService;

/**
 * @author SergioRamos
 *
 */
@Service
public class ConsultaService implements IConsultaService {
  
  @Autowired
  private IconsultaDao consultaDAO;
  
  @Autowired
  private IchequeraDao chequeraDAO;

  @Override
  public ArrayList<Movimiento> consultarDepositos(long idChequera, String periodo, int anio, int mes)
      throws Exception {
    // TODO Auto-generated method stub
    
    ArrayList<Movimiento> depositos = new ArrayList<>();
    
    try {
      
      depositos = consultaDAO.consultarDepositos(idChequera, periodo, anio, mes);
      
    } catch (Exception e) {
      System.out.println("Error en consultarDepositos de ConsultaServicio: " + e);
    }
    return depositos;
  }

  @Override
  public ArrayList<Movimiento> consultarRetiros(long idChequera, String periodo, int anio, int mes)
      throws Exception {
    // TODO Auto-generated method stub
    ArrayList<Movimiento> retiros = new ArrayList<>();
    
    try {
      
      retiros = consultaDAO.consultarRetiros(idChequera, periodo, anio, mes);
      
    } catch (Exception e) {
      System.out.println("Error en consultarRetiros de ConsultaServicio: " + e);
    }
    return retiros;
  }

  @Override
  public ArrayList<Movimiento> consultarTodos(long idChequera, String periodo, int anio, int mes) throws Exception {
    // TODO Auto-generated method stub
    
    ArrayList<Movimiento> movimientosTodos = new ArrayList<>();
    
    Chequera chequera = new Chequera();
    
    double saldoApertura = 0.0;
    
    int contador = 1;
    
    double saldo = 0.0;
    
    double saldoAnterior = 0.0;
    
    try {
      
      saldoApertura = chequeraDAO.consultarChequera(idChequera).getSaldoApertura();
      
      movimientosTodos = consultaDAO.consultarTodos(idChequera, periodo, anio, mes);
      
      for (Movimiento movimiento: movimientosTodos) {
        if (contador == 1) {
          if (movimiento.getIdTipo() == 1 || movimiento.getIdTipo() == 3) {
            saldo = saldoApertura - movimiento.getMonto();
            saldoAnterior = saldo;
            movimiento.setSaldo(saldo);
          }
          if (movimiento.getIdTipo() == 2 || movimiento.getIdTipo() == 4) {
            saldo = saldoApertura + movimiento.getMonto();
            saldoAnterior = saldo;
            movimiento.setSaldo(saldo);
          }
          contador = 2;
        } else {
          if (movimiento.getIdTipo() == 1 || movimiento.getIdTipo() == 3) {
            saldo = saldoAnterior - movimiento.getMonto();
            saldoAnterior = saldo;
            movimiento.setSaldo(saldo);
          }
        
          if (movimiento.getIdTipo() == 2 || movimiento.getIdTipo() == 4) {
            saldo = saldoAnterior + movimiento.getMonto();
            saldoAnterior = saldo;
            movimiento.setSaldo(saldo);
          }
        }
        
      }
      
      
    } catch (Exception e) {
      System.out.println("Error en consultarTodos de consultaServicio: " + e);
    }
    
    return movimientosTodos;
  }

  @Override
  public ArrayList<Movimiento> consultarDepositosFecha(long idChequera, String desde,
      String hasta) throws Exception {
    // TODO Auto-generated method stub
    ArrayList<Movimiento> depositosFecha = new ArrayList<>();
    
    String diaD = desde.substring(8,10);
    String mesD = desde.substring(5,7);
    String anioD = desde.substring(0,4);
    
    String fechaD = diaD + "/" + mesD + "/" + anioD;
    String horaD = "";
    if (desde.substring(11).length() == 5) {
      horaD = desde.substring(11) + ":00";
    } else {
      horaD = desde.substring(11);
    }
    
    String diaH = hasta.substring(8,10);
    String mesH = hasta.substring(5,7);
    String anioH = hasta.substring(0,4);
    
    String fechaH = diaH + "/" + mesH + "/" + anioH;
    String horaH = "";
    if (hasta.substring(11).length() == 5) {
      horaH = hasta.substring(11) + ":00";
    } else {
      horaH = hasta.substring(11);
    }
    
    String desdeS = fechaD + " " + horaD;
    String hastaS = fechaH + " " + horaH;
    
    try {
      
      depositosFecha = consultaDAO.consultarDepositosFecha(idChequera, desdeS, hastaS);
      
    } catch (Exception e) {
      System.out.println("Error en consultarDepositosFecha de ConsultaServicio: " + e);
    }
    return depositosFecha;
  }

  @Override
  public ArrayList<Movimiento> consultarRetirosFecha(long idChequera, String desde, String hasta)
      throws Exception {
    // TODO Auto-generated method stub
    ArrayList<Movimiento> retirosFecha = new ArrayList<>();
    
    String diaD = desde.substring(8,10);
    String mesD = desde.substring(5,7);
    String anioD = desde.substring(0,4);
    
    String fechaD = diaD + "/" + mesD + "/" + anioD;
    String horaD = "";
    if (desde.substring(11).length() == 5) {
      horaD = desde.substring(11) + ":00";
    } else {
      horaD = desde.substring(11);
    }
    
    String diaH = hasta.substring(8,10);
    String mesH = hasta.substring(5,7);
    String anioH = hasta.substring(0,4);
    
    String fechaH = diaH + "/" + mesH + "/" + anioH;
    String horaH = "";
    if (hasta.substring(11).length() == 5) {
      horaH = hasta.substring(11) + ":00";
    } else {
      horaH = hasta.substring(11);
    }
    
    String desdeS = fechaD + " " + horaD;
    String hastaS = fechaH + " " + horaH;
    
    try {
      
      retirosFecha = consultaDAO.consultarRetirosFecha(idChequera, desdeS, hastaS);
      
    } catch (Exception e) {
      System.out.println("Error en consultarRetirosFecha de ConsultaServicio: " + e);
    }
    return retirosFecha;
  }
  
  @Override
  public ArrayList<Movimiento> consultarTodosFecha(long idChequera, String desde, String hasta)
      throws Exception {
    // TODO Auto-generated method stub
    ArrayList<Movimiento> todosFecha = new ArrayList<>();
    
    String diaD = desde.substring(8,10);
    String mesD = desde.substring(5,7);
    String anioD = desde.substring(0,4);
    
    String fechaD = diaD + "/" + mesD + "/" + anioD;
    String horaD = "";
    if (desde.substring(11).length() == 5) {
      horaD = desde.substring(11) + ":00";
    } else {
      horaD = desde.substring(11);
    }
    
    String diaH = hasta.substring(8,10);
    String mesH = hasta.substring(5,7);
    String anioH = hasta.substring(0,4);
    
    String fechaH = diaH + "/" + mesH + "/" + anioH;
    String horaH = "";
    if (hasta.substring(11).length() == 5) {
      horaH = hasta.substring(11) + ":00";
    } else {
      horaH = hasta.substring(11);
    }
    
    String desdeS = fechaD + " " + horaD;
    String hastaS = fechaH + " " + horaH;
    
    try {
      
      todosFecha = consultaDAO.consultarTodosFecha(idChequera, desdeS, hastaS);
      
    } catch (Exception e) {
      System.out.println("Error en consultarTodosFecha de ConsultaServicio: " + e);
    }
    return todosFecha;
  }

  @Override
  public Estadisticas consultarEstadisticas() throws Exception {
    // TODO Auto-generated method stub
    
    Estadisticas estadisticas = new Estadisticas();
    
    try {
      
      estadisticas = consultaDAO.consultarEstadisticas();
      
    } catch (Exception e) {
      System.out.println("Error en consultarEstadisticas de ConsultaServicio: " + e);
    }
    
    return estadisticas;
  }

}
