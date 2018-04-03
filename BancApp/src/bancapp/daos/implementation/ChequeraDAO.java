package bancapp.daos.implementation;

import bancapp.daos.interfaces.IChequeraDAO;
import bancapp.models.Chequera;

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
public class ChequeraDAO implements IChequeraDAO {

  @Autowired
    private JdbcTemplate jdbcTemplate;

  /* (non-Javadoc)
   * @see bancapp.daos.interfaces.IChequeraDAO#insertarChequera(bancapp.models.Chequera)
   */

  @Override
  public ArrayList<Chequera> listarChequeras() throws Exception {
    
    ArrayList<Chequera> chequeras = new ArrayList<>();

    String sql = "SELECT CH.ID AS CHEQUERA_ID, CH.SALDO_APERTURA, CH.FECHA_APERTURA, CH.SALDO, CH.CLABE, CH.STATUS, CL.ID AS CLIENTE_ID, CL.NOMBRE, B.ID AS BANCO_ID, B.ENTIDAD AS BANCO_NOMBRE "
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
          chequera.setSaldoApertura(result.getInt("SALDO_APERTURA"));
          chequera.setFechaApertura(result.getDate("FECHA_APERTURA"));
          chequera.setSaldo(result.getInt("SALDO"));
          chequera.setClabe(result.getLong("CLABE"));
          chequera.setStatus(result.getString("STATUS"));
          chequera.setIdCliente(result.getInt("CLIENTE_ID"));
          chequera.setNombreCliente(result.getString("NOMBRE"));
          chequera.setIdBanco(result.getInt("BANCO_ID"));
          chequera.setNombreBanco(result.getString("BANCO_NOMBRE"));
          
//          chequera.setIdChequera(result.getInt("ID"));
//          chequera.setNombre(result.getString("NOMBRE"));
//          chequera.setApellidoPaterno(result.getString("APELLIDO_PATERNO"));
//          chequera.setApellidoMaterno(result.getString("APELLIDO_MATERNO"));
//          chequera.setDireccion(result.getString("DIRECCION"));
//          chequera.setEstado(result.getString("ESTADO"));
//          chequera.setCodigoPostal(result.getInt("CODIGO_POSTAL"));
//          chequera.setTelefono(result.getLong("TELEFONO"));
//          chequera.setCorreo(result.getString("CORREO"));
//          chequera.setFechaNacimiento(result.getDate("FECHA_NACIMIENTO"));
//          chequera.setRfc(result.getString("RFC"));
//          chequera.setStatus(result.getString("STATUS"));
          
          
          return chequera;
          }
        
      });
      
    } catch (Exception e) {
      System.out.println("Error en listarChequeras de ChequeraDAO: " + e);
    }
    
    return chequeras;
    
  }
  
  @Override
  public Chequera consultarChequera(int idChequera) throws Exception {
    //TODO Cambiar consulta
    
    Chequera chequera = new Chequera();
    
    String sql = "SELECT ID, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, DIRECCION, ESTADO, CODIGO_POSTAL, TELEFONO, CORREO, FECHA_NACIMIENTO, RFC, STATUS FROM CLIENTES WHERE ID = ?";
    
    try {
      chequera = (Chequera) jdbcTemplate.query(sql, 
          new Object[] {Integer.valueOf(idChequera) }, 
          new RowMapper<Chequera>() {
        
          @Override
          public Chequera mapRow(ResultSet result, int rowNum) throws SQLException {
            Chequera chequera = new Chequera();
//            chequera.setIdChequera(result.getInt("ID"));
//            chequera.setNombre(result.getString("NOMBRE"));
//            chequera.setApellidoPaterno(result.getString("APELLIDO_PATERNO"));
//            chequera.setApellidoMaterno(result.getString("APELLIDO_MATERNO"));
//            chequera.setDireccion(result.getString("DIRECCION"));
//            chequera.setEstado(result.getString("ESTADO"));
//            chequera.setCodigoPostal(result.getInt("CODIGO_POSTAL"));
//            chequera.setTelefono(result.getLong("TELEFONO"));
//            chequera.setCorreo(result.getString("CORREO"));
//            chequera.setFechaNacimiento(result.getDate("FECHA_NACIMIENTO"));
//            chequera.setRfc(result.getString("RFC"));
//            chequera.setStatus(result.getString("STATUS"));
          
            return chequera;
          
          }
        
        }).get(0);
    } catch (Exception e) {
      System.out.println("Error en consultarChequera de ChequeraDAO: " + e);
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
      
      callableStatement = conection.prepareCall("{call AGREGAR_CHEQUERA(?, ?, ?)}");
      callableStatement.setInt(1, chequera.getSaldoApertura());
      callableStatement.setInt(2, chequera.getIdCliente());
      callableStatement.setInt(3, chequera.getIdBanco());

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
    
    String mensaje = "";
//    
//    String sql = "UPDATE CLIENTES "
//        + "SET NOMBRE = ?, "
//        + " APELLIDO_PATERNO = ?, "
//        + " APELLIDO_MATERNO = ?, "
//        + " DIRECCION = ?, "
//        + " ESTADO = ?, "
//        + " CODIGO_POSTAL = ?, "
//        + " TELEFONO = ?, "
//        + " CORREO = ?, "
//        + " FECHA_NACIMIENTO = ?, "
//        + " RFC = ? "
//        + "WHERE ID = ?";
//
//    try {
//      
////      jdbcTemplate.update(sql, 
////          chequera.getNombre(), chequera.getApellidoPaterno(), chequera.getApellidoMaterno(), chequera.getDireccion(), chequera.getEstado(), chequera.getCodigoPostal(), chequera.getTelefono(), chequera.getCorreo(), chequera.getFechaNacimiento(), chequera.getRfc(), chequera.getIdChequera());
//    
      mensaje = "Chequera actualizado con exito";
//      
//    } catch (Exception e) {
//      System.out.println("Error en modificarChequera de ChequeraDAO: " + e);
//      
//      mensaje = "Error al modificar chequera!!!";
//      
//    }
    
    
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
      System.out.println("Error en eliminarChequera de ChequeraDAO: " + e);
      
      mensaje = "Error al borrar chequera!!!";
      
    }
    
    
    return mensaje;
  }

}
