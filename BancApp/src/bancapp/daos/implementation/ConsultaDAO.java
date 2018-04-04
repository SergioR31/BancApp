package bancapp.daos.implementation;

import bancapp.daos.interfaces.IChequeraDAO;
import bancapp.daos.interfaces.IConsultaDAO;
import bancapp.daos.interfaces.IMovimientoDAO;
import bancapp.models.Chequera;
import bancapp.models.Cliente;
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
public class ConsultaDAO implements IConsultaDAO {

  @Autowired
    private JdbcTemplate jdbcTemplate;

  /* (non-Javadoc)
   * @see bancapp.daos.interfaces.IChequeraDAO#insertarChequera(bancapp.models.Chequera)
   */
  
  @Override
  public ArrayList<Movimiento> consultarDepositos (long idChequera, String periodo, int anio, int mes) throws Exception {
    
    ArrayList<Movimiento> depositos = new ArrayList<>();
    
    String stringMes = "";
    String stringMes1 = "";
    String dia = "01";
    
    if (mes < 10) {
      stringMes = "0" + mes;
    } else {
      stringMes = "" + mes;
    }
    
    if ((mes + 1) < 10) {
      stringMes1 = "0" + (mes + 1);
    } else {
      stringMes1 = "" + (mes + 1);
    }
    
    
    if (mes == 12) {
      stringMes1 = "" + mes;
      dia = "12";
    }
    
    
    String anual = "SELECT * FROM MOVIMIENTOS "
        + "WHERE CHEQUERA_ID = " + idChequera + " AND FECHA BETWEEN TO_DATE ('01/01/" + anio + "', 'DD/MM/YYYY') "
            + "AND TO_DATE ('31/12/" + anio + "', 'DD/MM/YYYY') "
                + "AND TIPO_MOVIMIENTO_ID IN (2, 4) ORDER BY ID";
    
    String mensual = "SELECT * FROM MOVIMIENTOS "
        + "WHERE CHEQUERA_ID = " + idChequera + " AND FECHA >= TO_DATE ('01/" + stringMes + "/" + anio + "', 'DD/MM/YYYY') "
            + "AND FECHA < TO_DATE ('"+ dia +"/" + stringMes1 + "/" + anio + "', 'DD/MM/YYYY') "
                + "AND TIPO_MOVIMIENTO_ID IN (2, 4) ORDER BY ID";
    String sql = "";
    
    if (periodo.equals("anual")) {
      sql = anual;
    } else {
      sql = mensual;
    }
    
    
    try {
      
      depositos = (ArrayList<Movimiento>) jdbcTemplate.query(sql, new RowMapper<Movimiento>() {
        
        @Override
        public Movimiento mapRow(ResultSet result, int rowNum) throws SQLException {
          
          Movimiento movimiento = new Movimiento();
          
          movimiento.setIdMovimiento(result.getInt("ID"));
          movimiento.setConcepto(result.getString("CONCEPTO"));
          movimiento.setMonto(result.getDouble("MONTO"));
          movimiento.setFecha(result.getTimestamp("FECHA"));
          movimiento.setStatus(result.getString("STATUS"));
          movimiento.setTipo(result.getInt("TIPO_MOVIMIENTO_ID"));
          movimiento.setIdChequera(result.getLong("CHEQUERA_ID"));
          movimiento.setSaldo(result.getDouble("SALDO"));
          
          return movimiento;         
        }
        
      });
      
      
    } catch (Exception e) {
      
      System.out.println("Error en consultarDepositos de ConsultaDAO: " + e);

    } 
    
    return depositos;
  }

}
