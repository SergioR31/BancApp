package bancapp.daos.implementation;

import bancapp.daos.interfaces.IClienteDAO;
import bancapp.models.Cliente;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



/**
 * Objeto de Acceso a Datos.
 * @author SergioRamos
 *
 */
@Repository
public class ClienteDAO implements IClienteDAO {

  @Autowired
    private JdbcTemplate jdbcTemplate;

  /* (non-Javadoc)
   * @see bancapp.daos.interfaces.IClienteDAO#insertarCliente(bancapp.models.Cliente)
   */

  @Override
  public ArrayList<Cliente> listarClientes() throws Exception {
    
    ArrayList<Cliente> clientes = new ArrayList<>();

    String sql = "SELECT ID, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, DIRECCION, ESTADO, CODIGO_POSTAL, TELEFONO, CORREO, FECHA_NACIMIENTO, RFC, STATUS FROM CLIENTES ORDER BY ID";
    
    try {
      
      
      clientes = (ArrayList<Cliente>) jdbcTemplate.query(sql, new RowMapper<Cliente>() {
        
        @Override
        public Cliente mapRow(ResultSet result, int rowNum) throws SQLException {
          Cliente cliente = new Cliente();
          cliente.setIdCliente(result.getInt("ID"));
          cliente.setNombre(result.getString("NOMBRE"));
          cliente.setApellidoPaterno(result.getString("APELLIDO_PATERNO"));
          cliente.setApellidoMaterno(result.getString("APELLIDO_MATERNO"));
          cliente.setDireccion(result.getString("DIRECCION"));
          cliente.setEstado(result.getString("ESTADO"));
          cliente.setCodigoPostal(result.getInt("CODIGO_POSTAL"));
          cliente.setTelefono(result.getLong("TELEFONO"));
          cliente.setCorreo(result.getString("CORREO"));
          cliente.setFechaNacimiento(result.getDate("FECHA_NACIMIENTO"));
          cliente.setRfc(result.getString("RFC"));
          cliente.setStatus(result.getString("STATUS"));
          
          
          return cliente;
          }
        
      });
      
    } catch (Exception e) {
      System.out.println("Error en listarClientes de ClienteDAO: " + e);
    }
    
    return clientes;
    
  }
  
  @Override
  public Cliente consultarCliente(int idCliente) throws Exception {
    
    Cliente cliente = new Cliente();
    
    String sql = "SELECT ID, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, DIRECCION, ESTADO, CODIGO_POSTAL, TELEFONO, CORREO, FECHA_NACIMIENTO, RFC, STATUS FROM CLIENTES WHERE ID = ?";
    
    try {
      cliente = (Cliente) jdbcTemplate.query(sql, 
          new Object[] {Integer.valueOf(idCliente) }, 
          new RowMapper<Cliente>() {
        
          @Override
          public Cliente mapRow(ResultSet result, int rowNum) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(result.getInt("ID"));
            cliente.setNombre(result.getString("NOMBRE"));
            cliente.setApellidoPaterno(result.getString("APELLIDO_PATERNO"));
            cliente.setApellidoMaterno(result.getString("APELLIDO_MATERNO"));
            cliente.setDireccion(result.getString("DIRECCION"));
            cliente.setEstado(result.getString("ESTADO"));
            cliente.setCodigoPostal(result.getInt("CODIGO_POSTAL"));
            cliente.setTelefono(result.getInt("TELEFONO"));
            cliente.setCorreo(result.getString("CORREO"));
            cliente.setFechaNacimiento(result.getDate("FECHA_NACIMIENTO"));
            cliente.setRfc(result.getString("RFC"));
            cliente.setStatus(result.getString("STATUS"));
          
            return cliente;
          
          }
        
        }).get(0);
    } catch (Exception e) {
      System.out.println("Error en consultarCliente de ClienteDAO: " + e);
    }
    
    return cliente;
  }
  
  
  @Override
  public String insertarCliente(Cliente cliente) throws Exception {
//    TODO Modificar Stored Procedure
    
    String mensaje = "";
    
    Connection conection = null;
    
    CallableStatement callableStatement = null;
    
    System.out.println(cliente.getFechaNacimiento());

    try {
      
      conection = jdbcTemplate.getDataSource().getConnection();
      
      callableStatement = conection.prepareCall("{call AGREGAR_CLIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
      callableStatement.setString(1, cliente.getNombre());
      callableStatement.setString(2, cliente.getApellidoPaterno());
      callableStatement.setString(3, cliente.getApellidoMaterno());
      callableStatement.setString(4, cliente.getDireccion()); 
      callableStatement.setString(5, cliente.getEstado());
      callableStatement.setInt(6, cliente.getCodigoPostal());
      callableStatement.setLong(7, cliente.getTelefono());
      callableStatement.setString(8,cliente.getCorreo());
      callableStatement.setDate(9, cliente.getFechaNacimiento());
      callableStatement.setString(10, cliente.getRfc());
      callableStatement.executeUpdate();
      
      mensaje = "Cliente insertado con exito";
      
      
    } catch (Exception e) {
      
      System.out.println("Error en insertarCliente: " + e);
      
      mensaje = "Error al insertar cliente!!!";
    } finally {
      if (conection != null) {
        conection.close();
      }
      if (callableStatement != null) {
        callableStatement.close();
      }
    }

    return mensaje;
  }

  @Override
  public String modificarCliente(Cliente cliente) throws Exception {
    //TODO Modificar metodo
    
    String mensaje = "";
    
    String sql = "UPDATE BANCOS "
        + "SET ENTIDAD = ?, "
        + " SUCURSAL = ?, "
        + " DIRECCION = ? "
        + "WHERE ID = ?";

    try {
      
//      jdbcTemplate.update(sql, 
//          cliente.getEntidad(), cliente.getSucursal(), cliente.getDireccion(), cliente.getIdCliente());
    
      mensaje = "Cliente actualizado con exito";
      
    } catch (Exception e) {
      System.out.println("Error en modificarCliente de ClienteDAO: " + e);
      
      mensaje = "Error al modificar cliente!!!";
      
    }
    
    
    return mensaje;
  }
  
  @Override
  public String eliminarCliente(int idCliente) throws Exception {
    //TODO Modificar metodo
    
    String mensaje = "";
    
    String sql = "UPDATE BANCOS "
        + "SET STATUS = ? "
        + "WHERE ID = ?";

    try {
      
      jdbcTemplate.update(sql, "No Disponible", idCliente);
    
      mensaje = "Cliente borrado con exito";
      
    } catch (Exception e) {
      System.out.println("Error en eliminarCliente de ClienteDAO: " + e);
      
      mensaje = "Error al borrar cliente!!!";
      
    }
    
    
    return mensaje;
  }

}
