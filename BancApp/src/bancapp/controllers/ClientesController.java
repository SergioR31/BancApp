package bancapp.controllers;

import bancapp.models.Cliente;
import bancapp.services.interfaces.IClienteService;

import java.sql.Date;
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
   * Mapeo de metodo para mostrar pagina listado-clientes con los clientes de la base de datos.  
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
    
//    model.addAttribute(MENSAJE, (String) model.asMap().get(MENSAJE));
      
    return "clientes-listado";
      
  }
  
  /**
 * Mapeo de metodo para mostrar la pagina de clientes-agregar.  
 * @param model Define atributos para el JSP.
 * @return
 */
  @RequestMapping(value = "/Clientes/agregar", method = RequestMethod.GET)
  public String clientesInsertar(Model model) {
    
    return "clientes-agregar";
    
  }
  
  /**
   * Mapeo de metodo para mostrar la pagina para modificar un cliente.  
   * @param idCliente Define el id del CLiente
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
   * @param nombre define Nombre del Cliente
   * @param apellidoPaterno define apellidoPaterno del Cliente
   * @param apellidoMaterno define Nombre del Cliente
   * @param direccion define direccion del Cliente
   * @param estado define estado del Cliente
   * @param codigoPostal define codigoPostal del Cliente
   * @param telefono define telefono del Cliente
   * @param correo define correo del Cliente
   * @param fechaNacimiento define fechaNacimiento del Cliente
   * @param rfc define rfc del Cliente
   * @param attributes Para enviar atributos a otro controlador.
   */
  @RequestMapping(value = "/insertarCliente", method = RequestMethod.POST)
  public RedirectView insertarCliente(
      @RequestParam("nombre") String nombre,
      @RequestParam("apellidoPaterno") String apellidoPaterno,
      @RequestParam("apellidoMaterno") String apellidoMaterno,
      @RequestParam("direccion") String direccion,
      @RequestParam("estado") String estado,
      @RequestParam("codigoPostal") int codigoPostal,
      @RequestParam("telefono") long telefono,
      @RequestParam("correo") String correo,
      @RequestParam("fechaNacimiento") Date fechaNacimiento,
      @RequestParam("rfc") String rfc,
      RedirectAttributes attributes) {
    
    Cliente cliente = new Cliente();
    
    cliente.setNombre(nombre);
    cliente.setApellidoPaterno(apellidoPaterno);
    cliente.setApellidoMaterno(apellidoMaterno);
    cliente.setDireccion(direccion);
    cliente.setEstado(estado);
    cliente.setCodigoPostal(codigoPostal);
    cliente.setTelefono(telefono);
    cliente.setCorreo(correo);
    cliente.setFechaNacimiento(fechaNacimiento);
    cliente.setRfc(rfc);
    
    
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
  /**
   * @param idCliente Define el id del Cliente
   * @param nombre Define el Nombre del Cliente
   * @param apellidoPaterno Define el Apellido Paterno del Cliente
   * @param apellidoMaterno Define el Apellido Materno del Cliente
   * @param direccion Define la Direccion del Cliente
   * @param estado Define el Estado del Cliente
   * @param codigoPostal Define el Codigo Postal del Cliente
   * @param telefono Define el Telefono del Cliente
   * @param correo Define el Correo del Cliente
   * @param fechaNacimiento Define la Fecha de Nacimiento del Cliente
   * @param rfc Define el RFC del Cliente
   * @param attributes Para enviar atributos a otro controlador.
   * @return
   */
  @RequestMapping(value = "/modificarCliente", method = RequestMethod.POST)
  public RedirectView modificarCliente(
      @RequestParam("idCliente") int idCliente,
      @RequestParam("nombre") String nombre,
      @RequestParam("apellidoPaterno") String apellidoPaterno,
      @RequestParam("apellidoMaterno") String apellidoMaterno,
      @RequestParam("direccion") String direccion,
      @RequestParam("estado") String estado,
      @RequestParam("codigoPostal") int codigoPostal,
      @RequestParam("telefono") long telefono,
      @RequestParam("correo") String correo,
      @RequestParam("fechaNacimiento") Date fechaNacimiento,
      @RequestParam("rfc") String rfc,
      RedirectAttributes attributes) {
    
    Cliente cliente = new Cliente();
    
    cliente.setIdCliente(idCliente);
    cliente.setNombre(nombre);
    cliente.setApellidoPaterno(apellidoPaterno);
    cliente.setApellidoMaterno(apellidoMaterno);
    cliente.setDireccion(direccion);
    cliente.setEstado(estado);
    cliente.setCodigoPostal(codigoPostal);
    cliente.setTelefono(telefono);
    cliente.setCorreo(correo);
    cliente.setFechaNacimiento(fechaNacimiento);
    cliente.setRfc(rfc);
    
    
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
