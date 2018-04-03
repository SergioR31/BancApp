package bancapp.controllers;

import bancapp.models.Banco;
import bancapp.models.Chequera;
import bancapp.models.Cliente;
import bancapp.services.interfaces.IBancoService;
import bancapp.services.interfaces.IChequeraService;
import bancapp.services.interfaces.IClienteService;

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
 * Chequeras Controller.
 * @author SergioRamos
 *
 */

@Controller
public class ChequerasController {
  
  @Autowired
  private IChequeraService chequeraService;
  
  @Autowired
  private IClienteService clienteService;
  
  @Autowired
  private IBancoService bancoService;

  private static final String MENSAJE = "mensaje";
  
  /**
   * Mapeo de metodo para mostrar pagina listado-chequeras con las chequeras de la base de datos.  
   * @param model Define atributos para el JSP.
   * @return
   */
  @RequestMapping(value = "/Chequeras/listado")
    public String chequerasMostrar(Model model) {
      
    ArrayList<Chequera> chequeras = new ArrayList<Chequera>();
    
    try {
      
      chequeras = (ArrayList<Chequera>) chequeraService.listarChequeras();
      
    } catch (Exception e) {
      System.out.println("Error en chequerasMostrar: " + e);
    }
    
    model.addAttribute("chequeras", chequeras);
    
//    model.addAttribute(MENSAJE, (String) model.asMap().get(MENSAJE));
      
    return "chequeras-listado";
      
  }
  
  /**
 * Mapeo de metodo para mostrar la pagina de chequeras-agregar.  
 * @param model Define atributos para el JSP.
 * @return
 */
  @RequestMapping(value = "/Chequeras/agregar", method = RequestMethod.GET)
  public String chequerasInsertar(Model model) {
    
    List<Banco> bancos = new ArrayList<>();
    List<Cliente> clientes = new ArrayList<>();
    
    try {
      
      bancos = bancoService.listarBancos();
      clientes = clienteService.listarClientes();
      
    } catch (Exception e) {
      System.out.println("Error al traer los datos de bancos y clientes: " + e);
    }
    
    model.addAttribute("bancos", bancos);
    model.addAttribute("clientes", clientes);
    
    return "chequeras-agregar";
    
  }
  
//  /**
//   * Mapeo de metodo para mostrar la pagina para modificar un chequera.  
//   * @param idChequera Define el id del CLiente
//   * @param model Define atributos para el JSP.
//   * @return
//   */
//  @RequestMapping(value = "/Chequeras/modificar")
//    public String chequerasModificar(
//        @RequestParam("idChequera") int idChequera,
//        Model model) {
//    
//    Chequera chequera = new Chequera();
//    
//    try {
//      chequera = chequeraService.consultarChequera(idChequera);
//    } catch (Exception e) {
//      System.out.println("Error en chequerasModificar: " + e);
//    }
//    
//    model.addAttribute("chequera", chequera);
//    
//    return "chequeras-modificar";
//    
//  }
  
  
  
  /**
   * Mapeo de metodo para insertar un Chequera en la Base de Datos.
   * @param nombre define Nombre del Chequera
   * @param apellidoPaterno define apellidoPaterno del Chequera
   * @param apellidoMaterno define Nombre del Chequera
   * @param direccion define direccion del Chequera
   * @param estado define estado del Chequera
   * @param codigoPostal define codigoPostal del Chequera
   * @param telefono define telefono del Chequera
   * @param correo define correo del Chequera
   * @param fechaNacimiento define fechaNacimiento del Chequera
   * @param rfc define rfc del Chequera
   * @param attributes Para enviar atributos a otro controlador.
   */
  @RequestMapping(value = "/insertarChequera", method = RequestMethod.POST)
  public RedirectView insertarChequera(
      @RequestParam("saldoApertura") int saldoApertura,
      @RequestParam("idBanco") int idBanco,
      @RequestParam("idCliente") int idCliente,
      RedirectAttributes attributes) {
    
    Chequera chequera = new Chequera();
    
    chequera.setSaldoApertura(saldoApertura);
    chequera.setIdBanco(idBanco);
    chequera.setIdCliente(idCliente);    
    
    String mensaje = "";
    
    try {
      
      
      mensaje = chequeraService.insertarChequera(chequera);
      
    } catch (Exception e) { 
      System.out.println("Error en insertarChequera en ChequerasController: " + e);
    }
    
    attributes.addFlashAttribute(MENSAJE, mensaje);
    
    return new RedirectView("Chequeras/listado");
    
  }
  
//  /**
//   * Mapeo de metodo para modificar un Chequera en la Base de Datos.
//  /**
//   * @param idChequera Define el id del Chequera
//   * @param nombre Define el Nombre del Chequera
//   * @param apellidoPaterno Define el Apellido Paterno del Chequera
//   * @param apellidoMaterno Define el Apellido Materno del Chequera
//   * @param direccion Define la Direccion del Chequera
//   * @param estado Define el Estado del Chequera
//   * @param codigoPostal Define el Codigo Postal del Chequera
//   * @param telefono Define el Telefono del Chequera
//   * @param correo Define el Correo del Chequera
//   * @param fechaNacimiento Define la Fecha de Nacimiento del Chequera
//   * @param rfc Define el RFC del Chequera
//   * @param attributes Para enviar atributos a otro controlador.
//   * @return
//   */
//  @RequestMapping(value = "/modificarChequera", method = RequestMethod.POST)
//  public RedirectView modificarChequera(
//      @RequestParam("idChequera") int idChequera,
//      @RequestParam("nombre") String nombre,
//      @RequestParam("apellidoPaterno") String apellidoPaterno,
//      @RequestParam("apellidoMaterno") String apellidoMaterno,
//      @RequestParam("direccion") String direccion,
//      @RequestParam("estado") String estado,
//      @RequestParam("codigoPostal") int codigoPostal,
//      @RequestParam("telefono") long telefono,
//      @RequestParam("correo") String correo,
//      @RequestParam("fechaNacimiento") Date fechaNacimiento,
//      @RequestParam("rfc") String rfc,
//      RedirectAttributes attributes) {
//    
//    Chequera chequera = new Chequera();
//    
////    chequera.setIdChequera(idChequera);
////    chequera.setNombre(nombre);
////    chequera.setApellidoPaterno(apellidoPaterno);
////    chequera.setApellidoMaterno(apellidoMaterno);
////    chequera.setDireccion(direccion);
////    chequera.setEstado(estado);
////    chequera.setCodigoPostal(codigoPostal);
////    chequera.setTelefono(telefono);
////    chequera.setCorreo(correo);
////    chequera.setFechaNacimiento(fechaNacimiento);
////    chequera.setRfc(rfc);
//    
//    
//    String mensaje = "";
//    
//    try {
//      
//      
////      mensaje = chequeraService.modificarChequera(chequera);
//      
//    } catch (Exception e) { 
//      System.out.println("Error en modificarChequera en ChequerasController: " + e);
//    }
//    
//    attributes.addFlashAttribute(MENSAJE, mensaje);
//    
//    return new RedirectView("Chequeras/listado");
//    
//  }
  
  /**
   * Mapeo de metodo para "eliminar" un Chequera en la Base de Datos.
   * @param attributes Para enviar atributos a otro controlador.
   * @return
   */
  @RequestMapping(value = "/eliminarChequera", method = RequestMethod.POST)
  public RedirectView eliminarChequera(
      @RequestParam("idChequera") long idChequera,
      RedirectAttributes attributes) {
    
    String mensaje = "";
    
    try {
      
      mensaje = chequeraService.eliminarChequera(idChequera);
      
    } catch (Exception e) { 
      System.out.println("Error en eliminarChequera en ChequerasController: " + e);
    }
    
    attributes.addFlashAttribute(MENSAJE, mensaje);
    
    return new RedirectView("Chequeras/listado");
    
  }

}
