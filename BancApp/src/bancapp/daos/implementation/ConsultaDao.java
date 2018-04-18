package bancapp.daos.implementation;

import bancapp.daos.interfaces.IconsultaDao;
import bancapp.models.Estadisticas;
import bancapp.models.Movimiento;

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
public class ConsultaDao implements IconsultaDao {

  @Autowired
    private JdbcTemplate jdbcTemplate;

  /* (non-Javadoc)
   * @see bancapp.daos.interfaces.IChequeraDAO#insertarChequera(bancapp.models.Chequera)
   */
  
  @Override
  public ArrayList<Movimiento> consultarDepositos(
      long idChequera, String periodo, int anio, int mes) throws Exception {
    
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
    
    
    String anual = "SELECT * FROM MOVIMIENTOS M "
        + "JOIN TIPOS_MOVIMIENTOS TM ON M.TIPO_MOVIMIENTO_ID = TM.ID "
        + "WHERE M.CHEQUERA_ID = " + idChequera + " "
            + "AND M.FECHA BETWEEN TO_DATE ('01/01/" + anio + "', 'DD/MM/YYYY') "
            + "AND TO_DATE ('31/12/" + anio + "', 'DD/MM/YYYY') "
                + "AND M.TIPO_MOVIMIENTO_ID IN (2, 4) ORDER BY M.FECHA";
    
    String mensual = "SELECT * FROM MOVIMIENTOS M "
        + "JOIN TIPOS_MOVIMIENTOS TM ON M.TIPO_MOVIMIENTO_ID = TM.ID "
        + "WHERE M.CHEQUERA_ID = " + idChequera + " "
            + "AND M.FECHA >= TO_DATE ('01/" + stringMes + "/" + anio + "', 'DD/MM/YYYY') "
            + "AND M.FECHA < TO_DATE ('" + dia + "/" + stringMes1 + "/" + anio + "', 'DD/MM/YYYY') "
                + "AND M.TIPO_MOVIMIENTO_ID IN (2, 4) ORDER BY M.FECHA";
    
    String completa = "SELECT * FROM MOVIMIENTOS M "
        + "JOIN TIPOS_MOVIMIENTOS TM ON M.TIPO_MOVIMIENTO_ID = TM.ID "
        + " WHERE M.CHEQUERA_ID = " + idChequera + " "
        + "AND M.TIPO_MOVIMIENTO_ID IN (2, 4) ORDER BY M.FECHA";
    
    String sql = "";
    
    if (periodo.equals("anual")) {
      sql = anual;
    } else if (periodo.equals("mensual")) {
      sql = mensual;
    } else {
      sql = completa;
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
          movimiento.setIdTipo(result.getInt("TIPO_MOVIMIENTO_ID"));
          movimiento.setOperacion(result.getString("OPERACION"));
          movimiento.setIdChequera(result.getLong("CHEQUERA_ID"));
          movimiento.setSaldo(result.getDouble("SALDO"));
          
          return movimiento;         
        }
        
      });
      
      
    } catch (Exception e) {
      
      System.out.println("Error en consultarDepositos de ConsultaDao: " + e);

    } 
    
    return depositos;
  }
  
  @Override
  public ArrayList<Movimiento> consultarDepositosFecha(
      long idChequera, String desde, String hasta) throws Exception {
    
    ArrayList<Movimiento> depositosFecha = new ArrayList<>();
    
    String sql = "SELECT * FROM MOVIMIENTOS M "
        + "JOIN TIPOS_MOVIMIENTOS TM ON M.TIPO_MOVIMIENTO_ID = TM.ID "
        + "WHERE M.CHEQUERA_ID = " + idChequera
        + " AND FECHA >= '" + desde + "' AND FECHA <= '" + hasta
        + "' AND M.TIPO_MOVIMIENTO_ID IN (2, 4) ORDER BY M.FECHA";
    
    try {
      
      depositosFecha = (ArrayList<Movimiento>) jdbcTemplate.query(sql, new RowMapper<Movimiento>() {
        
        @Override
        public Movimiento mapRow(ResultSet result, int rowNum) throws SQLException {
          
          Movimiento movimiento = new Movimiento();
          
          movimiento.setIdMovimiento(result.getInt("ID"));
          movimiento.setConcepto(result.getString("CONCEPTO"));
          movimiento.setMonto(result.getDouble("MONTO"));
          movimiento.setFecha(result.getTimestamp("FECHA"));
          movimiento.setStatus(result.getString("STATUS"));
          movimiento.setIdTipo(result.getInt("TIPO_MOVIMIENTO_ID"));
          movimiento.setOperacion(result.getString("OPERACION"));
          movimiento.setIdChequera(result.getLong("CHEQUERA_ID"));
          movimiento.setSaldo(result.getDouble("SALDO"));
          
          return movimiento;
          
        }
      });
      
    } catch (Exception e) {
      
      System.out.println("Error en consultarDepositosFecha de ConsultaDao: " + e);
      
    }
    
    return depositosFecha;
  }

  @Override
  public ArrayList<Movimiento> consultarRetiros(long idChequera, String periodo, int anio, int mes)
      throws Exception {

    ArrayList<Movimiento> retiros = new ArrayList<>();
    
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
    
    
    String anual = "SELECT * FROM MOVIMIENTOS M "
        + "JOIN TIPOS_MOVIMIENTOS TM ON M.TIPO_MOVIMIENTO_ID = TM.ID "
        + "WHERE M.CHEQUERA_ID = " + idChequera + " "
        + "AND M.FECHA BETWEEN TO_DATE ('01/01/" + anio + "', 'DD/MM/YYYY') "
        + "AND TO_DATE ('31/12/" + anio + "', 'DD/MM/YYYY') "
        + "AND M.TIPO_MOVIMIENTO_ID IN (1, 3) ORDER BY M.FECHA";
    
    String mensual = "SELECT * FROM MOVIMIENTOS M "
        + "JOIN TIPOS_MOVIMIENTOS TM ON M.TIPO_MOVIMIENTO_ID = TM.ID "
        + "WHERE M.CHEQUERA_ID = " + idChequera + " "
        + "AND M.FECHA >= TO_DATE ('01/" + stringMes + "/" + anio + "', 'DD/MM/YYYY') "
        + "AND M.FECHA < TO_DATE ('" + dia + "/" + stringMes1 + "/" + anio + "', 'DD/MM/YYYY') "
        + "AND M.TIPO_MOVIMIENTO_ID IN (1, 3) ORDER BY M.FECHA";
    
    String completa = "SELECT * FROM MOVIMIENTOS M "
        + "JOIN TIPOS_MOVIMIENTOS TM ON M.TIPO_MOVIMIENTO_ID = TM.ID "
        + " WHERE M.CHEQUERA_ID = " + idChequera + " "
        + "AND M.TIPO_MOVIMIENTO_ID IN (1, 3) ORDER BY M.FECHA";
    
    String sql = "";
    
    if (periodo.equals("anual")) {
      sql = anual;
    } else if (periodo.equals("mensual")) {
      sql = mensual;
    } else {
      sql = completa;
    }
    
    
    try {
      
      retiros = (ArrayList<Movimiento>) jdbcTemplate.query(sql, new RowMapper<Movimiento>() {
        
        @Override
        public Movimiento mapRow(ResultSet result, int rowNum) throws SQLException {
          
          Movimiento movimiento = new Movimiento();
          
          movimiento.setIdMovimiento(result.getInt("ID"));
          movimiento.setConcepto(result.getString("CONCEPTO"));
          movimiento.setMonto(result.getDouble("MONTO"));
          movimiento.setFecha(result.getTimestamp("FECHA"));
          movimiento.setStatus(result.getString("STATUS"));
          movimiento.setIdTipo(result.getInt("TIPO_MOVIMIENTO_ID"));
          movimiento.setOperacion(result.getString("OPERACION"));
          movimiento.setIdChequera(result.getLong("CHEQUERA_ID"));
          movimiento.setSaldo(result.getDouble("SALDO"));
          
          return movimiento;         
        }
        
      });
      
      
    } catch (Exception e) {
      
      System.out.println("Error en consultarRetiros de ConsultaDao: " + e);

    } 
    
    return retiros;
  }
  
  @Override
  public ArrayList<Movimiento> consultarRetirosFecha(
      long idChequera, String desde, String hasta) throws Exception {
    
    ArrayList<Movimiento> retirosFecha = new ArrayList<>();
    
    String sql = "SELECT * FROM MOVIMIENTOS M "
        + "JOIN TIPOS_MOVIMIENTOS TM ON M.TIPO_MOVIMIENTO_ID = TM.ID "
        + "WHERE M.CHEQUERA_ID = " + idChequera
        + " AND FECHA >= '" + desde + "' AND FECHA <= '" + hasta
        + "' AND M.TIPO_MOVIMIENTO_ID IN (1, 3) ORDER BY M.FECHA";
    
    try {
      
      retirosFecha = (ArrayList<Movimiento>) jdbcTemplate.query(sql, new RowMapper<Movimiento>() {
        
        @Override
        public Movimiento mapRow(ResultSet result, int rowNum) throws SQLException {
          
          Movimiento movimiento = new Movimiento();
          
          movimiento.setIdMovimiento(result.getInt("ID"));
          movimiento.setConcepto(result.getString("CONCEPTO"));
          movimiento.setMonto(result.getDouble("MONTO"));
          movimiento.setFecha(result.getTimestamp("FECHA"));
          movimiento.setStatus(result.getString("STATUS"));
          movimiento.setIdTipo(result.getInt("TIPO_MOVIMIENTO_ID"));
          movimiento.setOperacion(result.getString("OPERACION"));
          movimiento.setIdChequera(result.getLong("CHEQUERA_ID"));
          movimiento.setSaldo(result.getDouble("SALDO"));
          
          return movimiento;
          
        }
      });
      
    } catch (Exception e) {
      
      System.out.println("Error en consultarRetirosFecha de ConsultaDao: " + e);
      
    }
    
    return retirosFecha;
  }

  @Override
  public ArrayList<Movimiento> consultarTodos(
      long idChequera, String periodo, int anio, int mes) throws Exception {
    
    ArrayList<Movimiento> movimientosTodos = new ArrayList<>();
    
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
    
    String anual = "SELECT * FROM MOVIMIENTOS M "
        + "JOIN TIPOS_MOVIMIENTOS TM ON M.TIPO_MOVIMIENTO_ID = TM.ID "
        + "WHERE M.CHEQUERA_ID = " + idChequera + " "
        + "AND M.FECHA BETWEEN TO_DATE ('01/01/" + anio + "', 'DD/MM/YYYY') "
        + "AND TO_DATE ('31/12/" + anio + "', 'DD/MM/YYYY') "
        + "ORDER BY M.FECHA";
    
    String mensual = "SELECT * FROM MOVIMIENTOS M "
        + "JOIN TIPOS_MOVIMIENTOS TM ON M.TIPO_MOVIMIENTO_ID = TM.ID "
        + "WHERE M.CHEQUERA_ID = " + idChequera + " "
        + "AND M.FECHA >= TO_DATE ('01/" + stringMes + "/" + anio + "', 'DD/MM/YYYY') "
        + "AND M.FECHA < TO_DATE ('" + dia + "/" + stringMes1 + "/" + anio + "', 'DD/MM/YYYY') "
        + "ORDER BY M.FECHA";
    
    String completa = "SELECT * FROM MOVIMIENTOS M "
        + "JOIN TIPOS_MOVIMIENTOS TM ON M.TIPO_MOVIMIENTO_ID = TM.ID "
        + " WHERE M.CHEQUERA_ID = " + idChequera + " "
        + "ORDER BY M.FECHA";
    
    String sql = "";
    
    if (periodo.equals("anual")) {
      sql = anual;
    } else if (periodo.equals("mensual")) {
      sql = mensual;
    } else {
      sql = completa;
    }
    
    try {
      
      movimientosTodos = (ArrayList<Movimiento>) jdbcTemplate.query(sql,
          new RowMapper<Movimiento>() {
        
          @Override
          public Movimiento mapRow(ResultSet result, int rowNum) throws SQLException {
            
            Movimiento movimiento = new Movimiento();
          
            movimiento.setIdMovimiento(result.getInt("ID"));
            movimiento.setConcepto(result.getString("CONCEPTO"));
            movimiento.setMonto(result.getDouble("MONTO"));
            movimiento.setFecha(result.getTimestamp("FECHA"));
            movimiento.setStatus(result.getString("STATUS"));
            movimiento.setIdTipo(result.getInt("TIPO_MOVIMIENTO_ID"));
            movimiento.setOperacion(result.getString("OPERACION"));
            movimiento.setIdChequera(result.getLong("CHEQUERA_ID"));
            movimiento.setSaldo(result.getDouble("SALDO"));
          
            return movimiento; 
          
          }
        });
      
    } catch (Exception e) {
      System.out.println("Error en consultarTodos de ConsultaDao: " + e);
    }
    
    return movimientosTodos;
  }
  
  @Override
  public ArrayList<Movimiento> consultarTodosFecha(
      long idChequera, String desde, String hasta) throws Exception {
    
    ArrayList<Movimiento> todosFecha = new ArrayList<>();
    
    String sql = "SELECT * FROM MOVIMIENTOS M "
        + "JOIN TIPOS_MOVIMIENTOS TM ON M.TIPO_MOVIMIENTO_ID = TM.ID "
        + "WHERE M.CHEQUERA_ID = " + idChequera
        + " AND FECHA >= '" + desde + "' AND FECHA <= '" + hasta
        + "' ORDER BY M.FECHA";
    
    try {
      
      todosFecha = (ArrayList<Movimiento>) jdbcTemplate.query(sql, new RowMapper<Movimiento>() {
        
        @Override
        public Movimiento mapRow(ResultSet result, int rowNum) throws SQLException {
          
          Movimiento movimiento = new Movimiento();
          
          movimiento.setIdMovimiento(result.getInt("ID"));
          movimiento.setConcepto(result.getString("CONCEPTO"));
          movimiento.setMonto(result.getDouble("MONTO"));
          movimiento.setFecha(result.getTimestamp("FECHA"));
          movimiento.setStatus(result.getString("STATUS"));
          movimiento.setIdTipo(result.getInt("TIPO_MOVIMIENTO_ID"));
          movimiento.setOperacion(result.getString("OPERACION"));
          movimiento.setIdChequera(result.getLong("CHEQUERA_ID"));
          movimiento.setSaldo(result.getDouble("SALDO"));
          movimiento.setOperacion(result.getString("OPERACION"));
          
          return movimiento;
          
        }
      });
      
    } catch (Exception e) {
      
      System.out.println("Error en consultarTodosFecha de ConsultaDao: " + e);
      
    }
    
    return todosFecha;
  }

  @Override
  public Estadisticas consultarEstadisticas() throws Exception {
    
    String sql = "SELECT  (SELECT COUNT(*) FROM BANCOS) AS Numero_Bancos, "
        + "(SELECT COUNT(*) FROM CLIENTES) AS Numero_Clientes, " 
        + "(SELECT COUNT(*) FROM CHEQUERAS) AS Numero_Chequeras " 
        + "FROM    dual";
    
    Estadisticas estadisticas = new Estadisticas();
    
    try {
      
      estadisticas = jdbcTemplate.query(sql, new RowMapper<Estadisticas>() {
        
        @Override
        public Estadisticas mapRow(ResultSet result, int rowNum) throws SQLException {
          Estadisticas estadisticas = new Estadisticas();
          estadisticas.setNumeroBancos(result.getInt("Numero_Bancos"));
          estadisticas.setNumeroClientes(result.getInt("Numero_Clientes"));
          estadisticas.setNumeroChequeras(result.getInt("Numero_Chequeras"));
          return estadisticas;
        }
        
      }).get(0);
      
    } catch (Exception e) {
      System.out.println("Error en consultarEstadisticas de ConsultaDao: " + e);
    }
    
    return estadisticas;
  }

}
