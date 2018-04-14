/**
 * 
 */

package bancapp.controllers;

import java.sql.Timestamp;
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
  @RequestMapping(value = "/ConsultaMovimientos")
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
  
  @RequestMapping(value = "/ConsultaMovimientos/depositos")
  public String consultarDepositos(
      @RequestParam("idChequera") long idChequera,
      @RequestParam("periodo") String periodo,
      @RequestParam(value = "anioMovimientos", defaultValue = "0") int anio,
      @RequestParam("mesMovimientos") int mes,
      Model model,
      RedirectAttributes attributes) {
    
    ArrayList<Movimiento> depositos = new ArrayList<>();
    Chequera chequera = new Chequera();
    
    double totalDepositos = 0;
    
    String stringMes = "";
    
    
    try {
      
      chequera = chequeraService.consultarChequera(idChequera);
      
      depositos = consultaService.consultarDepositos(idChequera, periodo, anio, mes); 

    } catch (Exception e) {
      System.out.println("Error en consultarDepositos: " + e);
    }
    
    for (Movimiento deposito: depositos) {
      totalDepositos += deposito.getMonto();
      
    }
    
    if (periodo.equals("anual")) {
      model.addAttribute("anio", anio);
    } else {
      model.addAttribute("anio", anio);
      
      switch (mes) {
        case 1:  stringMes = "Enero";
      break;
        case 2:  stringMes = "Febrero";
      break;
        case 3:  stringMes = "Marzo";
      break;
        case 4:  stringMes = "Abril";
      break;
        case 5:  stringMes = "Mayo";
      break;
        case 6:  stringMes = "Junio";
      break;
        case 7:  stringMes = "Julio";
      break;
        case 8:  stringMes = "Agosto";
      break;
        case 9:  stringMes = "Septiembre";
      break;
        case 10: stringMes = "Octubre";
      break;
        case 11: stringMes = "Noviembre";
      break;
        case 12: stringMes = "Decimbre";
      break;
        default: stringMes = "Invalid month";
      break;
      }
      model.addAttribute("mes", stringMes);
    }
    model.addAttribute("chequera", chequera);
    model.addAttribute("depositos", depositos);
    model.addAttribute("totalDepositos", totalDepositos);
    model.addAttribute("periodo", periodo);
    
    return "consulta-depositos";
  }
  
  @RequestMapping(value = "/ConsultaMovimientos/depositos-rango-fecha")
  public String consultarDepositosPorFecha(
      @RequestParam("idChequera") long idChequera,
      @RequestParam("desde") String desde,
      @RequestParam("hasta") String hasta,
      Model model,
      RedirectAttributes attributes) {
    
    ArrayList<Movimiento> depositosFecha = new ArrayList<>();
    Chequera chequera = new Chequera();
    double totalDepositos = 0;
    
    try {
      
      chequera = chequeraService.consultarChequera(idChequera);
      
      depositosFecha = consultaService.consultarDepositosFecha(idChequera, desde, hasta); 

    } catch (Exception e) {
      System.out.println("Error en consultarDepositosPoFecha: " + e);
    }
    
    for (Movimiento deposito: depositosFecha) {
      totalDepositos += deposito.getMonto();
      
    }
    
    model.addAttribute("desde", desde);
    model.addAttribute("hasta", hasta);
    model.addAttribute("chequera", chequera);
    model.addAttribute("depositos", depositosFecha);
    model.addAttribute("totalDepositos", totalDepositos);
    
    return "consulta-depositos-fechas";
  }
  
  @RequestMapping(value = "/ConsultaMovimientos/retiros")
  public String consultarRetiros(
      @RequestParam("idChequera") long idChequera,
      @RequestParam("periodo") String periodo,
      @RequestParam(value = "anioMovimientos", defaultValue = "0") int anio,
      @RequestParam("mesMovimientos") int mes,
      Model model,
      RedirectAttributes attributes) {
    
    ArrayList<Movimiento> retiros = new ArrayList<>();
    Chequera chequera = new Chequera();
    
    double totalRetiros = 0;
    
    String stringMes = "";
    
    
    try {
      
      chequera = chequeraService.consultarChequera(idChequera);
      
      retiros = consultaService.consultarRetiros(idChequera, periodo, anio, mes); 

    } catch (Exception e) {
      System.out.println("Error en consultarRetiros: " + e);
    }
    
    for (Movimiento retiro: retiros) {
      totalRetiros += retiro.getMonto();
      
    }
    
    if (periodo.equals("anual")) {
      model.addAttribute("anio", anio);
    } else {
      model.addAttribute("anio", anio);
      
      switch (mes) {
        case 1:  stringMes = "Enero";
      break;
        case 2:  stringMes = "Febrero";
      break;
        case 3:  stringMes = "Marzo";
      break;
        case 4:  stringMes = "Abril";
      break;
        case 5:  stringMes = "Mayo";
      break;
        case 6:  stringMes = "Junio";
      break;
        case 7:  stringMes = "Julio";
      break;
        case 8:  stringMes = "Agosto";
      break;
        case 9:  stringMes = "Septiembre";
      break;
        case 10: stringMes = "Octubre";
      break;
        case 11: stringMes = "Noviembre";
      break;
        case 12: stringMes = "Decimbre";
      break;
        default: stringMes = "Invalid month";
      break;
      }
      model.addAttribute("mes", stringMes);
    }
    model.addAttribute("chequera", chequera);
    model.addAttribute("retiros", retiros);
    model.addAttribute("totalRetiros", totalRetiros);
    model.addAttribute("periodo", periodo);
    
    return "consulta-retiros";
  }
  
  @RequestMapping(value = "/ConsultaMovimientos/retiros-rango-fecha")
  public String consultarRetirosPorFecha(
      @RequestParam("idChequera") long idChequera,
      @RequestParam("desde") String desde,
      @RequestParam("hasta") String hasta,
      Model model,
      RedirectAttributes attributes) {
    
    ArrayList<Movimiento> retirosFecha = new ArrayList<>();
    Chequera chequera = new Chequera();
    double totalRetiros = 0;
    
    try {
      
      chequera = chequeraService.consultarChequera(idChequera);
      
      retirosFecha = consultaService.consultarRetirosFecha(idChequera, desde, hasta);

    } catch (Exception e) {
      System.out.println("Error en consultarRetirosPorFecha: " + e);
    }
    
    for (Movimiento retiro: retirosFecha) {
      totalRetiros += retiro.getMonto();
      
    }
    
    model.addAttribute("desde", desde);
    model.addAttribute("hasta", hasta);
    model.addAttribute("chequera", chequera);
    model.addAttribute("retiros", retirosFecha);
    model.addAttribute("totalRetiros", totalRetiros);
    
    return "consulta-retiros-fechas";
  }
  
  @RequestMapping(value = "/ConsultaMovimientos/todos")
  public String consultarTodos(
      @RequestParam("idChequera") long idChequera,
      @RequestParam("periodo") String periodo,
      @RequestParam(value = "anioMovimientos", defaultValue = "0") int anio,
      @RequestParam("mesMovimientos") int mes,
      Model model,
      RedirectAttributes attributes) {
    
    ArrayList<Movimiento> movimientosTodos = new ArrayList<>();
    Chequera chequera = new Chequera();
    
    double totalRetiros = 0;
    double totalDepositos = 0;
    
    String stringMes = "";
    
    
    try {
      
      chequera = chequeraService.consultarChequera(idChequera);
      
      movimientosTodos = consultaService.consultarTodos(idChequera, periodo, anio, mes); 

    } catch (Exception e) {
      System.out.println("Error en consultarTodos: " + e);
    }
    
    for (Movimiento movimiento: movimientosTodos) {
      if (movimiento.getIdTipo() == 1 || movimiento.getIdTipo() == 3) {
        totalRetiros += movimiento.getMonto();
      } else {
        totalDepositos += movimiento.getMonto();
      }
    }
    
    if (periodo.equals("anual")) {
      model.addAttribute("anio", anio);
    } else {
      model.addAttribute("anio", anio);
      
      switch (mes) {
        case 1:  stringMes = "Enero";
      break;
        case 2:  stringMes = "Febrero";
      break;
        case 3:  stringMes = "Marzo";
      break;
        case 4:  stringMes = "Abril";
      break;
        case 5:  stringMes = "Mayo";
      break;
        case 6:  stringMes = "Junio";
      break;
        case 7:  stringMes = "Julio";
      break;
        case 8:  stringMes = "Agosto";
      break;
        case 9:  stringMes = "Septiembre";
      break;
        case 10: stringMes = "Octubre";
      break;
        case 11: stringMes = "Noviembre";
      break;
        case 12: stringMes = "Decimbre";
      break;
        default: stringMes = "Invalid month";
      break;
      }
      model.addAttribute("mes", stringMes);
    }
    model.addAttribute("chequera", chequera);
    model.addAttribute("movimientos", movimientosTodos);
    model.addAttribute("totalRetiros", totalRetiros);
    model.addAttribute("totalDepositos", totalDepositos);
    model.addAttribute("periodo", periodo);
    
    return "consulta-todos";
  }
  
  @RequestMapping(value = "/ConsultaMovimientos/todos-rango-fecha")
  public String consultarTodosPorFecha(
      @RequestParam("idChequera") long idChequera,
      @RequestParam("desde") String desde,
      @RequestParam("hasta") String hasta,
      Model model,
      RedirectAttributes attributes) {
    
    ArrayList<Movimiento> movimientosTodosFecha = new ArrayList<>();
    Chequera chequera = new Chequera();
    double totalDepositos = 0;
    double totalRetiros = 0;
    
    try {
      
      chequera = chequeraService.consultarChequera(idChequera);
      
      movimientosTodosFecha = consultaService.consultarTodosFecha(idChequera, desde, hasta);

    } catch (Exception e) {
      System.out.println("Error en consultarTodosPorFecha: " + e);
    }
    
    for (Movimiento movimiento: movimientosTodosFecha) {
      if (movimiento.getIdTipo() == 1 || movimiento.getIdTipo() == 3) {
        totalRetiros += movimiento.getMonto();
      } else {
        totalDepositos += movimiento.getMonto();
      }
    }
    
    model.addAttribute("desde", desde);
    model.addAttribute("hasta", hasta);
    model.addAttribute("chequera", chequera);
    model.addAttribute("movimientos", movimientosTodosFecha);
    model.addAttribute("totalDepositos", totalDepositos);
    model.addAttribute("totalRetiros", totalRetiros);
    
    return "consulta-todos-fechas";
  }
  
  @RequestMapping(value = "/Consultar/estado-de-cuenta")
  public String consultarEstadoDeCuenta(
      @RequestParam("idChequera") long idChequera,
      @RequestParam(value = "anioEstadoCuenta") int anio,
      @RequestParam("mesEstadodeCuenta") int mes,
      Model model,
      RedirectAttributes attributes) {
    
    ArrayList<Movimiento> movimientosTodos = new ArrayList<>();
    Chequera chequera = new Chequera();
    
    double totalRetiros = 0;
    double totalDepositos = 0;
    
    String stringMes = "";
    String periodo = "mensual";
    
    try {
      
      chequera = chequeraService.consultarChequera(idChequera);
      
      movimientosTodos = consultaService.consultarTodos(idChequera, periodo, anio, mes); 

    } catch (Exception e) {
      System.out.println("Error en consultarEstadoDeCuenta: " + e);
    }
    
    for (Movimiento movimiento: movimientosTodos) {
      if (movimiento.getIdTipo() == 1 || movimiento.getIdTipo() == 3) {
        totalRetiros += movimiento.getMonto();
      } else {
        totalDepositos += movimiento.getMonto();
      }
    }
    
    if (periodo.equals("anual")) {
      model.addAttribute("anio", anio);
    } else {
      model.addAttribute("anio", anio);
      
      switch (mes) {
        case 1:  stringMes = "Enero";
      break;
        case 2:  stringMes = "Febrero";
      break;
        case 3:  stringMes = "Marzo";
      break;
        case 4:  stringMes = "Abril";
      break;
        case 5:  stringMes = "Mayo";
      break;
        case 6:  stringMes = "Junio";
      break;
        case 7:  stringMes = "Julio";
      break;
        case 8:  stringMes = "Agosto";
      break;
        case 9:  stringMes = "Septiembre";
      break;
        case 10: stringMes = "Octubre";
      break;
        case 11: stringMes = "Noviembre";
      break;
        case 12: stringMes = "Decimbre";
      break;
        default: stringMes = "Invalid month";
      break;
      }
      model.addAttribute("mes", stringMes);
    }
    model.addAttribute("chequera", chequera);
    model.addAttribute("movimientos", movimientosTodos);
    model.addAttribute("totalRetiros", totalRetiros);
    model.addAttribute("totalDepositos", totalDepositos);
    model.addAttribute("periodo", periodo);
    
    return "consulta-estado-cuenta";
  }

}
