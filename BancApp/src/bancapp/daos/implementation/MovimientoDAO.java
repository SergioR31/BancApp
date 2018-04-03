package bancapp.daos.implementation;

import bancapp.daos.interfaces.IChequeraDAO;
import bancapp.daos.interfaces.IMovimientoDAO;
import bancapp.models.Chequera;
import bancapp.models.Movimiento;

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
public class MovimientoDAO implements IMovimientoDAO {

  @Autowired
    private JdbcTemplate jdbcTemplate;

  /* (non-Javadoc)
   * @see bancapp.daos.interfaces.IChequeraDAO#insertarChequera(bancapp.models.Chequera)
   */
  
  @Override
  public String hacerRetiro (Movimiento movimiento) throws Exception {
    
    String mensaje = "";
    
    Connection conection = null;
    
    CallableStatement callableStatement = null;

    try {
      
      conection = jdbcTemplate.getDataSource().getConnection();
      
      callableStatement = conection.prepareCall("{call HACER_RETIRO(?, ?, ?)}");
      callableStatement.setLong(1, movimiento.getIdChequera());
      callableStatement.setDouble(2, movimiento.getMonto());
      callableStatement.setString(3, movimiento.getConcepto());

      callableStatement.executeUpdate();
      
      mensaje = "Retiro realizado con exito";
      
      
    } catch (Exception e) {
      
      System.out.println("Error en hacerRetiro: " + e);
      
      mensaje = "Error al hacer retiro!!!";
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
  public String hacerDeposito(Movimiento movimiento) throws Exception {
    // TODO Auto-generated method stub
    
    String mensaje = "";
    
    Connection conection = null;
    
    CallableStatement callableStatement = null;

    try {
      
      conection = jdbcTemplate.getDataSource().getConnection();
      
      callableStatement = conection.prepareCall("{call HACER_DEPOSITO(?, ?, ?)}");
      callableStatement.setLong(1, movimiento.getIdChequera());
      callableStatement.setDouble(2, movimiento.getMonto());
      callableStatement.setString(3, movimiento.getConcepto());

      callableStatement.executeUpdate();
      
      mensaje = "Deposito realizado con exito";
      
      
    } catch (Exception e) {
      
      System.out.println("Error en hacerDeposito: " + e);
      
      mensaje = "Error al hacer deposito!!!";
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

}
