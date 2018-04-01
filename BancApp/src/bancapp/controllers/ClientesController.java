package bancapp.controllers;

import bancapp.models.Cliente;
import bancapp.services.interfaces.IClienteService;

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
 * Clientes Controller.
 * @author SergioRamos
 *
 */

@Controller
public class ClientesController {
  
  @Autowired
  private IClienteService clienteService;

  private static final String MENSAJE = "mensaje";
  
  /**
   * Mapeo de metodo para mostrar la pagina de listado-clientes con los clientes de la base de datos.  
   * @param model Define atributos para el JSP.
   * @return
   */
  @RequestMapping(value = "/Clientes/listado")
    public String clientesMostrar(Model model) {
      
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    
    try {
      
      clientes = (ArrayList<Cliente>) clienteService.listarClientes();
      
    } catch (Exception e) {
      System.out.println("Error en clientesMostrar: " + e);
    }
    
    model.addAttribute("clientes", clientes);
    
    model.addAttribute(MENSAJE, (String) model.asMap().get(MENSAJE));
      
    return "clientes-listado";
      
  }
  
  /**
 * Mapeo de metodo para mostrar la pagina de agregar-clientes.  
 * @param model Define atributos para el JSP.
 * @return
 */
  @RequestMapping(value = "/Clientes/agregar", method = RequestMethod.GET)
  public String clientesInsertar(Model model) {
    
    return "clientes-agregar";
    
  }
  
  /**
   * Mapeo de metodo para mostrar la pagina para modificar un cliente.  
   * @param model Define atributos para el JSP.
   * @return
   */
  @RequestMapping(value = "/Clientes/modificar")
    public String clientesModificar(
        @RequestParam("idCliente") int idCliente,
        Model model) {
    Cliente cliente = new Cliente();
    
    try {
      cliente = clienteService.consultarCliente(idCliente);
    } catch (Exception e) {
      System.out.println("Error en clientesModificar: " + e);
    }
    
    model.addAttribute("cliente", cliente);
    
    return "clientes-modificar";
    
  }
  
  
  
  /**
   * Mapeo de metodo para insertar un Cliente en la Base de Datos.
   * @param nombreEntidad Define el nombre de la Enttidad bancaria.
   * @param nombreSucursal Define el nombre de la Sucursal.
   * @param direccion Define la Direccion de la Sucursal.
   * @param attributes Para enviar atributos a otro controlador.
   * @return
   */
  @RequestMapping(value = "/insertarCliente", method = RequestMethod.POST)
  public RedirectView insertarCliente(
      @RequestParam("nombre_entidad") String nombreEntidad,
      @RequestParam("nombre_sucursal") String nombreSucursal,
      @RequestParam("direccion") String direccion,
      RedirectAttributes attributes) {
    
    Cliente cliente = new Cliente();
    
//    cliente.setEntidad(nombreEntidad);
//    cliente.setSucursal(nombreSucursal);
//    cliente.setDireccion(direccion);
    
    
    String mensaje = "";
    
    try {
      
      
      mensaje = clienteService.insertarCliente(cliente);
      
    } catch (Exception e) { 
      System.out.println("Error en insertarCliente en ClientesController: " + e);
    }
    
    attributes.addFlashAttribute(MENSAJE, mensaje);
    
    return new RedirectView("Clientes/listado");
    
  }
  
  /**
   * Mapeo de metodo para modificar un Cliente en la Base de Datos.
   * @param nombreEntidad Define el nombre de la Enttidad bancaria.
   * @param nombreSucursal Define el nombre de la Sucursal.
   * @param direccion Define la Direccion de la Sucursal.
   * @param attributes Para enviar atributos a otro controlador.
   * @return
   */
  @RequestMapping(value = "/modificarCliente", method = RequestMethod.POST)
  public RedirectView modificarCliente(
      @RequestParam("idCliente") int idCliente,
      @RequestParam("nombre_entidad") String nombreEntidad,
      @RequestParam("nombre_sucursal") String nombreSucursal,
      @RequestParam("direccion") String direccion,
      RedirectAttributes attributes) {
    
    Cliente cliente = new Cliente();
    
//    cliente.setIdCliente(idCliente);
//    cliente.setEntidad(nombreEntidad);
//    cliente.setSucursal(nombreSucursal);
//    cliente.setDireccion(direccion);
    
    
    String mensaje = "";
    
    try {
      
      
      mensaje = clienteService.modificarCliente(cliente);
      
    } catch (Exception e) { 
      System.out.println("Error en modificarCliente en ClientesController: " + e);
    }
    
    attributes.addFlashAttribute(MENSAJE, mensaje);
    
    return new RedirectView("Clientes/listado");
    
  }
  
  /**
   * Mapeo de metodo para "eliminar" un Cliente en la Base de Datos.
   * @param attributes Para enviar atributos a otro controlador.
   * @return
   */
  @RequestMapping(value = "/eliminarCliente", method = RequestMethod.POST)
  public RedirectView eliminarCliente(
      @RequestParam("idCliente") int idCliente,
      RedirectAttributes attributes) {
    
    String mensaje = "";
    
    try {
      
      mensaje = clienteService.eliminarCliente(idCliente);
      
    } catch (Exception e) { 
      System.out.println("Error en eliminarCliente en ClientesController: " + e);
    }
    
    attributes.addFlashAttribute(MENSAJE, mensaje);
    
    return new RedirectView("Clientes/listado");
    
  }

}
