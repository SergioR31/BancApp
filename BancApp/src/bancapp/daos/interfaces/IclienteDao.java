package bancapp.daos.interfaces;

import bancapp.models.Cliente;

import java.util.List;

/**
 * Interfaz del Objeto de Acceso a DAtos de Cliente.
 * @author SergioRamos
 *
 */
public interface IclienteDao {
  public List<Cliente> listarClientes() throws Exception;
  
  public String insertarCliente(Cliente cliente) throws Exception;
  
  public Cliente consultarCliente(int idCliente) throws Exception;
  
  public String modificarCliente(Cliente cliente) throws Exception;
  
  public String eliminarCliente(int idCliente) throws Exception;
  
}
