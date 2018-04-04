/**
 * 
 */

package bancapp.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bancapp.models.Chequera;
import bancapp.models.Movimiento;
import bancapp.services.interfaces.IChequeraService;
import bancapp.services.interfaces.IConsultaService;
import bancapp.services.interfaces.IMovimientoService;

/**
 * Controller de Consultas.
 * @author SergioRamos
 *
 */

@Controller
public class ConsultasController {
  
  @Autowired
  private IChequeraService chequeraService;

  @Autowired
  private IMovimientoService movimientoService;
  
  @Autowired
  private IConsultaService consultaService;
  
private static final String MENSAJE = "mensaje";
  
  /**
   * Mapeo de metodo para mostrar pagina hacer movimiento.
   * @param model Define atributos para el JSP.
   * @return
   */
  @RequestMapping(value = "/Consultas")
  public String mostrarPaginaConsulta(Model model) {
    
    ArrayList<Chequera> chequeras = new ArrayList<Chequera>();
    
    try {
      
      chequeras = (ArrayList<Chequera>) chequeraService.listarChequeras();
      
    } catch (Exception e) {
      System.out.println("Error en mostrarPaginaRetiro: " + e);
    }
    
    model.addAttribute("chequeras", chequeras);
    
    return "consultas";
  }
  
  @RequestMapping(value = "/Consultar/depositos")
  public String consultarDepositos(
      @RequestParam("idChequera") long idChequera,
      @RequestParam("periodo") String periodo,
      @RequestParam("anioMovimientos") int anio,
      @RequestParam("mesMovimientos") int mes,
      Model model,
      RedirectAttributes attributes) {
    
    ArrayList<Movimiento> depositos = new ArrayList<>();
    Chequera chequera = new Chequera();
    
    double totalDepositos = 0;
    
    
    try {
      
      chequera = chequeraService.consultarChequera(idChequera);
      
      depositos = consultaService.consultarDepositos(idChequera, periodo, anio, mes); 

    } catch (Exception e) {
      System.out.println("Error en consultarDepositos: " + e);
    }
    
    for (Movimiento deposito: depositos) {
      totalDepositos += deposito.getMonto();
      
    }
    
    model.addAttribute("chequera", chequera);
    model.addAttribute("depositos", depositos);
    model.addAttribute("totalDepositos", totalDepositos);
    
    System.out.println("totalDepositos: " + totalDepositos);
    
    return "consulta-depositos";
  }
  

}
