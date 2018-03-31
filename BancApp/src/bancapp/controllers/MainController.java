package bancapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller Principal.
 * @author SergioRamos
 *
 */

@Controller
public class MainController {
  
  /**
 * Mapeo de metodo para pagina principal de la aplicacion.  
 * @param model Define atributos para el JSP.
 * @return
 */
  @RequestMapping(value = "/Home", method = RequestMethod.GET)
  public String bancosInsertar(Model model) {
    
    return "home";
    
  }
  
}
