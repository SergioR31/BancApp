package bancapp.controllers;

import java.sql.CallableStatement;
import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import bancapp.models.Estadisticas;
import bancapp.services.interfaces.IConsultaService;

/**
 * Controller Principal.
 * @author SergioRamos
 *
 */

@Controller
public class MainController {
  
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Autowired
  private IConsultaService consultaService;
  
  /**
 * Mapeo de metodo para pagina principal de la aplicacion.  
 * @param model Define atributos para el JSP.
 * @return
 */
  
  @RequestMapping(value = {"/Home", ""}, method = RequestMethod.GET)
  public String home(Model model) {
    
    Estadisticas estadisticas = new Estadisticas();
    
    try {
      
      estadisticas = consultaService.consultarEstadisticas();
      
    } catch (Exception e) {
      System.out.println("Error en home: " + e);
    }
    
    model.addAttribute("estadisticas", estadisticas);
    
    return "home";
    
  }
  
  /**
   * Mapeo de metodo para pagina principal de la aplicacion.  
   * @param model Define atributos para el JSP.
   * @return
   */
  @RequestMapping(value = "/BorrarDB")
  public RedirectView borrarDB(
      @RequestParam("password") String password,
      Model model,
      RedirectAttributes attributes) {
    
    String pass = "SergioR31";
    String mensaje = "";
      
    Connection conection = null;
      
    CallableStatement callableStatement = null;
    
    System.out.println("Password Ingresado: " +password);
      
    try {
      
      if (password.equals(pass)) {
      
        conection = jdbcTemplate.getDataSource().getConnection();
        
        callableStatement = conection.prepareCall("{CALL LIMPIAR_DB()}");
        
        callableStatement.executeUpdate();
        
        mensaje = "Base de Datos limpiada/reiniciada con exito";
        
        System.out.println("Password Correcto");
        
      } else {
        
        mensaje = "Contraseña Incorrecta.";
        
        System.out.println("Password Incorrecto");
        
      }
      
    } catch (Exception e) {
      
      System.out.println("Error en borrarDB: " + e);
      
      mensaje = "Error al limpiar/reiniciar Base de Datos!!!";
      
    }
    
    attributes.addFlashAttribute("mensaje", mensaje);
    
    return new RedirectView("Home");
    
  }
  
}
