package bancapp.daos.implementation;

import bancapp.daos.interfaces.IBancoDAO;
import bancapp.models.Banco;

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
public class BancoDAO implements IBancoDAO {

  @Autowired
    private JdbcTemplate jdbcTemplate;

  /* (non-Javadoc)
   * @see bancapp.daos.interfaces.IBancoDAO#insertarBanco(bancapp.models.Banco)
   */

  @Override
  public ArrayList<Banco> listarBancos() throws Exception {
    
    ArrayList<Banco> bancos = new ArrayList<>();

    String sql = "SELECT ID, ENTIDAD, SUCURSAL, DIRECCION, STATUS FROM BANCOS ORDER BY ID";
    
    try {
      
      
      bancos = (ArrayList<Banco>) jdbcTemplate.query(sql, new RowMapper<Banco>() {
        
        @Override
        public Banco mapRow(ResultSet result, int rowNum) throws SQLException {
          Banco banco = new Banco();
          banco.setIdBanco(result.getInt("ID"));
          banco.setEntidad(result.getString("ENTIDAD"));
          banco.setSucursal(result.getString("SUCURSAL"));
          banco.setDireccion(result.getString("DIRECCION"));
          banco.setStatus(result.getString("STATUS"));
          
          return banco;
          }
        
      });
      
    } catch (Exception e) {
      System.out.println("Error en BancoDAO: " + e);
    }
    
    return bancos;
    
  }
  
  @Override
  public Banco consultarBanco(int idBanco) throws Exception {
    
    Banco banco = new Banco();
    
    String sql = "SELECT ID, ENTIDAD, SUCURSAL, DIRECCION, STATUS FROM BANCOS WHERE ID = ?";
    
    try {
      banco = (Banco) jdbcTemplate.query(sql, 
          new Object[] {Integer.valueOf(idBanco) }, 
          new RowMapper<Banco>() {
        
          @Override
          public Banco mapRow(ResultSet result, int rowNum) throws SQLException {
            Banco banco = new Banco();
            banco.setIdBanco(result.getInt("ID"));
            banco.setEntidad(result.getString("ENTIDAD"));
            banco.setSucursal(result.getString("SUCURSAL"));
            banco.setDireccion(result.getString("DIRECCION"));
            banco.setStatus(result.getString("STATUS"));
          
            return banco;
          
          }
        
        }).get(0);
    } catch (Exception e) {
      System.out.println("Error en consultarBanco en BancoDAO: " + e);
    }
    
    return banco;
  }
  
  
  @Override
  public String insertarBanco(Banco banco) throws Exception {
    
    String mensaje = "";
    
    Connection conection = null;
    CallableStatement callableStatement = null;

    try {
      
      conection = jdbcTemplate.getDataSource().getConnection();
      
      callableStatement = conection.prepareCall("{call AGREGAR_BANCO(?, ?, ?)}");
      callableStatement.setString(1, banco.getEntidad());
      callableStatement.setString(2, banco.getSucursal());
      callableStatement.setString(3, banco.getDireccion());
      callableStatement.registerOutParameter(3, Types.VARCHAR);
      callableStatement.executeUpdate();
      
      mensaje = "Banco insertado con exito";
      
      
    } catch (Exception e) {
      
      System.out.println("Error en insertarBanco: " + e);
      
      mensaje = "Error al insertar banco!!!";
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
  public String modificarBanco(Banco banco) throws Exception {
    
    String mensaje = "";
    
    String sql = "UPDATE BANCOS "
        + "SET ENTIDAD = ?, "
        + " SUCURSAL = ?, "
        + " DIRECCION = ? "
        + "WHERE ID = ?";

    try {
      
      jdbcTemplate.update(sql, 
          banco.getEntidad(), banco.getSucursal(), banco.getDireccion(), banco.getIdBanco());
    
      mensaje = "Banco actualizado con exito";
      
    } catch (Exception e) {
      System.out.println("Error en modificarBanco de BancoDAO: " + e);
      
      mensaje = "Error al modificar banco!!!";
      
    }
    
    
    return mensaje;
  }
  
  @Override
  public String eliminarBanco(int idBanco) throws Exception {
    
    String mensaje = "";
    
    String sql = "UPDATE BANCOS "
        + "SET STATUS = ? "
        + "WHERE ID = ?";

    try {
      
      jdbcTemplate.update(sql, "No Disponible", idBanco);
    
      mensaje = "Banco borrado con exito";
      
    } catch (Exception e) {
      System.out.println("Error en eliminarBanco de BancoDAO: " + e);
      
      mensaje = "Error al borrar banco!!!";
      
    }
    
    
    return mensaje;
  }

}
