package bancapp.daos.implementation;

import bancapp.daos.interfaces.IchequeraDao;
import bancapp.models.Chequera;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ChequeraDao implements IchequeraDao {

  @Autowired
    private JdbcTemplate jdbcTemplate;

  /* (non-Javadoc)
   * @see bancapp.daos.interfaces.IChequeraDAO#insertarChequera(bancapp.models.Chequera)
   */

  @Override
  public ArrayList<Chequera> listarChequeras() throws Exception {
    
    ArrayList<Chequera> chequeras = new ArrayList<>();

    String sql = "SELECT CH.ID AS CHEQUERA_ID, CH.SALDO_APERTURA, CH.FECHA_APERTURA, "
        + "CH.SALDO, CH.CLABE, CH.STATUS, CL.ID AS CLIENTE_ID, CL.NOMBRE, CL.APELLIDO_PATERNO, "
        + "B.ID AS BANCO_ID, B.ENTIDAD AS BANCO_NOMBRE "
        + "FROM CHEQUERAS CH "
        + "JOIN CLIENTES CL ON CH.CLIENTE_ID = CL.ID "
        + "JOIN BANCOS B ON CH.BANCO_ID = B.ID "
        + "ORDER BY CH.ID";
    
    try {
      
      
      chequeras = (ArrayList<Chequera>) jdbcTemplate.query(sql, new RowMapper<Chequera>() {
        
        @Override
        public Chequera mapRow(ResultSet result, int rowNum) throws SQLException {
          Chequera chequera = new Chequera();
          chequera.setIdChequera(result.getLong("CHEQUERA_ID"));
          chequera.setSaldoApertura(result.getDouble("SALDO_APERTURA"));
          chequera.setFechaApertura(result.getDate("FECHA_APERTURA"));
          chequera.setSaldo(result.getDouble("SALDO"));
          chequera.setClabe(result.getLong("CLABE"));
          chequera.setStatus(result.getString("STATUS"));
          chequera.setIdCliente(result.getInt("CLIENTE_ID"));
          chequera.setNombreCliente(result.getString("NOMBRE"));
          chequera.setApellodoPCliente(result.getString("APELLIDO_PATERNO"));
          chequera.setIdBanco(result.getInt("BANCO_ID"));
          chequera.setNombreBanco(result.getString("BANCO_NOMBRE"));
          
          
          return chequera;
          }
        
      });
      
    } catch (Exception e) {
      System.out.println("Error en listarChequeras de ChequeraDao: " + e);
    }
    
    return chequeras;
    
  }
  
  @Override
  public Chequera consultarChequera(long idChequera) throws Exception {
    
    Chequera chequera = new Chequera();
    
    String sql = "SELECT CH.ID AS CHEQUERA_ID, CH.SALDO_APERTURA, "
        + "CH.FECHA_APERTURA, CH.SALDO, CH.CLABE, CH.STATUS, "
        + "CL.ID AS CLIENTE_ID, CL.NOMBRE, CL.APELLIDO_PATERNO, "
        + "B.ID AS BANCO_ID, B.ENTIDAD AS BANCO_NOMBRE "
        + "FROM CHEQUERAS CH "
        + "JOIN CLIENTES CL ON CH.CLIENTE_ID = CL.ID "
        + "JOIN BANCOS B ON CH.BANCO_ID = B.ID WHERE CH.ID = ? "
        + "ORDER BY CH.ID";
    
    try {
      chequera = (Chequera) jdbcTemplate.query(sql, 
          new Object[] {Long.valueOf(idChequera) }, 
          new RowMapper<Chequera>() {
        
          @Override
          public Chequera mapRow(ResultSet result, int rowNum) throws SQLException {
            Chequera chequera = new Chequera();
            chequera.setIdChequera(result.getLong("CHEQUERA_ID"));
            chequera.setSaldoApertura(result.getDouble("SALDO_APERTURA"));
            chequera.setFechaApertura(result.getDate("FECHA_APERTURA"));
            chequera.setSaldo(result.getDouble("SALDO"));
            chequera.setClabe(result.getLong("CLABE"));
            chequera.setStatus(result.getString("STATUS"));
            chequera.setIdCliente(result.getInt("CLIENTE_ID"));
            chequera.setNombreCliente(result.getString("NOMBRE"));
            chequera.setApellodoPCliente(result.getString("APELLIDO_PATERNO"));
            chequera.setIdBanco(result.getInt("BANCO_ID"));
            chequera.setNombreBanco(result.getString("BANCO_NOMBRE"));
            return chequera;
          
          }
        
        }).get(0);
    } catch (Exception e) {
      System.out.println("Error en consultarChequera de ChequeraDao: " + e);
    }
    
    return chequera;
  }
  
  
  @Override
  public String insertarChequera(Chequera chequera) throws Exception {
    
    String mensaje = "";
    
    Connection conection = null;
    
    CallableStatement callableStatement = null;

    try {
      
      conection = jdbcTemplate.getDataSource().getConnection();
      
      callableStatement = conection.prepareCall("{call AGREGAR_CHEQUERA_FM(?, ?, ?, ?)}");
      callableStatement.setDate(1, chequera.getFechaApertura());
      callableStatement.setDouble(2, chequera.getSaldoApertura());
      callableStatement.setInt(3, chequera.getIdCliente());
      callableStatement.setInt(4, chequera.getIdBanco());

      callableStatement.executeUpdate();
      
      mensaje = "Chequera insertada con exito";
      
      
    } catch (Exception e) {
      
      System.out.println("Error en insertarChequera: " + e);
      
      mensaje = "Error al insertar chequera!!!";
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
  public String modificarChequera(Chequera chequera) throws Exception {
    
    String mensaje = "No se puede modificar chequera.";
    
    return mensaje;
  }
  
  @Override
  public String eliminarChequera(long idChequera) throws Exception {
    
    String mensaje = "";
    
    String sql = "UPDATE CHEQUERAS "
        + "SET STATUS = ? "
        + "WHERE ID = ?";

    try {
      
      jdbcTemplate.update(sql, "Inactiva", idChequera);
    
      mensaje = "Chequera borrado con exito";
      
    } catch (Exception e) {
      System.out.println("Error en eliminarChequera de ChequeraDao: " + e);
      
      mensaje = "Error al borrar chequera!!!";
      
    }
    
    
    return mensaje;
  }

}
