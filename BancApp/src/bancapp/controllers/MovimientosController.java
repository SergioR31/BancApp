package bancapp.controllers;

import bancapp.models.Banco;
import bancapp.models.Chequera;
import bancapp.models.Cliente;
import bancapp.models.Movimiento;
import bancapp.services.interfaces.IBancoService;
import bancapp.services.interfaces.IChequeraService;
import bancapp.services.interfaces.IClienteService;
import bancapp.services.interfaces.IMovimientoService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Movimientos Controller.
 * @author SergioRamos
 *
 */

@Controller
public class MovimientosController {
  
  @Autowired
  private IChequeraService chequeraService;
  
  @Autowired
  private IMovimientoService movimientoService;

  private static final String MENSAJE = "mensaje";
  
  /**
   * Mapeo de metodo para mostrar pagina hacer movimiento.
   * @param model Define atributos para el JSP.
   * @return
   */
  @RequestMapping(value = "/Movimientos/retiro")
    public String mostrarPaginaRetiro(Model model) {
      
    ArrayList<Chequera> chequeras = new ArrayList<Chequera>();
    
    try {
      
      chequeras = (ArrayList<Chequera>) chequeraService.listarChequeras();
      
    } catch (Exception e) {
      System.out.println("Error en mostrarPaginaRetiro: " + e);
    }
    
    model.addAttribute("chequeras", chequeras);
      
    return "movimientos-retiro";
      
  }
  
  /**
   * Mapeo de metodo para mostrar pagina hacer movimiento.
   * @param model Define atributos para el JSP.
   * @return
   */
  @RequestMapping(value = "/Movimientos/deposito")
    public String mostrarPaginaDeposito(Model model) {
      
    ArrayList<Chequera> chequeras = new ArrayList<Chequera>();
    
    try {
      
      chequeras = (ArrayList<Chequera>) chequeraService.listarChequeras();
      
    } catch (Exception e) {
      System.out.println("Error en mostrarPaginaDeposito: " + e);
    }
    
    model.addAttribute("chequeras", chequeras);
      
    return "movimientos-deposito";
      
  }
  
  /**
   * Mapeo de metodo para mostrar pagina hacer transferencia.
   * @param model Define atributos para el JSP.
   * @return
   */
  @RequestMapping(value = "/Movimientos/transferencia")
    public String mostrarPaginaTransferencia(Model model) {
      
    ArrayList<Chequera> chequeras = new ArrayList<Chequera>();
    
    try {
      
      chequeras = (ArrayList<Chequera>) chequeraService.listarChequeras();
      
    } catch (Exception e) {
      System.out.println("Error en mostrarPaginaTransferencia: " + e);
    }
    
    model.addAttribute("chequeras", chequeras);
      
    return "movimientos-transferencia";
      
  }
  
  /**
 * Mapeo de metodo para hacer retiro en chequera.  
 * @param model Define atributos para el JSP.
 * @return
 */
  @RequestMapping(value = "/retirar", method = RequestMethod.POST)
  public RedirectView movimientoRetirar(
      @RequestParam("fechaRetiro") String fechaRetiroS,
      @RequestParam("idChequera") long idChequera,
      @RequestParam("monto") double monto,
      @RequestParam("concepto") String concepto,
      Model model,
      RedirectAttributes attributes) {
    
    String fecha = fechaRetiroS.substring(0, 10);
    String hora = "";
    if (fechaRetiroS.substring(11).length() == 5) {
      hora = fechaRetiroS.substring(11) + ":00";
    } else {
      hora = fechaRetiroS.substring(11);
    }
    
    System.out.println("String Timestamp: " + fecha + " " + hora);
    Timestamp fechaRetiro = Timestamp.valueOf(fecha + " " + hora);
    
    Movimiento movimiento = new Movimiento();
    
    movimiento.setFecha(fechaRetiro);
    movimiento.setIdChequera(idChequera);
    movimiento.setMonto(monto);
    movimiento.setConcepto(concepto);
    
    String mensaje = "";
    
    try {
      
      mensaje = movimientoService.hacerRetiro(movimiento);
      
    } catch (Exception e) {
      System.out.println("Error al hacer retiro: " + e);
    }
    
    attributes.addFlashAttribute(MENSAJE, mensaje);
    
    return new RedirectView("Chequeras/listado");
    
  }
  
  /**
   * Mapeo de metodo para hacer deposito en chequera.  
   * @param model Define atributos para el JSP.
   * @return
   */
  @RequestMapping(value = "/depositar", method = RequestMethod.POST)
  public RedirectView movimientoDepositar(
      @RequestParam("fechaDeposito") String fechaDepositoS,
      @RequestParam("idChequera") long idChequera,
      @RequestParam("monto") double monto,
      @RequestParam("concepto") String concepto,
      Model model,
      RedirectAttributes attributes) {

    String fecha = fechaDepositoS.substring(0, 10);
    String hora = "";
    if (fechaDepositoS.substring(11).length() == 5) {
      hora = fechaDepositoS.substring(11) + ":00";
    } else {
      hora = fechaDepositoS.substring(11);
    }
    Timestamp fechaDeposito = Timestamp.valueOf(fecha + " " + hora);
      
    Movimiento movimiento = new Movimiento();
      
    movimiento.setFecha(fechaDeposito);
    movimiento.setIdChequera(idChequera);
    movimiento.setMonto(monto);
    movimiento.setConcepto(concepto);
      
    String mensaje = "";
      
    try {
        
      mensaje = movimientoService.hacerDeposito(movimiento);
        
    } catch (Exception e) {
      System.out.println("Error al hacer deposito: " + e);
    }
      
    attributes.addFlashAttribute(MENSAJE, mensaje);
      
    return new RedirectView("Chequeras/listado");
      
  }
  
  /**
   * Mapeo de metodo para hacer una trasferencia a chequera.  
   * @param model Define atributos para el JSP.
   * @return
   */
  @RequestMapping(value = "/transferir", method = RequestMethod.POST)
  public RedirectView movimientoTransferir(
      @RequestParam("fechaTransferencia") String fechaTransferenciaS,
      @RequestParam("idChequera") long idChequera,
      @RequestParam("monto") double monto,
      @RequestParam("concepto") String concepto,
      @RequestParam("clabe") long clabe,
      Model model,
      RedirectAttributes attributes) {
    
    String fecha = fechaTransferenciaS.substring(0, 10);
    String hora = "";
    if (fechaTransferenciaS.substring(11).length() == 5) {
      hora = fechaTransferenciaS.substring(11) + ":00";
    }else {
      hora = fechaTransferenciaS.substring(11);
    }
    Timestamp fechaTransferencia = Timestamp.valueOf(fecha + " " + hora);
      
    Movimiento movimiento = new Movimiento();
      
    movimiento.setFecha(fechaTransferencia);
    movimiento.setIdChequera(idChequera);
    movimiento.setMonto(monto);
    movimiento.setConcepto(concepto);
      
    String mensaje = "";
      
    try {
        
      mensaje = movimientoService.hacerTransferencia(movimiento, clabe);
        
    } catch (Exception e) {
      System.out.println("Error al hacer transferencia: " + e);
    }
      
    attributes.addFlashAttribute(MENSAJE, mensaje);
      
    return new RedirectView("Chequeras/listado");
      
  }

}
