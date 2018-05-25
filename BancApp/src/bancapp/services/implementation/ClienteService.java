package bancapp.services.implementation;

import bancapp.daos.interfaces.IclienteDao;
import bancapp.models.Cliente;
import bancapp.services.interfaces.IClienteService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementacion del Service de Cliente.
 * @author SergioRamos
 *
 */
@Service
public class ClienteService implements IClienteService {
  
  @Autowired
  private IclienteDao clienteDAO;

  /* (non-Javadoc)
   * @see bancapp.services.interfaces.IClienteService#insertarCliente(bancapp.models.Cliente)
   */
  
  @Override
  public List<Cliente> listarClientes() throws Exception {
    
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    
    try {
      clientes = (ArrayList<Cliente>) clienteDAO.listarClientes();
    } catch (Exception e) {
      System.out.println("Error en listarClientes en ClienteService: " + e);
    }
    return clientes;
  }
  
  @Override
  public Cliente consultarCliente(int idCliente) throws Exception {
    
    Cliente cliente = new Cliente();
    
    try {
      cliente = clienteDAO.consultarCliente(idCliente);
    } catch (Exception e) {
      System.out.println("Error en consultarCliente en Cliente Service: " + e);
    }
    return cliente;
  }
  
  @Override
  public String insertarCliente(Cliente cliente) throws Exception {
    
    String respuesta = "";
    
    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    
    boolean actualizarCliente = false;
    
    try {
      
      clientes = (ArrayList<Cliente>) clienteDAO.listarClientes();
      
      for (Cliente clientedb : clientes) {
        if (cliente.getRfc().equals(clientedb.getRfc())) {
          cliente.setIdCliente(clientedb.getIdCliente());
          actualizarCliente = true;
        }
      }
      
      if (actualizarCliente) {
        cliente.setStatus("Disponible");
        clienteDAO.modificarCliente(cliente);
        respuesta = "Cliente ya existia en DB, informacion y status actualizados.";
      } else {
        respuesta = clienteDAO.insertarCliente(cliente);
      }
      
    } catch (Exception e) {
      System.out.println("Error en insertarCliente ClienteService: " + e);
    }
    
    return respuesta;
  }

  @Override
  public String modificarCliente(Cliente cliente) throws Exception {
    
    String respuesta = "";
    
    cliente.setStatus("Disponible");
    
    try {
      respuesta = clienteDAO.modificarCliente(cliente);
    } catch (Exception e) {
      System.out.println("Error en modificarCliente en ClienteService");
    }
    return respuesta;
  }

  @Override
  public String eliminarCliente(int idCliente) throws Exception {
    String respuesta = "";
    
    try {
      respuesta = clienteDAO.eliminarCliente(idCliente);
    } catch (Exception e) {
      System.out.println("Error en eliminarCliente en ClienteService");
    }
    return respuesta;
  }

  

}
