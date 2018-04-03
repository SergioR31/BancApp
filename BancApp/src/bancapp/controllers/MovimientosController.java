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
      System.out.println("Error en retirosMostrar: " + e);
    }
    
    model.addAttribute("chequeras", chequeras);
      
    return "movimientos-retiro";
      
  }
  
  /**
 * Mapeo de metodo para mostrar la pagina de chequeras-agregar.  
 * @param model Define atributos para el JSP.
 * @return
 */
  @RequestMapping(value = "/retirar", method = RequestMethod.POST)
  public RedirectView movimientoRetirar(
      @RequestParam("idChequera") long idChequera,
      @RequestParam("monto") double monto,
      @RequestParam("concepto") String concepto,
      Model model,
      RedirectAttributes attributes) {
    
    Movimiento movimiento = new Movimiento();
    
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

}
