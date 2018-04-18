package bancapp.daos.implementation;

import bancapp.daos.interfaces.ImovimientoDao;
import bancapp.models.Movimiento;

import java.sql.CallableStatement;
import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



/**
 * Objeto de Acceso a Datos.
 * @author SergioRamos
 *
 */
@Repository
public class MovimientoDao implements ImovimientoDao {

  @Autowired
    private JdbcTemplate jdbcTemplate;

  /* (non-Javadoc)
   * @see bancapp.daos.interfaces.IChequeraDAO#insertarChequera(bancapp.models.Chequera)
   */
  
  @Override
  public String hacerRetiro(Movimiento movimiento) throws Exception {
    
    String mensaje = "";
    
    Connection conection = null;
    
    CallableStatement callableStatement = null;

    try {
      
      conection = jdbcTemplate.getDataSource().getConnection();
      
      callableStatement = conection.prepareCall("{call HACER_RETIRO_FM(?, ?, ?, ?)}");
      callableStatement.setTimestamp(1, movimiento.getFecha());
      callableStatement.setLong(2, movimiento.getIdChequera());
      callableStatement.setDouble(3, movimiento.getMonto());
      callableStatement.setString(4, movimiento.getConcepto());

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
    
    String mensaje = "";
    
    Connection conection = null;
    
    CallableStatement callableStatement = null;

    try {
      
      conection = jdbcTemplate.getDataSource().getConnection();
      
      callableStatement = conection.prepareCall("{call HACER_DEPOSITO_FM(?, ?, ?, ?)}");
      callableStatement.setTimestamp(1, movimiento.getFecha());
      callableStatement.setLong(2, movimiento.getIdChequera());
      callableStatement.setDouble(3, movimiento.getMonto());
      callableStatement.setString(4, movimiento.getConcepto());

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

  @Override
  public String hacerTransferencia(Movimiento movimiento, long clabe) throws Exception {
    
    String mensaje = "";
    
    Connection conection = null;
    
    CallableStatement callableStatement = null;
    
    try {
      
      conection = jdbcTemplate.getDataSource().getConnection();
      
      callableStatement = conection.prepareCall("{call HACER_TRANSFERENCIA_FM(?, ?, ?, ?, ?)}");
      callableStatement.setTimestamp(1, movimiento.getFecha());
      callableStatement.setLong(2, movimiento.getIdChequera());
      callableStatement.setDouble(3, movimiento.getMonto());
      callableStatement.setString(4, movimiento.getConcepto());
      callableStatement.setLong(5, clabe);

      callableStatement.executeUpdate();
      
      mensaje = "Transferencia realizado con exito";
      
      
    } catch (Exception e) {
      
      System.out.println("Error en hacerTransferencia en MovimientoDao: " + e);
      
      mensaje = "Error al hacer transferencia!!!";
      
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
