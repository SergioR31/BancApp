package bancapp.controllers;

import bancapp.models.Banco;
import bancapp.services.interfaces.IBancoService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Bancos Controller.
 * @author SergioRamos
 *
 */

@Controller
public class BancosController {
  
  @Autowired
  private IBancoService bancoService;

  private static final String MENSAJE = "mensaje";
  
  /**
   * Mapeo de metodo para mostrar la pagina de listado-bancos con los bancos de la base de datos.  
   * @param model Define atributos para el JSP.
   * @return
   */
  @RequestMapping(value = "/Bancos/listado")
    public String bancosMostrar(Model model) {
      
    ArrayList<Banco> bancos = new ArrayList<Banco>();
    
    try {
      
      bancos = (ArrayList<Banco>) bancoService.listarBancos();
      
    } catch (Exception e) {
      System.out.println("Error en bancosMostrar: " + e);
    }
    
    model.addAttribute("bancos", bancos);
    
    model.addAttribute(MENSAJE, (String) model.asMap().get(MENSAJE));
      
    return "bancos-listado";
      
  }
  
  /**
 * Mapeo de metodo para mostrar la pagina de agregar-bancos.  
 * @param model Define atributos para el JSP.
 * @return
 */
  @RequestMapping(value = "/Bancos/agregar", method = RequestMethod.GET)
  public String bancosInsertar(Model model) {
    
    return "bancos-agregar";
    
  }
  
  /**
   * Mapeo de metodo para mostrar la pagina para modificar un banco.  
   * @param model Define atributos para el JSP.
   * @return
   */
  @RequestMapping(value = "/Bancos/modificar")
    public String bancosModificar(
        @RequestParam("idBanco") int idBanco,
        Model model) {
    Banco banco = new Banco();
    
    try {
      banco = bancoService.consultarBanco(idBanco);
    } catch (Exception e) {
      System.out.println("Error en bancosModificar: " + e);
    }
    
    model.addAttribute("banco", banco);
    
    return "bancos-modificar";
    
  }
  
  
  
  /**
   * Mapeo de metodo para insertar un Banco en la Base de Datos.
   * @param nombreEntidad Define el nombre de la Enttidad bancaria.
   * @param nombreSucursal Define el nombre de la Sucursal.
   * @param direccion Define la Direccion de la Sucursal.
   * @param attributes Para enviar atributos a otro controlador.
   * @return
   */
  @RequestMapping(value = "/insertarBanco", method = RequestMethod.POST)
  public RedirectView insertarBanco(
      @RequestParam("nombre_entidad") String nombreEntidad,
      @RequestParam("nombre_sucursal") String nombreSucursal,
      @RequestParam("direccion") String direccion,
      RedirectAttributes attributes) {
    
    Banco banco = new Banco();
    
    banco.setEntidad(nombreEntidad);
    banco.setSucursal(nombreSucursal);
    banco.setDireccion(direccion);
    
    
    String mensaje = "";
    
    try {
      
      
      mensaje = bancoService.insertarBanco(banco);
      
    } catch (Exception e) { 
      System.out.println("Error en insertarBanco en BancosController: " + e);
    }
    
    attributes.addFlashAttribute(MENSAJE, mensaje);
    
    return new RedirectView("Bancos/listado");
    
  }
  
  /**
   * Mapeo de metodo para modificar un Banco en la Base de Datos.
   * @param nombreEntidad Define el nombre de la Enttidad bancaria.
   * @param nombreSucursal Define el nombre de la Sucursal.
   * @param direccion Define la Direccion de la Sucursal.
   * @param attributes Para enviar atributos a otro controlador.
   * @return
   */
  @RequestMapping(value = "/modificarBanco", method = RequestMethod.POST)
  public RedirectView modificarBanco(
      @RequestParam("idBanco") int idBanco,
      @RequestParam("nombre_entidad") String nombreEntidad,
      @RequestParam("nombre_sucursal") String nombreSucursal,
      @RequestParam("direccion") String direccion,
      RedirectAttributes attributes) {
    
    Banco banco = new Banco();
    
    banco.setIdBanco(idBanco);
    banco.setEntidad(nombreEntidad);
    banco.setSucursal(nombreSucursal);
    banco.setDireccion(direccion);
    
    
    String mensaje = "";
    
    try {
      
      
      mensaje = bancoService.modificarBanco(banco);
      
    } catch (Exception e) { 
      System.out.println("Error en modificarBanco en BancosController: " + e);
    }
    
    attributes.addFlashAttribute(MENSAJE, mensaje);
    
    return new RedirectView("Bancos/listado");
    
  }
  
  /**
   * Mapeo de metodo para "eliminar" un Banco en la Base de Datos.
   * @param attributes Para enviar atributos a otro controlador.
   * @return
   */
  @RequestMapping(value = "/eliminarBanco", method = RequestMethod.POST)
  public RedirectView eliminarBanco(
      @RequestParam("idBanco") int idBanco,
      RedirectAttributes attributes) {
    
    String mensaje = "";
    
    try {
      
      mensaje = bancoService.eliminarBanco(idBanco);
      
    } catch (Exception e) { 
      System.out.println("Error en eliminarBanco en BancosController: " + e);
    }
    
    attributes.addFlashAttribute(MENSAJE, mensaje);
    
    return new RedirectView("Bancos/listado");
    
  }

}
