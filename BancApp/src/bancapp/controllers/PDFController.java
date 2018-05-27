/**
 * 
 */
package bancapp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bancapp.models.Banco;
import bancapp.models.Chequera;
import bancapp.models.Cliente;
import bancapp.models.EstadoCuenta;
import bancapp.models.Movimiento;
import bancapp.services.interfaces.IBancoService;
import bancapp.services.interfaces.IChequeraService;
import bancapp.services.interfaces.IClienteService;
import bancapp.services.interfaces.IConsultaService;


/**
 * @author SergioRamos
 *
 */
@Controller
public class PDFController{
  
  @Autowired
  private IChequeraService chequeraService;
  
  @Autowired
  private IConsultaService consultaService;
  
  @Autowired
  private IBancoService bancoService;
  
  @Autowired
  private IClienteService clienteService;
  
  @RequestMapping(value = "/estado-de-cuenta")
  public ModelAndView pdfView(HttpServletRequest request,
      HttpServletResponse response, Model model, 
      @RequestParam("idChequera") long idChequera,
      @RequestParam(value = "anioEstadoCuenta", defaultValue = "0") int anio,
      @RequestParam("mesEstadodeCuenta") int mes,
      RedirectAttributes attributes) throws Exception {
    
    String periodo = "mensual";
    Chequera chequera = new Chequera();
    Banco banco = new Banco();
    Cliente cliente = new Cliente();
    ArrayList<Movimiento> movimientos = new ArrayList<>();
    
    chequera = chequeraService.consultarChequera(idChequera);
    banco = bancoService.consultarBanco(chequera.getIdBanco());
    cliente= clienteService.consultarCliente(chequera.getIdCliente());
    movimientos = consultaService.consultarTodos(idChequera, periodo, anio, mes);
    
    //user data
    EstadoCuenta estadoCuenta = new EstadoCuenta();
    estadoCuenta.setBanco(banco);
    estadoCuenta.setChequera(chequera);
    estadoCuenta.setCliente(cliente);
    estadoCuenta.setMovimientos(movimientos);
    
    if (movimientos.size() != 0) {
      
      return new ModelAndView("PDFView","estadoCuenta",estadoCuenta);
      
    } else {
      
      ArrayList<Chequera> chequeras = new ArrayList<Chequera>();
      String mensaje = "No hay movimientos en el periodo seleccionado.";
      
      try {
        
        chequeras = (ArrayList<Chequera>) chequeraService.listarChequeras();
        
      } catch (Exception e) {
        System.out.println("Error en mostrarPaginaRetiro: " + e);
      }
      
      model.addAttribute("mensaje", mensaje);
      model.addAttribute("chequeras", chequeras);
      
      return new ModelAndView("consultas","modelo",model);
    }
    
    
  }
  
  

}
